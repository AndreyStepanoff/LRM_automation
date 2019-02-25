package io.github.sskorol.raid.core;

import io.github.sskorol.raid.exceptions.NoSuchElementException;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import one.util.streamex.EntryStream;
import one.util.streamex.StreamEx;
import org.sikuli.basics.Debug;
import org.sikuli.basics.Settings;
import org.sikuli.script.*;

import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Function;

import static io.github.sskorol.raid.config.BaseConfig.CONFIG;
import static java.lang.ClassLoader.getSystemResource;
import static java.lang.String.format;
import static org.assertj.core.util.Arrays.asList;

@Slf4j
public class BaseElement {

    private final Pattern[] elements;
    private final Region desktop;
    private final boolean shouldHighlight;
    private final Dimension screenSize;
    private boolean isObserved;

    public BaseElement(final float affinity, final boolean shouldHighlight, final String[] names, final int height,
                       final int width, final int x, final int y) {
        Debug.on(CONFIG.debugLevel());
        Settings.AlwaysResize = CONFIG.screenScaleFactor();
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.desktop = new Region(new Rectangle(x, y,
            width == CONFIG.defaultWidth() ? screenSize.width : width,
            height == CONFIG.defaultHeight() ? screenSize.height : height));
        this.elements = StreamEx.of(names).map(name -> createImage(name, affinity)).toArray(Pattern[]::new);
        this.shouldHighlight = shouldHighlight;
    }

    public BaseElement adjustArea(final Function<Dimension, Integer> x, final Function<Dimension, Integer> y) {
        desktop.setRect(x.apply(screenSize), y.apply(screenSize), desktop.w, desktop.h);
        return this;
    }

    private void click(final Consumer<Match> matcher, final boolean shouldWaitForAll) {
        EntryStream.of(elements).forEach(entry -> desktop.onAppear(entry.getValue(),
            handle(matcher, !shouldWaitForAll || entry.getKey() == elements.length - 1)));
        val observeResult = desktop.observe(CONFIG.waitTimeout());

        if (!isObserved && !observeResult) {
            throw new NoSuchElementException("Unable to find " + asList(elements));
        }
    }

    public void click(final boolean shouldWaitForAll) {
        click(Match::click, shouldWaitForAll);
    }

    public BaseElement click() {
        click(false);
        return waitForAction();
    }

    public void doubleClick(final boolean shouldWaitForAll) {
        click(Match::doubleClick, shouldWaitForAll);
    }

    public void doubleClick() {
        doubleClick(false);
    }

    public void doubleClick(final String text) {
        Try.of(() -> desktop.waitText(text, CONFIG.waitTimeout()))
            .map(match -> shouldHighlight ? match.highlight(CONFIG.highlightTimeout()) : match)
            .onSuccess(Region::doubleClick);
    }

    public void clickPoint(final int x, final int y) {
        if (shouldHighlight) {
            desktop.highlight(CONFIG.highlightTimeout());
        }
        Commands.click(new Location(desktop.x + x, desktop.y + y));
    }

    public boolean exists() {
        if (shouldHighlight) {
            desktop.highlight(CONFIG.highlightTimeout());
        }
        return elements.length > 0 && desktop.exists(elements[0], CONFIG.conditionalTimeout()) != null;
    }

    public String text() {
        desktop.wait(CONFIG.commandPause());
        return shouldHighlight ? desktop.highlight(CONFIG.highlightTimeout()).text() : desktop.text();
    }

    public BaseElement waitForAction(final long timeout) {
        Try.run(() -> Thread.sleep(timeout * 1000));
        return this;
    }

    public BaseElement waitForAction() {
        return waitForAction(CONFIG.actionTimeout());
    }

    private ObserverCallBack handle(final Consumer<Match> matcher, final boolean shouldStopObserving) {
        return new ObserverCallBack() {
            @Override
            public void appeared(ObserveEvent e) {
                matcher.accept(highlight(e.getMatch()));
                if (shouldStopObserving) {
                    stopObserving();
                }
            }
        };
    }

    private Match highlight(final Match match) {
        if (shouldHighlight) {
            match.highlight(CONFIG.highlightTimeout());
        }
        return match;
    }

    private void stopObserving() {
        isObserved = true;
        desktop.stopObserver();
    }

    private Pattern createImage(final String name, final float similarity) {
        return new Pattern(getSystemResource(format("%s.%s", name, CONFIG.imageExtension())).getPath()).similar(similarity);
    }

    public boolean disappears() {
        if (shouldHighlight) {
            desktop.highlight(CONFIG.highlightTimeout());
        }
        return disappears(CONFIG.waitTimeout());
    }

    public boolean disappears(final double timeout) {
        if (shouldHighlight) {
                   desktop.highlight(CONFIG.highlightTimeout());
        }
        return elements.length > 0 && desktop.waitVanish(elements[0], timeout);
    }
}
