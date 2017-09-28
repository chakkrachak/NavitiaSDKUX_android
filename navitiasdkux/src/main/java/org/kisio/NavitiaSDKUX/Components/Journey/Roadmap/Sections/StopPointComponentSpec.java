package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.BusinessLogic.SectionStopPointType;
import org.kisio.NavitiaSDKUX.Components.ContainerComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.StopPoint.*;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.StopPoint.DescriptionComponent;

import java.util.HashMap;
import java.util.Map;

@LayoutSpec
public class StopPointComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section,
        @Prop SectionStopPointType sectionWay) {

        String stopPointLabel;
        if (sectionWay == SectionStopPointType.departure) {
            stopPointLabel = section.getFrom().getName();
        } else {
            stopPointLabel = section.getTo().getName();
        }

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey).child(
            LayoutComponent.create(c)
                .firstComponent(TimeComponent.create(c)
                    .dateTime(sectionWay == SectionStopPointType.departure ? section.getDepartureDateTime() : section.getArrivalDateTime())
                    .build())
                .secondComponent(ContainerComponent.create(c))
                .thirdComponent(DescriptionComponent.create(c)
                    .stopPointLabel(stopPointLabel)
                )
        );
        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }
}