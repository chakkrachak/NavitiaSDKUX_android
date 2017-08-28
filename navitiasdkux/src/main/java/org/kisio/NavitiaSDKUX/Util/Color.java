package org.kisio.NavitiaSDKUX.Util;

/**
 * NavitiaSDKUX_android
 *
 * Created by Johan Rouve on 28/08/2017.
 * Copyright © 2017 Kisio. All rights reserved.
 */

public class Color {
    public static Integer getColorFromHexadecimal(String hex) {
        return android.graphics.Color.parseColor("#" + hex);
    }
}
