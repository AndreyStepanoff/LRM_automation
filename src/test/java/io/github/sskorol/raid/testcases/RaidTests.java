package io.github.sskorol.raid.testcases;

import io.github.sskorol.core.DataSupplier;
import io.github.sskorol.raid.model.RaidArrayInfo;
import io.github.sskorol.raid.screens.ArraySetupScreen;
import io.github.sskorol.raid.screens.MainScreen;
import io.github.sskorol.raid.screens.OverviewScreen;
import javafx.scene.layout.Priority;
import one.util.streamex.StreamEx;
import org.testng.annotations.Test;

import static io.github.sskorol.raid.screens.ArraySetupScreen.Drive;
import static io.github.sskorol.raid.screens.ArraySetupScreen.Drive.*;
import static io.github.sskorol.raid.screens.ArraySetupScreen.RaidType;
import static io.github.sskorol.raid.screens.ArraySetupScreen.RaidType.*;
import static io.github.sskorol.raid.screens.ArraySetupScreen.SetupType.CUSTOM;
import static io.github.sskorol.raid.screens.ArraySetupScreen.SetupType.QUICK;
import static io.github.sskorol.raid.utils.ScreenFactory.at;
import static org.assertj.core.api.Assertions.assertThat;

public class RaidTests {



    @Test(dataProvider = "RAID0", priority = 0)
    public void RAID0(RaidArrayInfo expectedRaidInfo, Drive[] drives, RaidType raidType) {
        at(MainScreen.class)
                .select(OverviewScreen.class)
                .createArray()
                .startConfiguration(CUSTOM)
                .buttonNextClick()
                .selectDrive(drives)
                .selectRaid(raidType)
                .finishSetup()
                .summaryWindowDisappear()
                .formattingWindowDisappear();

        assertThat(at(OverviewScreen.class).raidGridInfo())
                .isEqualTo(expectedRaidInfo);

        at(MainScreen.class)
                .select(OverviewScreen.class)
                .expandMenu()
                .deleteArray();
    }


    @Test(dataProvider = "RAID5", priority = 1)
    public void RAID5(RaidArrayInfo expectedRaidInfo, Drive[] drives, RaidType raidType) {
        at(MainScreen.class)
            .select(OverviewScreen.class)
            .createArray()
            .startConfiguration(CUSTOM)
             .buttonNextClick()
            .selectDrive(drives)
            .selectRaid(raidType)
            .finishSetup()
                .summaryWindowDisappear()
                .formattingWindowDisappear();

        assertThat(at(OverviewScreen.class).raidGridInfo())
            .isEqualTo(expectedRaidInfo);

        at(MainScreen.class)
                .select(OverviewScreen.class)
                .expandMenu()
                .deleteArray();
    }

    @Test(dataProvider = "RAID6", priority = 2)
    public void RAID6(RaidArrayInfo expectedRaidInfo, Drive[] drives, RaidType raidType) {
        at(MainScreen.class)
                .select(OverviewScreen.class)
                .createArray()
                .startConfiguration(CUSTOM)
                .buttonNextClick()
                .selectDrive(drives)
                .selectRaid(raidType)
                .finishSetup()
                .summaryWindowDisappear()
                .formattingWindowDisappear();

        assertThat(at(OverviewScreen.class).raidGridInfo())
                .isEqualTo(expectedRaidInfo);

        at(MainScreen.class)
                .select(OverviewScreen.class)
                .expandMenu()
                .deleteArray();
    }

