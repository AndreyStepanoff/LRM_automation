package io.github.sskorol.raid.screens;

import io.github.sskorol.raid.components.RaidArraysGrid;
import io.github.sskorol.raid.components.RaidInfoPopup;
import io.github.sskorol.raid.core.BaseElement;
import io.github.sskorol.raid.core.Image;
import io.github.sskorol.raid.model.RaidArrayInfo;

import static io.github.sskorol.raid.utils.ScreenFactory.at;

public class OverviewScreen extends MainScreen {

    @Image(name = "buttonDelete", highlight = true)
    private BaseElement buttonDelete;

    @Image(name = "formattingWindow", highlight = true)
    private BaseElement formattingWindow;

    @Image(name = "buttonConfirmDelete", highlight = true)
    private BaseElement buttonConfirmDelete;

    @Image(name = "buttonDots", highlight = true)
    private BaseElement buttonDots;

    @Image(name = "buttonInfo", affinity = 0.9f)
    private BaseElement menuItemInfo;

    @Image(name = "buttonCreateArray", highlight = true)
    private BaseElement buttonCreateArray;

    @Image(screenWidth = 117, screenHeight = 39, x = 742, y = 509, highlight = true)
    private BaseElement buttonCreateArrayText;

    @Image(name = "summaryWindow",  highlight = true)
    private BaseElement summaryWindow;




    public OverviewScreen expandMenu() {
        buttonDots.click();
        return this;
    }

    public OverviewScreen deleteArray(){
        buttonDelete.click();
        buttonConfirmDelete.click();
        return at(OverviewScreen.class);
    }


    public OverviewScreen formattingWindowDisappear(){
        if (!formattingWindow.exists()){
            formattingWindow.waitForAction(15);
            formattingWindow.disappears(30);
        }
        else {
            formattingWindow.disappears(30);

        }
        return at(OverviewScreen.class);
    }

    public OverviewScreen summaryWindowDisappear() {
        if (summaryWindow.exists())
            summaryWindow.disappears();
        return at(OverviewScreen.class);
    }

    public RaidInfoPopup openRaidInfo() {
        menuItemInfo.click();
        return at(RaidInfoPopup.class);
    }

    public ArraySetupScreen createArray() {
        buttonCreateArray.click();
        return at(ArraySetupScreen.class);
    }



    public RaidArrayInfo raidGridInfo() {
        return at(RaidArraysGrid.class).getInfo();
    }
}
