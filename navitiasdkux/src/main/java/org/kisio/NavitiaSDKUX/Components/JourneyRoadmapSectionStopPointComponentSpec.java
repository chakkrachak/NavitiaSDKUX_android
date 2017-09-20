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
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section,
        @Prop SectionStopPointType sectionWay) {

        String pointText = "";
        if (sectionWay == SectionStopPointType.departure) {
            if (section.getDepartureDateTime() != null && section.getFrom() != null) {
                pointText = Metrics.timeText(section.getDepartureDateTime()) + " : " + section.getFrom().getName();
            }
        } else if (sectionWay == SectionStopPointType.arrival) {
            if (section.getArrivalDateTime() != null && section.getTo() != null) {
                pointText = Metrics.timeText(section.getArrivalDateTime()) + " : " + section.getTo().getName();
            }
        }

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey).child(
            TextComponent.create(c)
                .text(pointText)
                .build()
        );
        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }
}