package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Description;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDK.models.StopDateTime;
import org.kisio.NavitiaSDKUX.Components.ContainerComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Description.Details.FooterComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Description.Details.HeaderComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Description.Details.IntermediateStopPointComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;

import java.util.HashMap;
import java.util.Map;

@LayoutSpec
class DetailsComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey).child(
            HeaderComponent.create(c)
                .section(section)
        ).child(
            getIntermediateStops(c, section)
        ).child(
            FooterComponent.create(c)
                .section(section)
        );

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }

    static ComponentLayout.ContainerBuilder getIntermediateStops(ComponentContext c, Section section) {
        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c);

        for (StopDateTime stopDateTime : section.getStopDateTimes()) {
            builder.child(
                IntermediateStopPointComponent.create(c)
                    .stopDateTime(stopDateTime)
                    .color(section.getDisplayInformations().getColor())
                    .build()
            );
        }

        return builder;
    }
}
