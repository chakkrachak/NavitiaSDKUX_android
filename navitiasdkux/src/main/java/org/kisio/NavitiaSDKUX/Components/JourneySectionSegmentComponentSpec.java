package org.kisio.NavitiaSDKUX.Components;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;
import org.kisio.NavitiaSDKUX.Util.Color;

import java.util.HashMap;
import java.util.Map;

/**
 * NavitiaSDKUX_android
 *
 * Created by Johan Rouve on 28/08/2017.
 * Copyright © 2017 Kisio. All rights reserved.
 */

@LayoutSpec
public class JourneySectionSegmentComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section) {

        Integer color = Configuration.colors.getDarkerGray();
        if (section.getDisplayInformations() != null) {
            color = Color.getColorFromHexadecimal(section.getDisplayInformations().getColor());
        }

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey);
        containerStyles.put("backgroundColor", color);
        final Map<String, Object> computedStyles = StylizedComponent.mergeStyles(containerStyles, styles);
        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, computedStyles);
        return styledBuilder.build();
    }

    static Map<String, Object> containerStyles = new HashMap<>();
    static {
        containerStyles.put("height", 5);
    }
}