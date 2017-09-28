package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Diagram;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDK.models.StopDateTime;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Description.Details.IntermediateStopPointComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;
import org.kisio.NavitiaSDKUX.Util.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NavitiaSDKUX_android
 *
 * Created by Johan Rouve on 27/09/2017.
 * Copyright © 2017 Kisio. All rights reserved.
 */

@LayoutSpec
public class LineDiagramStopPointIconComponentSpec {
    @PropDefault static Boolean hasUpperJunction = false;
    @PropDefault static Boolean hasLowerJunction = false;
    @PropDefault static Integer outerFontSize = 18;
    @PropDefault static Integer innerFontSize = 12;

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop String color,
        @Prop(optional = true) Boolean hasUpperJunction,
        @Prop(optional = true) Boolean hasLowerJunction,
        @Prop(optional = true) Integer outerFontSize,
        @Prop(optional = true) Integer innerFontSize) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c);

        if (hasUpperJunction) {
            builder.child(
                LineDiagramJunctionIconComponent.create(c)
                    .color(color)
                    .hasUpperJunction(hasUpperJunction)
            );
        }

        if (hasLowerJunction) {
            builder.child(
                LineDiagramJunctionIconComponent.create(c)
                    .color(color)
                    .hasLowerJunction(hasLowerJunction)
            );
        }

        builder.child(
            StopPointIconComponent.create(c)
                .color(color)
                .innerFontSize(innerFontSize)
                .outerFontSize(outerFontSize)
        );

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, containerStyles);
        return styledBuilder.build();
    }

    static Map<String, Object> containerStyles = new HashMap<>();
    static {
        containerStyles.put("width", 20);
        containerStyles.put("height", 20);
    }
}
