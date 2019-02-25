package io.github.sskorol.raid.screens;

import io.github.sskorol.raid.components.MainMenu;
import io.github.sskorol.raid.core.BaseElement;
import io.github.sskorol.raid.core.Image;

import static io.github.sskorol.raid.utils.ScreenFactory.at;

public class MainScreen implements Screen {

    @Image(name = "buttonUpdate")
    private BaseElement buttonUpdate;

    @Image(name = "buttonCancel", highlight = true)
    private BaseElement buttonCancel;

    @Image(name = "screenTitle", highlight = true)
    private BaseElement labelScreenTitle;

    public <T extends Screen> T select(final Class<T> screenClass) {
        if (screenClass == OverviewScreen.class) {
            dismissUpdateDialog().maximize();
        }
        return at(MainMenu.class).select(screenClass);
    }

    private MainScreen maximize() {
        labelScreenTitle.doubleClick();
        return this;
    }

    private MainScreen dismissUpdateDialog() {
        if (buttonUpdate.exists()) {
            buttonCancel.click();
        }
        return this;
    }


}
