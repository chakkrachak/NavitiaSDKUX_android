package org.kisio.NavitiaSDKUX.BusinessLogic;

public enum DisruptionLevel {
    blocking (3),
    nonblocking (2),
    information (1),
    none (0);

    private Integer level = 0;

    DisruptionLevel(Integer level) {
        this.level = level;
    }

    public String toString() {
        switch (level) {
            case 3:
                return "blocking";
            case 2:
                return "non-blocking";
            case 1:
                return "information";
            default:
                return "none";
        }
    }

    public String toColor() {
        switch (level) {
            case 3:
                return "A94442";
            case 2:
                return "8A6D3B";
            case 1:
                return "31708F";
            default:
                return "888888";
        }
    }

    public Integer toInteger() {
        return level;
    }
}
