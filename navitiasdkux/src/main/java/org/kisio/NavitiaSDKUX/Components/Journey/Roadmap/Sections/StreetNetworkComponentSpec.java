package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.BusinessLogic.SectionStopPointType;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.LineDiagram.DottedComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.StreetNetwork.DescriptionComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.BaseViewComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;

import java.util.HashMap;
import java.util.Map;

@LayoutSpec
public class StreetNetworkComponentSpec {
    @PropDefault
    static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section,
        @Prop String description) {

        final ComponentLayout.ContainerBuilder builder = BaseViewComponent.create(c).testKey(testKey).child(
            DottedComponent.create(c)
                .color(Configuration.colors.getGray())
                .build()
        ).child(
            SectionLayoutComponent.create(c)
                .header(
                    StopPointComponent.create(c)
                        .section(section)
                        .color(Configuration.colors.getGray())
                        .sectionWay(SectionStopPointType.departure)
                        .build()
                )
                .body(
                    DescriptionComponent.create(c)
                        .section(section)
                        .description(description)
                        .build()
                )
                .footer(
                    StopPointComponent.create(c)
                        .section(section)
                        .color(Configuration.colors.getGray())
                        .sectionWay(SectionStopPointType.arrival)
                        .build()
                )
        );

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }
}
