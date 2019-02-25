package io.github.sskorol.raid.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import one.util.streamex.StreamEx;

import static io.github.sskorol.raid.config.BaseConfig.CONFIG;
import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.joor.Reflect.on;

@Data
public class RaidInfo {

    private final String id;
    private final String raidLevel;
    private final String status;
    private final String size;
    private final String diskBlock;
    private final String stripeSize;
    private final String arrayCacheStatus;
    private final String diskCacheStatus;

    public static RaidInfo of(final String info) {
        val args = StreamEx.of(info.split("\n"))
            .filter(value -> !value.trim().isEmpty())
            .map(Title::assignedValueOf)
            .filter(value -> !value.isEmpty())
            .toArray();

        return args.length == CONFIG.raidInfoBlocks() ? on(RaidInfo.class).create(args).get() : empty();
    }

    public static RaidInfo empty() {
        return new RaidInfo("", "", "", "", "", "", "", "");
    }

    @Getter
    @RequiredArgsConstructor
    public enum Title {
        ID("ID"),
        RAID_LEVEL("RAID level"),
        STATUS("Status"),
        SIZE("Size"),
        DISK_BLOCK("Disk block"),
        STRIPE_SIZE("Stripe size"),
        ARRAY_CACHE_STATUS("Array cache status"),
        DISK_CACHE_STATUS("Disk cache status");

        private final String value;

        public static String assignedValueOf(final String text) {
            return StreamEx.of(values())
                .findFirst(val -> text.startsWith(val.getValue()))
                .map(val -> substringAfter(text, val.getValue()).trim())
                .orElse("");
        }
    }
}
