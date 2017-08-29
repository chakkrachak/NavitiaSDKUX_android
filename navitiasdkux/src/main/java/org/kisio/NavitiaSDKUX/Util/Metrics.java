package org.kisio.NavitiaSDKUX.Util;

/**
 * NavitiaSDKUX_android
 *
 * Created by Johan Rouve on 29/08/2017.
 * Copyright © 2017 Kisio. All rights reserved.
 */

public class Metrics {
    public static String timeText(String isoString) {
        final String[] timeData = isoString.split("T");
        final String hours = timeData[1].substring(0, 2);
        final String minutes = timeData[1].substring(2, 4);

        return hours + ":" + minutes;
    }

    public static String distanceText(Integer meters) {
        if (meters < 1000) {
            return meters + "m";
        } else {
            return String.valueOf(meters / 1000) + " km";
        }
    }
}
