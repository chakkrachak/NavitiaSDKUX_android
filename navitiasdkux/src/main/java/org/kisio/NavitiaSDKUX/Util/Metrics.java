package org.kisio.NavitiaSDKUX.Util;

import com.facebook.litho.ComponentContext;

import org.kisio.NavitiaSDKUX.Config.Configuration;
import org.kisio.NavitiaSDKUX.R;

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

    public static String distanceText(ComponentContext c, Integer meters) {
        if (meters < 1000) {
            return meters + " " + c.getString(R.string.units_meter_plural);
        } else {
            return String.valueOf(meters / 1000) + " " + c.getString(R.string.units_kilometer_abbr);
        }
    }

    public static String durationText(ComponentContext c, Integer seconds) {
        if (seconds < 60) {
            return "< 1 " + c.getString(R.string.units_minute);
        } else if (seconds < 3600) {
            Integer minutes = seconds / 60;
            return String.valueOf(minutes) + " " + c.getString(R.string.units_minute_plural);
        } else {
            Integer hours = seconds / 3600;
            Integer remainingMinutes = (seconds / 60) - (hours * 60);
            String minutes = String.valueOf(remainingMinutes);
            if (remainingMinutes < 10) {
                minutes = "0" + String.valueOf(remainingMinutes);
            }
            return String.valueOf(hours) + c.getString(R.string.units_hour_abbr) + minutes;
        }
    }
}
