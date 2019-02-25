package io.github.sskorol.raid.screens;

import io.github.sskorol.raid.components.RaidInfoPopup;
import io.github.sskorol.raid.core.BaseElement;
import io.github.sskorol.raid.core.Image;

import static io.github.sskorol.raid.utils.ScreenFactory.at;

public class ArraysScreen extends MainScreen {

    @Image(name = "arrayDetails")
    private BaseElement arrayDetails;

    public RaidInfoPopup openArrayDetails() {
        arrayDetails.click();
        return at(RaidInfoPopup.class);
    }

}
