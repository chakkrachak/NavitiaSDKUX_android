package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.BusinessLogic.SectionStopPointType;
import org.kisio.NavitiaSDKUX.Components.ContainerComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.LineDiagram.PlainComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.PublicTransport.DetailsComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.PublicTransport.DescriptionComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;
import org.kisio.NavitiaSDKUX.Util.Color;

import java.util.HashMap;
import java.util.Map;

@LayoutSpec
public class PublicTransportComponentSpec {
    @PropDefault
    static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey).child(
            PlainComponent.create(c)
                .color(Color.getColorFromHexadecimal(section.getDisplayInformations().getColor()))
                .build()
        ).child(
            SectionLayoutComponent.create(c)
                .header(
                    StopPointComponent.create(c)
                        .section(section)
                        .sectionWay(SectionStopPointType.departure)
                )
                .body(
                    ContainerComponent.create(c)
                        .styles(bodyContainerStyles)
                        .children(new Component<?>[] {
                            DescriptionComponent.create(c)
                                .section(section)
                                .build(),
                            DetailsComponent.create(c)
                                .section(section)
                                .build()
                    })
                )
                .footer(
                    StopPointComponent.create(c)
                        .section(section)
                        .sectionWay(SectionStopPointType.arrival)
                )
        );

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }

    static Map<String, Object> bodyContainerStyles = new HashMap<>();
    static {
        bodyContainerStyles.put("paddingVertical", 12);
        bodyContainerStyles.put("paddingHorizontal", 0);
    }
}
