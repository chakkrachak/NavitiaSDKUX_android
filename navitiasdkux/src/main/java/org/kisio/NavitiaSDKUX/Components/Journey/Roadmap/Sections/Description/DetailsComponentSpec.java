package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Description;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.StateValue;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateInitialState;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnUpdateState;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;
import com.facebook.litho.annotations.State;

import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDK.models.StopDateTime;
import org.kisio.NavitiaSDKUX.Components.ActionComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Description.Details.FooterComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Description.Details.HeaderComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Description.Details.IntermediateStopPointComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

@LayoutSpec
class DetailsComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateInitialState
    static void createInitialState(
        ComponentContext c,
        StateValue<Boolean> collapsed) {

        collapsed.set(true);
    }

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        final ComponentContext c,
        @State Boolean collapsed,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section) {

        ComponentLayout.ContainerBuilder intermediateStopsComponent;

        if (collapsed) {
            intermediateStopsComponent = ViewComponent.create(c);
        } else {
            intermediateStopsComponent = getIntermediateStops(c, section);
        }

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey).child(
            ActionComponent.create(c).actionToCall(new Callable<Void>() { public Void call() {
                DetailsComponent.updateCollapsedAsync(c);
                return null;
            }}).child(
                HeaderComponent.create(c)
                    .section(section)
                    .collapsed(collapsed)
            )
        ).child(
            intermediateStopsComponent
        ).child(
            FooterComponent.create(c)
                .section(section)
        );

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }

    @OnUpdateState
    static void updateCollapsed(StateValue<Boolean> collapsed) {
        collapsed.set(!collapsed.get());
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
