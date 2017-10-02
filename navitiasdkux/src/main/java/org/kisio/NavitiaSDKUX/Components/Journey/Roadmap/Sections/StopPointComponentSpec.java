package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.BusinessLogic.SectionStopPointType;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.StopPoint.LineDiagramComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.StopPoint.TimeComponent;
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

        final LayoutComponent.Builder builder = LayoutComponent
            .create(c)
            .testKey(testKey)
            .firstComponent(TimeComponent.create(c)
                .dateTime(sectionWay == SectionStopPointType.departure ? section.getDepartureDateTime() : section.getArrivalDateTime())
                .build())
            .secondComponent(
                LineDiagramComponent.create(c)
                    .color(section.getDisplayInformations().getColor())
                    .sectionWay(sectionWay)
            )
            .thirdComponent(DescriptionComponent.create(c)
                .stopPointLabel(stopPointLabel)
            )
        );
        
        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder.withLayout(), styles);
        return styledBuilder.build();
    }
}
