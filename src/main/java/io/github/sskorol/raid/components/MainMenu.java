package io.github.sskorol.raid.components;

import io.github.sskorol.raid.core.BaseElement;
import io.github.sskorol.raid.core.Image;
import io.github.sskorol.raid.screens.Screen;
import io.github.sskorol.raid.screens.ArraysScreen;
import io.github.sskorol.raid.screens.OverviewScreen;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.util.streamex.StreamEx;

import java.awt.*;

import static io.github.sskorol.raid.utils.ScreenFactory.at;
import static io.github.sskorol.raid.components.MainMenu.Item.ARRAYS;
import static io.github.sskorol.raid.components.MainMenu.Item.OVERVIEW;
import static io.vavr.API.*;

@Slf4j
public class MainMenu implements Screen {

    @Image(name = "buttonOverview")
    private BaseElement menuItemOverview;

    @Image(name = "arraysClosed")
    private BaseElement menuItemArrays;

    @Image(name = "iconArray", affinity = 0.8f)
    private BaseElement iconArray;

    @Image(y = 135, screenWidth = 200, screenHeight = 200, highlight = true)
    private BaseElement mainMenu;

    public <T extends Screen> T select(final Class<T> screenClass) {
        final T screen = at(screenClass);
        Match(Item.of(screenClass)).of(
            Case($(OVERVIEW), item -> run(() -> mainMenu.clickPoint(item.point.x, item.point.y))),
            Case($(ARRAYS), item -> run(() -> {
                mainMenu.clickPoint(item.point.x, item.point.y);
                iconArray.click();
            })),
            Case($(), () -> run(() -> {
                throw new IllegalArgumentException("Unable to open " + screenClass);
            }))
        );
        return screen;
    }

    @RequiredArgsConstructor
    public enum Item {
        OVERVIEW(OverviewScreen.class, new Point(100, 25)),
        ARRAYS(ArraysScreen.class, new Point(100, 75));

        private final Class<?> screenClass;
        private final Point point;

        public static Item of(final Class<?> screenClass) {
            return StreamEx.of(Item.values())
                .findFirst(item -> item.screenClass == screenClass)
                .orElseThrow(() -> new IllegalArgumentException("Unable to find menu item by " + screenClass));
        }
    }
}
