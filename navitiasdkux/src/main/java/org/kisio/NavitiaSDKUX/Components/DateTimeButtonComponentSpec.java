package org.kisio.NavitiaSDKUX.Components;


import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;
import com.facebook.litho.widget.Text;

import org.kisio.NavitiaSDKUX.Components.Primitive.ButtonComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.LabelComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;
import org.kisio.NavitiaSDKUX.R;
import org.kisio.NavitiaSDKUX.Util.Metrics;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * NavitiaSDKUX_android
 *
 * Created by Johan Rouve on 23/08/2017.
 * Copyright © 2017 Kisio. All rights reserved.
 */

@LayoutSpec
public class DateTimeButtonComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) Map<String, Object> styles) {

        final ComponentLayout.ContainerBuilder builder = ButtonComponent.create(c);
        builder
            .child(
                DateTimeButtonComponentSpec.getLabelComponent(c)
            );
        final Map<String, Object> computedStyles = StylizedComponent.mergeStyles(buttonStyles, styles);
        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, computedStyles);
        return styledBuilder.build();
    }

    static ComponentLayout.Builder getLabelComponent(ComponentContext c) {
        Text.Builder builder = LabelComponent.create(c)
            .text(c.getString(R.string.component_DateTimeButtonComponent_representation_departure) + " " + Metrics.longDateText(new Date()));

        return StylizedComponent.applyStyles(builder, textStyles);
    }

    static Map<String, Object> buttonStyles = new HashMap<>();
    static {
        buttonStyles.put("paddingTop", Configuration.metrics.marginL);
        buttonStyles.put("paddingLeft", 0);
        buttonStyles.put("paddingRight", 0);
        buttonStyles.put("paddingBottom", Configuration.metrics.margin);
    }

    static Map<String, Object> textStyles = new HashMap<>();
    static {
        textStyles.put("color", Configuration.colors.tertiaryText);
        textStyles.put("fontWeight", "bold");
    }
}
