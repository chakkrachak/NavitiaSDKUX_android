package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Diagram;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaJustify;

import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;

import java.util.HashMap;
import java.util.Map;

@LayoutSpec
class LineDiagramForIntermediateStopPointComponentSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop String color) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey);

        builder
            .child(
                SubLineDiagramComponent.create(c)
                    .color("0000FF")
            )
            .child(
                LineDiagramStopPointIconComponent.create(c)
                    .color(color)
                    .hasUpperJunction(true)
                    .hasLowerJunction(true)
                    .outerFontSize(12)
                    .innerFontSize(0)
            )
            .child(
                SubLineDiagramComponent.create(c)
                    .color(color)
            );

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, containerStyles);
        return styledBuilder.build();
    }

    static Map<String, Object> containerStyles = new HashMap<>();
    static {
        containerStyles.put("flexGrow", 1);
        containerStyles.put("alignItems", YogaAlign.CENTER);
        containerStyles.put("justifyContent", YogaJustify.CENTER);
    }
}
