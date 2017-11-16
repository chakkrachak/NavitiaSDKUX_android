package org.kisio.NavitiaSDKUX.BusinessLogic;

import org.kisio.NavitiaSDK.models.Disruption;

public class DisruptionMatcher {
    public static DisruptionLevel getLevel(Disruption disruption) {
        if (disruption.getSeverity() == null) {
            return DisruptionLevel.none;
        }
        if (disruption.getSeverity().getEffect() == null) {
            return DisruptionLevel.none;
        }
        switch (disruption.getSeverity().getEffect()) {
            case "NO_SERVICE":
                return DisruptionLevel.disrupt;
            case "REDUCED_SERVICE":
            case "STOP_MOVED":
            case "DETOUR":
            case "SIGNIFICANT_DELAYS":
            case "ADDITIONAL_SERVICE":
            case "MODIFIED_SERVICE":
                return DisruptionLevel.warning;
            case "OTHER_EFFECT":
            case "UNKNOWN_EFFECT":
                return DisruptionLevel.information;
            default:
                return DisruptionLevel.none;
        }
    }
}
