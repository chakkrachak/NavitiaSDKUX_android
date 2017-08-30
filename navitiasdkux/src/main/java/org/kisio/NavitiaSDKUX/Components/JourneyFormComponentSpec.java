package org.kisio.NavitiaSDKUX.Components;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * NavitiaSDKUX_android
 *
 * Created by Johan Rouve on 25/08/2017.
 * Copyright © 2017 Kisio. All rights reserved.
 */

@LayoutSpec
public class JourneyFormComponentSpec {
    @PropDefault
    static final Map<String, Object> styles = new HashMap<>();
    @PropDefault static final String origin = "";
    @PropDefault static final String destination = "";

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop(optional = true) String origin,
        @Prop(optional = true) String destination) {

        final FormComponent.Builder builder = FormComponent.create(c);
        builder
            .children(new Component<?>[]{
                AutocompleteInputComponent.create(c)
                    .icon("origin")
                    .iconColor(Configuration.colors.getOrigin())
                    .placeName(origin)
                    .build(),
                SeparatorComponent.create(c).build(),
                AutocompleteInputComponent.create(c)
                    .icon("destination")
                    .iconColor(Configuration.colors.getDestination())
                    .placeName(destination)
                    .build(),
            });
        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder.withLayout(), styles);
        return styledBuilder.build();
    }
}