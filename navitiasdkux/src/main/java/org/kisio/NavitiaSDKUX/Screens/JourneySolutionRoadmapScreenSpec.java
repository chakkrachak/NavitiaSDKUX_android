package org.kisio.NavitiaSDKUX.Screens;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;

import org.kisio.NavitiaSDK.models.Journey;
import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.Components.ContainerComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Results.SolutionComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.SectionComponent;
import org.kisio.NavitiaSDKUX.Components.ListRowComponent;
import org.kisio.NavitiaSDKUX.Components.ListViewComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.BaseViewComponent;
import org.kisio.NavitiaSDKUX.Components.ScrollViewComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@LayoutSpec
public class JourneySolutionRoadmapScreenSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop Journey journey) {

        return BaseViewComponent.create(c).testKey("roadmap").child(
            ContainerComponent.create(c)
                .styles(headerStyles)
                .children(new Component<?>[]{})
                .build()
        ).child(
            ContainerComponent.create(c)
                .styles(summaryStyles)
                .testKey("summary")
                .children(new Component<?>[]{
                    SolutionComponent.create(c)
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

        int index = 0;
        for (Section section : journey.getSections()) {
            if (!section.getType().contains("waiting") && !section.getType().contains("crow_fly")) {
                SectionComponent.Builder sectionComponentBuilder = SectionComponent.create(c)
                    .key("journey_roadmap_section_" + index)
                    .section(section);
                if (section.getType().contains("transfer")) {
                    sectionComponentBuilder.destinationSection(journey.getSections().get(index + 1));
                }
                components.add(ListRowComponent.create(c).child(
                    sectionComponentBuilder.build()
                ).build());
            }
            index++;
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
