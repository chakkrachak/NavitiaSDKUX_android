package org.kisio.NavitiaSDKUX.Config;

import java.util.HashMap;
import java.util.Map;

/**
 * NavitiaSDKUX_android
 *
 * Created by Johan Rouve on 24/08/2017.
 * Copyright © 2017 Kisio. All rights reserved.
 */

public class Configuration {
    public static class metrics {
        public static Integer space = 4;
        public static Integer radius = 5;
        public static Integer marginS = 4;
        public static Integer margin = 8;
        public static Integer marginL = 16;
        public static String timeFormat = "HH:mm";
        public static String shortDateFormat = "dd/MM/YYYY";
        public static String longDateFormat = "EEE d MMM - HH:mm";
    }

    public static class colors {
        public static Integer primary = 0xff666666;
        public static Integer primaryText = 0xffffffff;
        public static Integer secondary = 0xfff1f1f1;
        public static Integer secondaryText = 0xffffffff;
        public static Integer tertiary = 0xff009ee0;
        public static Integer tertiaryText = 0xffffffff;
        public static Integer brightText = 0xffffffff;
        public static Integer darkText = 0xff000000;
        public static Integer white = 0xffffffff;
        public static Integer lighterGray = 0xfff1f1f1;
        public static Integer lightGray = 0xffcdcdcd;
        public static Integer gray = 0xff9a9a9a;
        public static Integer darkGray = 0xff404040;
        public static Integer darkerGray = 0xff202020;
        public static Integer origin = 0xff99be27;
        public static Integer destination = 0xffe0137b;
    }

    public static Map<String, String> iconFontCodes = new HashMap<>();
    {
        iconFontCodes.put("address", "\uea02");
        iconFontCodes.put("administrative-region", "\uea03");
        iconFontCodes.put("air", "\uea04");
        iconFontCodes.put("arrow-details-down", "\uea05");
        iconFontCodes.put("arrow-details-up", "\uea06");
        iconFontCodes.put("arrow-direction-left", "\uea07");
        iconFontCodes.put("arrow-direction-right", "\uea08");
        iconFontCodes.put("arrow-direction-straight", "\uea09");
        iconFontCodes.put("arrow-left-long", "\uea0a");
        iconFontCodes.put("arrow-right-long", "\uea0b");
        iconFontCodes.put("arrow-right", "\uea0c");
        iconFontCodes.put("bike", "\uea0d");
        iconFontCodes.put("ferry", "\uea0e");
        iconFontCodes.put("bss", "\uea0f");
        iconFontCodes.put("bus", "\uea10");
        iconFontCodes.put("calendar", "\uea11");
        iconFontCodes.put("car", "\uea12");
        iconFontCodes.put("clock", "\uea13");
        iconFontCodes.put("coach", "\uea14");
        iconFontCodes.put("destination", "\uea15");
        iconFontCodes.put("direction", "\uea16");
        iconFontCodes.put("edit", "\uea17");
        iconFontCodes.put("funicular", "\uea18");
        iconFontCodes.put("geolocation", "\uea19");
        iconFontCodes.put("home", "\uea1a");
        iconFontCodes.put("metro", "\uea1b");
        iconFontCodes.put("notice", "\uea1c");
        iconFontCodes.put("origin", "\uea1d");
        // iconFontCodes.put("origin", "\uffff");
        iconFontCodes.put("poi", "\uea1e");
        iconFontCodes.put("realtime", "\uea1f");
        iconFontCodes.put("stop", "\uea21");
        iconFontCodes.put("localtrain", "\uea23");
        iconFontCodes.put("rapidtransit", "\uea23");
        iconFontCodes.put("longdistancetrain", "\uea23");
        iconFontCodes.put("tramway", "\uea24");
        iconFontCodes.put("walking", "\uea25");
        iconFontCodes.put("work", "\uea26");
    }
}