    @Test(dataProvider = "RAID10", priority = 3)
    public void RAID10(RaidArrayInfo expectedRaidInfo, Drive[] drives, RaidType raidType) {
        at(MainScreen.class)
                .select(OverviewScreen.class)
                .createArray()
                .startConfiguration(CUSTOM)
                .buttonNextClick()
                .selectDrive(drives)
                .selectRaid(raidType)
                .finishSetup()
                .summaryWindowDisappear()
                .formattingWindowDisappear();

        assertThat(at(OverviewScreen.class).raidGridInfo())
                .isEqualTo(expectedRaidInfo);

        at(MainScreen.class)
                .select(OverviewScreen.class)
                .expandMenu()
                .deleteArray();
    }







/*

    @Test(priority = 0, invocationCount = 3)
    public void createRaid1_1() {
        at(MainScreen.class)
                .select(OverviewScreen.class)
                .createArray()
                .startConfiguration(CUSTOM)
                .buttonNextClick()
                .selectDrive(DRIVE_1, DRIVE_2)
                .selectRaid(RAID_1)
                .finishSetup()
                .summaryWindowDisappear()
                .expandMenu()
                .deleteArray();

    }

        @Test(priority = 5,invocationCount = 3)
         public void createRaid1() {
              at(MainScreen.class)
                      .select(OverviewScreen.class)
                      .createArray()
                      .startConfiguration(CUSTOM)
                      .buttonNextClick()
                      .selectDrive(DRIVE_1,DRIVE_2)
                      .selectRaid(RAID_1)
                      .finishSetup()
                      .summaryWindowDisappear()
                      .expandMenu()
                      .deleteArray();

  }


    @Test(priority = 1)
    public void createRaid5() {
        at(MainScreen.class)
                .select(OverviewScreen.class)
                .createArray()
                .startConfiguration(CUSTOM)
                .buttonNextClick()
                .selectDrive(DRIVE_1,DRIVE_2,DRIVE_3,DRIVE_4, DRIVE_5, DRIVE_6)
                .selectRaid(RAID_5)
                .finishSetup()
                .formattingWindowDisappear();



    }
   @Test(priority = 3)
    public void deleteRaid5(){
        at (OverviewScreen.class)
                .expandMenu()
                .deleteArray();
    }

    @Test(priority = 4)
    public void quickCreate(){
        at(MainScreen.class)
                .select(OverviewScreen.class)
                .createArray()
                .startConfiguration(QUICK)
                .finishQuickSetup()
                ;
    }

    @Test(priority = 5)
    public void deleteQuickRaid(){
        at (OverviewScreen.class)
                .expandMenu()
                .deleteArray();
    }

  /* @Test
    public void copy(){
        at(ArraySetupScreen.class)
                .copyToMountedDrive();

    }


    @Test(priority = 2, dataProvider = "customRaidInfo")
    public void raidArrayInfoShouldMatch(RaidArrayInfo expectedRaidInfo) {
        at(MainScreen.class)
            .select(OverviewScreen.class);

        assertThat(at(OverviewScreen.class).raidGridInfo()).isEqualTo(expectedRaidInfo);
    }
*/
    @DataSupplier(transpose = true)
    public StreamEx RAID0() {
        return StreamEx.of(
            new RaidArrayInfo("Array 01", RAID_0.getName(), "24.0 TB", "24.0 TB", "", "No protection"),
            StreamEx.of(DRIVE_1, DRIVE_2, DRIVE_3, DRIVE_4, DRIVE_5, DRIVE_6).toArray(Drive[]::new),
            RAID_0);
    }



    @DataSupplier(transpose = true)
    public StreamEx RAID5() {
        return StreamEx.of(
            new RaidArrayInfo("Array 01", RAID_5.getName(), "20.0 TB", "20.0 TB", "4.0 TB", "Background initialization (0%)"),
            StreamEx.of(DRIVE_1, DRIVE_2, DRIVE_3, DRIVE_4, DRIVE_5, DRIVE_6).toArray(Drive[]::new),
            RAID_5);
    }


    @DataSupplier(transpose = true)
    public StreamEx RAID6() {
        return StreamEx.of(
                new RaidArrayInfo("Array 01", RAID_6.getName(), "16.0 TB", "16.0 TB", "8.0 TB", "Background initialization (0%)"),
                StreamEx.of(DRIVE_1, DRIVE_2, DRIVE_3, DRIVE_4, DRIVE_5, DRIVE_6).toArray(Drive[]::new),
                RAID_6);
    }

    @DataSupplier(transpose = true)
    public StreamEx RAID10() {
        return StreamEx.of(
                new RaidArrayInfo("Array 01", RAID_10.getName(), "8.0 TB", "8.0 TB", "8.0 TB", "Background initialization (0%)"),
                StreamEx.of(DRIVE_1, DRIVE_2, DRIVE_3, DRIVE_4).toArray(Drive[]::new),
                RAID_10);
    }

    @DataSupplier
    public RaidArrayInfo customRaidInfo() {
        return new RaidArrayInfo("Array 01", RAID_5.getName(), "20.0 TB", "20TB", "4.0 TB", "OK");
    }



}
