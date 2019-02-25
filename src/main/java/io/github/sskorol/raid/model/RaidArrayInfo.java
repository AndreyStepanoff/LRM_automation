package io.github.sskorol.raid.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class RaidArrayInfo {

    private final String name;
    private final String raid;
    private final String capacity;
    private final String available;
    private final String parityormirror;
    private final String status;

    public String getStatus() {
        return StringUtils.substringBefore(status, "(").trim();
    }

    public int getCompletenessPercentage() {
        return Integer.parseInt(StringUtils.substringBetween(status, "(", "%)"));
    }
}
