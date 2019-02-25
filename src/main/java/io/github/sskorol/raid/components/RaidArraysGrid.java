package io.github.sskorol.raid.components;

import io.github.sskorol.raid.core.BaseElement;
import io.github.sskorol.raid.core.Image;
import io.github.sskorol.raid.model.RaidArrayInfo;
import io.github.sskorol.raid.screens.MainScreen;
import lombok.val;

public class RaidArraysGrid extends MainScreen {

    //@Image(screenWidth = 250, screenHeight = 50, x = 900, y = 240, highlight = true) for old lrm
    @Image(screenWidth = 250, screenHeight = 50, x = 1070, y = 280, highlight = true)
    private BaseElement labelStatus;

    //@Image(screenWidth = 80, screenHeight = 50, x = 780, y = 240, highlight = true)
   // private BaseElement labelDisks;
    @Image(screenWidth = 204, screenHeight = 50, x = 700, y = 280, highlight = true)
    private BaseElement labelAvailable;

    @Image(screenWidth = 150, screenHeight = 50, x = 904, y = 280, highlight = true)
    private BaseElement labelParityOrMirror;

    //@Image(screenWidth = 80, screenHeight = 50, x = 620, y = 240, highlight = true)
    @Image(screenWidth = 80, screenHeight = 50, x = 400, y = 280, highlight = true)
    private BaseElement labelCapacity;

    //@Image(screenWidth = 80, screenHeight = 50, x = 490, y = 240, highlight = true)
    @Image(screenWidth = 80, screenHeight = 50, x = 555, y = 280, highlight = true)
    private BaseElement labelRaid;

    //@Image(screenWidth = 100, screenHeight = 50, x = 210, y = 240, highlight = true)
    @Image(screenWidth = 100, screenHeight = 50, x = 210, y = 280, highlight = true)
    private BaseElement labelName;

    public RaidArrayInfo getInfo() {
        val name = labelName.text().trim();
        val raid = labelRaid.text().trim();
        val capacity = labelCapacity.text().trim();
        val available = labelAvailable.text().trim();
        val parityormirror = labelParityOrMirror.text().trim();
        val status = labelStatus.text().trim();
        return new RaidArrayInfo(name, raid, capacity, available, parityormirror, status);
    }
}
