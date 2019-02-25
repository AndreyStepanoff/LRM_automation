package io.github.sskorol.raid.screens;

import io.github.sskorol.raid.core.BaseElement;
import io.github.sskorol.raid.core.Image;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.github.sskorol.raid.screens.ArraySetupScreen.DisableSwitchers.DisableDiskCache;
import static io.github.sskorol.raid.screens.ArraySetupScreen.SetupType.CUSTOM;
import static io.github.sskorol.raid.utils.ScreenFactory.at;
import static io.vavr.API.*;

@Slf4j
public class ArraySetupScreen extends MainScreen {

    @Image (name = "linkQuickArray", highlight = true)
     private BaseElement linkQuickArray;

    @Image(name = "linkCustomArray", highlight = true)
    private BaseElement linkCustomArray;

    @Image(name = "buttonNext", highlight = true)
    private BaseElement buttonNext;

    @Image(name = "buttonNext2", highlight = true)
    private BaseElement buttonNext2;

    @Image(name = "buttonContinue", highlight = true)
    private BaseElement buttonContinue;

    @Image(name = "buttonFinish", highlight = true)
    private BaseElement buttonFinish;

    @Image(name = "buttonIgnore", highlight = true)
    private BaseElement buttonIgnore;

    @Image(name = "optionRaid0", highlight = true)
    private BaseElement optionRaid0;

    @Image(name = "optionRaid1", highlight = true)
    private BaseElement optionRaid1;

    @Image(name = "optionRaid5", highlight = true)
    private BaseElement optionRaid5;

    @Image(name = "optionRaid6", highlight = true)
    private BaseElement optionRaid6;

    @Image(name = "optionRaid10", highlight = true)
    private BaseElement optionRaid10;

    @Image(name = "optionRaid50", highlight = true)
    private BaseElement optionRaid50;

    @Image(name = "optionRaid60", highlight = true)
    private BaseElement optionRaid60;

    @Image(name = "buttonDrive1", highlight = true)
    private BaseElement buttonDrive1;

    @Image(name = "buttonDrive2", highlight = true)
    private BaseElement buttonDrive2;

    @Image(name = "buttonDrive3", highlight = true)
    private BaseElement buttonDrive3;

    @Image(name = "buttonDrive4", highlight = true)
    private BaseElement buttonDrive4;

    @Image(name = "buttonDrive5", highlight = true)
    private BaseElement buttonDrive5;

    @Image(name = "buttonDrive6", highlight = true)
    private BaseElement buttonDrive6;

    @Image(name = "buttonDrive7", highlight = true)
    private BaseElement buttonDrive7;

    @Image(name = "buttonDrive8", highlight = true)
    private BaseElement buttonDrive8;

    @Image(name = "buttonDrive9", highlight = true)
    private BaseElement buttonDrive9;

    @Image(name = "buttonDrive10", highlight = true)
    private BaseElement buttonDrive10;

    @Image(name = "buttonDrive11", highlight = true)
    private BaseElement buttonDrive11;

    @Image(name = "buttonDrive12", highlight = true)
    private BaseElement buttonDrive12;

    @Image(name = "autoFormattSwitcher",  highlight = true)
    private BaseElement autoFormattSwitcher;

    @Image(name = "diskCacheSwitcher",  highlight = true)
    private BaseElement diskCacheSwitcher;



    private final List<BaseElement> drives;
    private final List<BaseElement> raids;


    public ArraySetupScreen() {
        this.drives = new ArrayList<>();
        this.raids = new ArrayList<>();
    }

    public ArraySetupScreen startConfiguration(final SetupType type) {
        Match(type).of(
            Case($(CUSTOM), () -> run(() -> linkCustomArray.click())),
            //Case($(), () -> run(() -> log.info("{} setup type is not yet implemented", type))),
                Case($(SetupType.QUICK), () -> run(() -> linkQuickArray.click()))
        );
        return fillInSupportedDrives().fillInSupportedRaids();
    }

    public ArraySetupScreen buttonNextClick(){
        buttonNext.click();
        if (buttonNext.exists())
            buttonNext.click();
        return this; //at(ArraySetupScreen.class);

    }


    public OverviewScreen finishQuickSetup() {
        buttonNext.click();
        buttonFinish.click();
        return at(OverviewScreen.class);
    }

    public OverviewScreen finishSetup() {
        buttonNext.click();
     if (buttonContinue.exists())
         buttonContinue.click();

   /*  if (!isAutoFormatEnabled) {
         buttonNext
     }*/
      //  disable.DisableDiskCache(diskCacheSwitcher.clickPoint(581, 457));

        buttonNext2.click();
        buttonFinish.click();

        return at(OverviewScreen.class);
    }



    public ArraySetupScreen selectDrive(final Drive ...drives) {
        Arrays.stream(drives).forEach(drive -> this.drives.get(drive.getIndex()).click());
        return this;
    }


    public ArraySetupScreen selectRaid(final RaidType type) {
        raids.get(type.getIndex()).click();
        return this;
    }



    private ArraySetupScreen fillInSupportedRaids() {
        if (raids.isEmpty()) {
            raids.add(optionRaid0);
            raids.add(optionRaid1);
            raids.add(optionRaid5);
            raids.add(optionRaid6);
            raids.add(optionRaid10);
            raids.add(optionRaid50);
            raids.add(optionRaid60);

        }
        return this;
    }

    private ArraySetupScreen fillInSupportedDrives() {
        if (drives.isEmpty()) {
            drives.add(buttonDrive1);
            drives.add(buttonDrive2);
            drives.add(buttonDrive3);
            drives.add(buttonDrive4);
            drives.add(buttonDrive5);
            drives.add(buttonDrive6);
            drives.add(buttonDrive7);
            drives.add(buttonDrive8);
            drives.add(buttonDrive9);
            drives.add(buttonDrive10);
            drives.add(buttonDrive11);
            drives.add(buttonDrive12);
        }
        return this;
    }


    public enum SetupType {
        QUICK, CUSTOM
    }

    public enum DisableSwitchers {
        DisableAutoFormat,
        DisableDiskCache
    }

    @Getter
    @RequiredArgsConstructor
    public enum Drive {
        DRIVE_1(0), DRIVE_2(1), DRIVE_3(2), DRIVE_4(3), DRIVE_5(4), DRIVE_6(5),DRIVE_7(6), DRIVE_8(7), DRIVE_9(8), DRIVE_10(9), DRIVE_11(10), DRIVE_12(10);

        private final int index;
    }

    @Getter
    @RequiredArgsConstructor
    public enum RaidType {
        RAID_0(0), RAID_1(1), RAID_5(2), RAID_6(3), RAID_10(4), RAID_50(5), RAID_60(6);

        private final int index;

        public String getName() {
            return name().replace("_", " ");
        }
    }
}
