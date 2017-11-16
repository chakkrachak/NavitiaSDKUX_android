package org.kisio.NavitiaSDKUX.BusinessLogic;

public enum DisruptionLevel {
    disrupt ("blocking"),
    warning ("non-blocking"),
    information ("information"),
    none ("none");

    private String level = "";

    DisruptionLevel(String level) {
        this.level = level;
    }

    public String toString() {
        return level;
    }
}
