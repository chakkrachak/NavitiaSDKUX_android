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
public class LineCodeComponentSpec {
    @PropDefault
    static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey);

        if (section.getDisplayInformations() != null && section.getDisplayInformations().getCode() != null) {
            String code = section.getDisplayInformations().getCode();
            codeStyles.put("backgroundColor", Color.getColorFromHexadecimal(section.getDisplayInformations().getColor()));
            textStyles.put("color", Color.getColorFromHexadecimal(section.getDisplayInformations().getTextColor()));

            builder.child(
                TextComponent.create(c)
                    .text(code)
                    .styles(textStyles)
            );

            final Map<String, Object> computedStyles = StylizedComponent.mergeStyles(codeStyles, styles);
            final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, computedStyles);
            return styledBuilder.build();
        } else {
            return builder.build();
        }
    }

    static Map<String, Object> codeStyles = new HashMap<>();
    static {
        codeStyles.put("padding", 6);
    }

    static Map<String, Object> textStyles = new HashMap<>();
    static {
        textStyles.put("fontSize", 12);
        textStyles.put("fontWeight", "bold");
    }
}