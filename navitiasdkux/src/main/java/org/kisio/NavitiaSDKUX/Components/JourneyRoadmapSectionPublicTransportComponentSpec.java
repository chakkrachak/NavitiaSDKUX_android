package org.kisio.NavitiaSDKUX.Components;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.Components.Primitive.LabelComponent;
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
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section) {

        return ViewComponent.create(c).child(
            LabelComponent.create(c).text(section.getType()).build()
        ).child(
            SeparatorComponent.create(c).build()
        ).child(
            JourneyRoadmapSectionStopPointComponent.create(c)
                .section(section)
                .sectionWay("departure")
                .build()
        ).child(
            JourneyRoadmapSectionDescriptionComponent.create(c)
                .section(section)
                .build()
        ).child(
            JourneyRoadmapSectionStopPointComponent.create(c)
                .section(section)
                .sectionWay("arrival")
                .build()
        ).build();
    }
}
