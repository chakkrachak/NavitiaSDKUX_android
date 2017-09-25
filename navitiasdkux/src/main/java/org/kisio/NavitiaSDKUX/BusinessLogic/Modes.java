package org.kisio.NavitiaSDKUX.BusinessLogic;

import org.kisio.NavitiaSDK.models.LinkSchema;
import org.kisio.NavitiaSDK.models.Section;

import java.util.List;

/**
 * NavitiaSDKUX_android
 *
 * Created by Johan Rouve on 25/09/2017.
 * Copyright © 2017 Kisio. All rights reserved.
 */

public class Modes {
    public static String getModeIcon(Section section) {
        switch (section.getType()) {
            case "public_transport":
                return getPhysicalMode(section.getLinks());
            case "transfer":
                return section.getTransferType();
            case "waiting":
                return section.getType();
            default:
                return section.getMode();
        }
    }

    static String getPhysicalMode(List<LinkSchema> links) {
        final String id = getPhysicalModeId(links);
        final String[] modeData = id.split(":");
        return (modeData.length > 1) ? modeData[1].toLowerCase() : "";
    }

    private static String getPhysicalModeId(List<LinkSchema> links) {
        for (LinkSchema link : links) {
            if (link.getType().equals("physical_mode")) {
                return link.getId();
            }
        }
        return "<not_found>";
    }
}
