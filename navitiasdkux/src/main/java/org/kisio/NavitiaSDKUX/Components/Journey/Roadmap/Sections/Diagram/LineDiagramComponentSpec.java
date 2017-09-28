package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Diagram;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.yoga.YogaAlign;

import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;
import org.kisio.NavitiaSDKUX.Util.Color;

import java.util.HashMap;
import java.util.Map;

@LayoutSpec
class LineDiagramComponentSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop String color) {

        final ComponentLayout.ContainerBuilder innerBuilder = ViewComponent.create(c);
        Map<String, Object> innerStyles = new HashMap<>(innerBaseStyles);
        innerStyles.put("backgroundColor", Color.getColorFromHexadecimal(color));
        final ComponentLayout.Builder innerStyledBuilder = StylizedComponent.applyStyles(innerBuilder, innerStyles);

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c)
            .child(innerStyledBuilder);

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, containerStyles);
        return styledBuilder.build();
    }

    static Map<String, Object> containerStyles = new HashMap<>();
    static {
        containerStyles.put("flexGrow", 1);
        containerStyles.put("alignItems", YogaAlign.CENTER);
    }

    static Map<String, Object> innerBaseStyles = new HashMap<>();
    static {
        innerBaseStyles.put("flexGrow", 1);
        innerBaseStyles.put("width", 4);
    }
}
