package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.LineDiagram;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.yoga.YogaPositionType;

import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;
import org.kisio.NavitiaSDKUX.Util.Color;

import java.util.HashMap;
import java.util.Map;

@LayoutSpec
public class PlainComponentSpec {

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop String color) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey);

        lineStyles.put("backgroundColor", Color.getColorFromHexadecimal(color));

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, lineStyles);
        return styledBuilder.build();
    }

    static Map<String, Object> lineStyles = new HashMap<>();
    static {
        lineStyles.put("position", YogaPositionType.ABSOLUTE);
        lineStyles.put("start", 58);
        lineStyles.put("top", 28);
        lineStyles.put("bottom", 28);
        lineStyles.put("width", 4);
    }
}
