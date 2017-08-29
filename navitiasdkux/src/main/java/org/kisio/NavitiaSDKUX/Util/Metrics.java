package org.kisio.NavitiaSDKUX.Util;

import org.kisio.NavitiaSDKUX.Config.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static String longDateText(Date datetime) {
        String pattern = Configuration.metrics.longDateFormat;

        return new SimpleDateFormat(pattern).format(datetime);
    }

    public static String getIsoDatetime(Date datetime) {
        String pattern = "yyyyMMdd'T'HHmmss";

        return new SimpleDateFormat(pattern).format(datetime);
    }

    public static String distanceText(Integer meters) {
        if (meters < 1000) {
            return meters + "m";
        } else {
            return String.valueOf(meters / 1000) + " km";
        }
    }
}
