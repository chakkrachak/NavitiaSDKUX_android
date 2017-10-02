package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Diagram;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaPositionType;

import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;
import org.kisio.NavitiaSDKUX.Util.Color;

import java.util.HashMap;
import java.util.Map;

/**
 * NavitiaSDKUX_android
 *
 * Created by Johan Rouve on 27/09/2017.
 * Copyright © 2017 Kisio. All rights reserved.
 */

@LayoutSpec
public class LineDiagramJunctionIconComponentSpec {
    @PropDefault static Boolean hasUpperJunction = false;
    @PropDefault static Boolean hasLowerJunction = false;

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop String color,
        @Prop(optional = true) Boolean hasUpperJunction,
        @Prop(optional = true) Boolean hasLowerJunction) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c);

        Map<String, Object> containerStyles = new HashMap<>(containerBaseStyles);
        if (hasUpperJunction) {
            containerStyles.put("top", 0);
        } else if (hasLowerJunction) {
            containerStyles.put("bottom", 0);
        }

        final ComponentLayout.ContainerBuilder lineBuilder = ViewComponent.create(c);
        Map<String, Object> lineStyles = new HashMap<>(lineBaseStyles);
        lineStyles.put("backgroundColor", Color.getColorFromHexadecimal(color));
        builder.child(
            StylizedComponent.applyStyles(lineBuilder, lineStyles)
        );

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, containerStyles);
        return styledBuilder.build();
    }

    static Map<String, Object> containerBaseStyles = new HashMap<>();
    static {
        containerBaseStyles.put("position", YogaPositionType.ABSOLUTE);
        containerBaseStyles.put("left", 0);
        containerBaseStyles.put("width", 20);
        containerBaseStyles.put("height", 10);
        containerBaseStyles.put("alignItems", YogaAlign.CENTER);
        containerBaseStyles.put("justifyContent", YogaJustify.CENTER);
    }

    static Map<String, Object> lineBaseStyles = new HashMap<>();
    static {
        lineBaseStyles.put("flexGrow", 1);
        lineBaseStyles.put("width", 4);
    }
}
