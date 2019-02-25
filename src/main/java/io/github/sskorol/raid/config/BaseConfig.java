package io.github.sskorol.raid.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigCache;

public interface BaseConfig extends Config {

    BaseConfig CONFIG = ConfigCache.getOrCreate(BaseConfig.class, System.getenv(), System.getProperties());

    @DefaultValue("LaCie RAID Manager")
    String appName();

    @DefaultValue("20")
    int waitTimeout();

    @DefaultValue("1")
    int actionTimeout();

    @DefaultValue("5")
    int conditionalTimeout();

    @DefaultValue("30")
    int startupTimeout();

    @DefaultValue("1")
    int highlightTimeout();

    @DefaultValue("0.5f")
    float screenScaleFactor();

    @DefaultValue("1439")
    int defaultWidth();

    @DefaultValue("845")
    int defaultHeight();

    @DefaultValue("png")
    String imageExtension();

    @DefaultValue("3")
    int debugLevel();

    @DefaultValue("8")
    int raidInfoBlocks();

    @DefaultValue("3")
    int disksInfoBlocks();

    @DefaultValue("1.0")
    double commandPause();
}
