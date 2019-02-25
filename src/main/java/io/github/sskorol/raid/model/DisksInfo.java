package io.github.sskorol.raid.model;

import lombok.Data;
import lombok.val;
import one.util.streamex.StreamEx;

import static io.github.sskorol.raid.config.BaseConfig.CONFIG;
import static org.joor.Reflect.on;

@Data
public class DisksInfo {

    private final String raid;
    private final String capacity;
    private final String status;

    public static DisksInfo of(final String info, final String separator) {
        val args = StreamEx.of(info.split(separator))
            .filter(value -> !value.trim().isEmpty())
            .toArray();

        return args.length == CONFIG.disksInfoBlocks() ? on(DisksInfo.class).create(args).get() : empty();
    }

    public static DisksInfo empty() {
        return new DisksInfo("", "", "");
    }
}
