package org.kisio.NavitiaSDKUX.Screens;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.StateValue;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateInitialState;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.State;

import org.kisio.NavitiaSDK.models.Journey;
import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.Components.ContainerComponent;
import org.kisio.NavitiaSDKUX.Components.JourneyRoadmapSectionComponent;
import org.kisio.NavitiaSDKUX.Components.JourneySolutionComponent;
import org.kisio.NavitiaSDKUX.Components.ListRowComponent;
import org.kisio.NavitiaSDKUX.Components.ListViewComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;
import org.kisio.NavitiaSDKUX.Components.ScrollViewComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NavitiaSDKUX_android
 *
 * Created by Thomas Noury on 05/09/2017.
 * Copyright © 2017 Kisio. All rights reserved.
 */

@LayoutSpec
public class JourneySolutionRoadmapScreenSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop Journey journey) {

        return ViewComponent.create(c).testKey("roadmap").child(
            ContainerComponent.create(c)
                .styles(headerStyles)
                .children(new Component<?>[]{})
                .build()
        ).child(
            ContainerComponent.create(c)
                .styles(summaryStyles)
                .testKey("summary")
                .children(new Component<?>[]{
                    JourneySolutionComponent.create(c)
                        .journey(journey)
                        .isTouchable(false)
                        .build()})
                .build()
        ).child(
            ScrollViewComponent.create(c).child(ListViewComponent.create(c).children(
                getJourneySectionComponents(c, journey)
            ).build())
        ).build();
    }

    static Component<?>[] getJourneySectionComponents(ComponentContext c, Journey journey) {
        List<Component<?>> components = new ArrayList<>();

        for (Section section : journey.getSections()) {
            components.add(ListRowComponent.create(c).child(
                JourneyRoadmapSectionComponent.create(c)
                    .section(section)
                    .build()
            ).build());
        }

        return components.toArray(new Component<?>[components.size()]);
    }

    static Map<String, Object> headerStyles = new HashMap<>();
    static {
        headerStyles.put("backgroundColor", Configuration.colors.getTertiary());
        headerStyles.put("paddingTop", 40);
    }

    static Map<String, Object> summaryStyles = new HashMap<>();
    static {
        summaryStyles.put("marginTop", -60);
    }
}
