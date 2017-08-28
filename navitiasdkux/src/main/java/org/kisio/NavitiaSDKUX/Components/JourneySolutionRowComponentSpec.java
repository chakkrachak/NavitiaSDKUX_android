package org.kisio.NavitiaSDKUX.Components;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaJustify;

import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.Components.Primitive.HorizontalViewComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NavitiaSDKUX_android
 *
 * Created by Johan Rouve on 28/08/2017.
 * Copyright © 2017 Kisio. All rights reserved.
 */

@LayoutSpec
public class JourneySolutionRowComponentSpec {
    @PropDefault
    static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop String departureTime,
        @Prop String arrivalTime,
        @Prop Integer totalDuration,
        @Prop Integer walkingDuration,
        @Prop Integer walkingDistance,
        @Prop List<Section> sections) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c);
        builder
            .child(getHeaderComponent(c, departureTime, arrivalTime, totalDuration))
            .child(SeparatorComponent.create(c))
            .child(
                JourneyRoadmapFriezeComponent.create(c)
                    .sections(sections)
            )
            .child(
                JourneyWalkingAbstractComponent.create(c)
                    .distance(walkingDistance)
                    .duration(walkingDuration)
            );
        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }

    static ComponentLayout.ContainerBuilder getHeaderComponent(ComponentContext c, String departureTime, String arrivalTime, Integer totalDuration) {
        final String timesText = "10h00 - 20h00";
        final ComponentLayout.ContainerBuilder builder = HorizontalViewComponent.create(c);
        builder
            .child(
                TextComponent.create(c)
                    .text(timesText)
                    .styles(timesStyles)
            )
            .child(
                DurationComponent.create(c)
                    .seconds(totalDuration)
                    .styles(durationStyles)
            );

        return builder;
    }

    static Map<String, Object> timesStyles = new HashMap<>();
    static {
        timesStyles.put("color", Configuration.colors.darkerGray);
        timesStyles.put("fontWeight", "bold");
    }

    static Map<String, Object> durationStyles = new HashMap<>();
    static {
        durationStyles.put("alignItems", YogaAlign.FLEX_END);
        durationStyles.put("justifyContent", YogaJustify.FLEX_END);
        durationStyles.put("flexGrow", 1);
    }
}