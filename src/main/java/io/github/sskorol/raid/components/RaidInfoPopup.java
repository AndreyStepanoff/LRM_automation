package io.github.sskorol.raid.components;

import io.github.sskorol.raid.core.BaseElement;
import io.github.sskorol.raid.core.Image;
import io.github.sskorol.raid.screens.MainScreen;
import io.github.sskorol.raid.model.RaidInfo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static io.github.sskorol.raid.utils.ScreenFactory.at;

public class RaidInfoPopup extends MainScreen {

    private static final int WIDTH = 550;
    private static final int HEIGHT = 445;
    private static final int TITLE_HEIGHT = 70;
    private static final int BORDER_OFFSET = 20;

    @Image(name = "buttonClose", affinity = 0.8f)
    private BaseElement buttonClose;

    @Image(screenWidth = 320, screenHeight = 250, highlight = true)
    private BaseElement labelRaidInfo;

    @Getter
    private final List<RaidInfo> raidInfo;

    public RaidInfoPopup() {
        this.raidInfo = new ArrayList<>();
    }

    public MainMenu closePopup() {
        buttonClose.click();
        return at(MainMenu.class);
    }

    public RaidInfoPopup retrieveRaidInfo() {
        labelRaidInfo.adjustArea(
            size -> (size.width / 2) - (WIDTH / 2) + BORDER_OFFSET,
            size -> (size.height / 2) - (HEIGHT / 2) + TITLE_HEIGHT + BORDER_OFFSET);
        this.raidInfo.add(RaidInfo.of(labelRaidInfo.text()));
        return this;
    }
}
