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
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.LineDiagram.DottedComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.StreetNetwork.DescriptionComponent;
import org.kisio.NavitiaSDKUX.Components.ViewComponent;
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

        final ViewComponent.Builder builder = ViewComponent.create(c)
            .testKey(testKey)
            .styles(styles)
            .children(new Component<?>[]{
                DottedComponent.create(c)
                    .color(Configuration.colors.getGray())
                    .build(),
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
                    .build()
            });

        return builder.buildWithLayout();
    }
}
