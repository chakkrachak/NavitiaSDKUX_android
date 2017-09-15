package org.kisio.NavitiaSDKUX.Components;

import android.graphics.Color;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * NavitiaSDKUX_android
 *
 * Created by Johan Rouve on 28/08/2017.
 * Copyright © 2017 Kisio. All rights reserved.
 */

@LayoutSpec
public class LineCodeComponentSpec {
    @PropDefault
    static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop String code,
        @Prop Integer color) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey);
        builder.child(
            TextComponent.create(c)
                .text(code)
                .styles(textStyles)
        );
        codeStyles.put("backgroundColor", color);
        final Map<String, Object> computedStyles = StylizedComponent.mergeStyles(codeStyles, styles);
        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, computedStyles);
        return styledBuilder.build();
    }

    static Map<String, Object> codeStyles = new HashMap<>();
    static {
        codeStyles.put("padding", 6);
    }

    static Map<String, Object> textStyles = new HashMap<>();
    static {
        textStyles.put("color", Color.WHITE);
        textStyles.put("fontSize", 12);
        textStyles.put("fontWeight", "bold");
    }
}