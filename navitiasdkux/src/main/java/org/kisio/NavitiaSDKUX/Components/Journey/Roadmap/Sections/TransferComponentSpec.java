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
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Transfer.Description.WaitingComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Transfer.DescriptionComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.BaseViewComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.ViewComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;

import java.util.HashMap;
import java.util.Map;

@LayoutSpec
public class TransferComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();
    @PropDefault static final Section waitingSection = null;

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section,
        @Prop(optional = true) Section waitingSection) {

        Component<?> waitingComponent;
        if (waitingSection != null) {
            waitingComponent = WaitingComponent.create(c)
                .section(waitingSection)
                .build();
        } else {
            waitingComponent = ViewComponent.create(c)
                .build();
        }

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
                            .color(Configuration.colors.getGray())
                            .section(section)
                            .sectionWay(SectionStopPointType.departure)
                            .build()
                    )
                    .body(
                        ViewComponent.create(c)
                            .children(new Component<?>[]{
                                DescriptionComponent.create(c)
                                    .section(section)
                                    .build(),
                                waitingComponent
                            })
                    )
                    .footer(
                        StopPointComponent.create(c)
                            .color(Configuration.colors.getGray())
                            .section(section)
                            .sectionWay(SectionStopPointType.arrival)
                            .build()
                    )
                    .build()
            });

        return builder.buildWithLayout();
    }
}
