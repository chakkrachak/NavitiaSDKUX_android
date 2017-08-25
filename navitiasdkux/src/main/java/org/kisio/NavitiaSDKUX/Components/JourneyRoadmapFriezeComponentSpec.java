package org.kisio.NavitiaSDKUX.Components;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDKUX.Components.Primitive.HorizontalViewComponent;
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
public class JourneyRoadmapFriezeComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) Map<String, Object> styles) {

        final ComponentLayout.ContainerBuilder builder = HorizontalViewComponent.create(c);
        builder
            .child(
                SeparatorComponent.create(c)
            )
            .child(
                JourneyRoadmapFriezeComponentSpec.getSectionsComponent(c)
            );

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }

    static ComponentLayout.Builder getSectionsComponent(ComponentContext c) {
        final ComponentLayout.ContainerBuilder builder = HorizontalViewComponent.create(c);
        /*
        builder
            .child(
                AutocompleteInputComponentSpec.getIconComponent(c, icon, iconColor)
            )
            .child(
                AutocompleteInputComponentSpec.getLabelComponent(c, placeName)
            );
        */
        final Map<String, Object> computedStyles = StylizedComponent.mergeStyles(modeListStyles, styles);
        return StylizedComponent.applyStyles(builder, computedStyles);
    }

    static Map<String, Object> modeListStyles = new HashMap<>();
    static {
        modeListStyles.put("paddingTop", Configuration.metrics.marginL);
        modeListStyles.put("paddingBottom", Configuration.metrics.marginL);
        modeListStyles.put("flexGrow", 1);
        modeListStyles.put("marginEnd", Configuration.metrics.margin * -1);
    }
}