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
import org.kisio.NavitiaSDKUX.Util.Metrics;

import java.util.HashMap;
import java.util.Map;

@LayoutSpec
public class JourneyRoadmapSectionStopPointComponentSpec {
    @PropDefault
    static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section,
        @Prop String sectionWay) {

        String pointText = "";
        if (sectionWay == "departure") {
            if (section.getDepartureDateTime() != null && section.getFrom() != null) {
                pointText = "(" + Metrics.timeText(section.getDepartureDateTime()) + ") : (" + section.getFrom().getName() + ")";
            }
        } else if (sectionWay == "arrival") {
            if (section.getArrivalDateTime() != null && section.getTo() != null) {
                pointText = "(" + Metrics.timeText(section.getArrivalDateTime()) + ") : (" + section.getTo().getName() + ")";
            }
        }

        return ViewComponent.create(c).child(
            LabelComponent.create(c)
                .text(pointText)
                .build()
        ).build();
    }
}
