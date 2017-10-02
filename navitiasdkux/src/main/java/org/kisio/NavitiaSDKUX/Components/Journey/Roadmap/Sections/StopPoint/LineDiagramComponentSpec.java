package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.StopPoint;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaJustify;

import org.kisio.NavitiaSDKUX.BusinessLogic.SectionStopPointType;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Diagram.EmptySubLineDiagramComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Diagram.LineDiagramStopPointIconComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Diagram.SubLineDiagramComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@LayoutSpec
class LineDiagramComponentSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop String color,
        @Prop SectionStopPointType sectionWay) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c);
        List<Component.Builder> subComponents = new ArrayList<>();
        subComponents.add(
            EmptySubLineDiagramComponent.create(c)
        );
        subComponents.add(
            LineDiagramStopPointIconComponent.create(c)
                .color(color)
                .hasUpperJunction(sectionWay.equals(SectionStopPointType.arrival))
                .hasLowerJunction(sectionWay.equals(SectionStopPointType.departure))
        );
        subComponents.add(
            SubLineDiagramComponent.create(c)
                .color(color)
        );

        if (sectionWay.equals(SectionStopPointType.arrival)) {
            Collections.reverse(subComponents);
        }

        for (int i = 0; i < subComponents.size(); i++) {
            builder.child(subComponents.get(i));
        }

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
