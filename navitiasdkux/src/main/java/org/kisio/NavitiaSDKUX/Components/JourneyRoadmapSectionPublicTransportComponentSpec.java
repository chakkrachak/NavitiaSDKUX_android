package org.kisio.NavitiaSDKUX.Components;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.BusinessLogic.SectionStopPointType;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;

import java.util.HashMap;
import java.util.Map;

@LayoutSpec
public class JourneyRoadmapSectionPublicTransportComponentSpec {
    @PropDefault
    static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey).child(
            JourneyRoadmapSectionStopPointComponent.create(c)
                .section(section)
                .sectionWay(SectionStopPointType.departure)
                .build()
        ).child(
            JourneyRoadmapSectionDescriptionComponent.create(c)
                .section(section)
                .build()
        ).child(
            JourneyRoadmapSectionStopPointComponent.create(c)
                .section(section)
                .sectionWay(SectionStopPointType.arrival)
                .build()
        );

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }

    static Map<String, Object> separatorStyles = new HashMap<>();
    static {
        separatorStyles.put("marginBottom", 10);
    }

    static Map<String, Object> typeStyles = new HashMap<>();
    static {
        typeStyles.put("fontWeight", "bold");
    }
}
