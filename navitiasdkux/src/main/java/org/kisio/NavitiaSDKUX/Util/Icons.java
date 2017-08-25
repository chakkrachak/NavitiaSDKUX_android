package org.kisio.NavitiaSDKUX.Util;

import org.kisio.NavitiaSDKUX.Config.Configuration;

/**
 * NavitiaSDKUX_android
 *
 * Created by Johan Rouve on 24/08/2017.
 * Copyright © 2017 Kisio. All rights reserved.
 */

public class Icons {
    public static String fontString(String name) {
        if (Configuration.iconFontCodes.containsKey(name)) {
            return Configuration.iconFontCodes.get(name);
        } else {
            return "\uffff";
        }
    }
}
