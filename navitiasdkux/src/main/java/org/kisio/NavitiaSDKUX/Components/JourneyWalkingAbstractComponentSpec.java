package org.kisio.NavitiaSDKUX.Components;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaJustify;

import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.Components.Primitive.HorizontalViewComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;
import org.kisio.NavitiaSDKUX.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NavitiaSDKUX_android
 *
 * Created by Johan Rouve on 28/08/2017.
 * Copyright © 2017 Kisio. All rights reserved.
 */

@LayoutSpec
public class JourneyWalkingAbstractComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Integer duration,
        @Prop Integer distance) {

        final ComponentLayout.ContainerBuilder builder = HorizontalViewComponent.create(c);
        builder
            .child(
                TextComponent.create(c)
                    .text(c.getString(R.string.component_JourneyWalkingAbstractComponent_With))
            )
            .child(
                TextComponent.create(c)
                    .text(duration.toString())
                    .styles(durationStyles)
            )
            .child(
                TextComponent.create(c)
                    .text(c.getString(R.string.component_JourneyWalkingAbstractComponent_With) + " (" + distance.toString() + ")")
            )
        ;
        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }

    static Map<String, Object> durationStyles = new HashMap<>();
    static {
        durationStyles.put("fontWeight", "bold");
    }
}