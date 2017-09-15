package org.kisio.NavitiaSDKUX.Components;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDKUX.Components.Primitive.HorizontalViewComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * NavitiaSDKUX_android
 *
 * Created by Johan Rouve on 24/08/2017.
 * Copyright © 2017 Kisio. All rights reserved.
 */

@LayoutSpec
public class DurationComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();
    @PropDefault static final Integer seconds = 0;

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop(optional = true) Integer seconds) {

        final ComponentLayout.ContainerBuilder builder = HorizontalViewComponent.create(c).testKey(testKey);
        builder
        .child(
            TextComponent.create(c)
                .text(String.valueOf((int) Math.ceil(seconds / 60)))
                .styles(digitsStyles)
        ).child(
            TextComponent.create(c)
                .text("min")
                .styles(abbrStyles)
        ).child(
            IconComponent.create(c)
                .name("arrow-right")
                .styles(arrowStyles)
        );
        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }

    static Map<String, Object> digitsStyles = new HashMap<>();
    static {
        digitsStyles.put("color", Configuration.colors.getTertiary());
        digitsStyles.put("fontWeight", "bold");
        digitsStyles.put("paddingRight", 4);
    }

    static Map<String, Object> abbrStyles = new HashMap<>();
    static {
        abbrStyles.put("color", Configuration.colors.getTertiary());
        abbrStyles.put("paddingRight", 8);
    }

    static Map<String, Object> arrowStyles = new HashMap<>();
    static {
        arrowStyles.put("color", Configuration.colors.getTertiary());
        arrowStyles.put("fontSize", 16);
        arrowStyles.put("marginRight", -4);
    }
}
