package org.kisio.NavitiaSDKUX.Components.Journey.Results.Solution;

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
import org.kisio.NavitiaSDKUX.Components.DurationComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.HorizontalViewComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.BaseViewComponent;
import org.kisio.NavitiaSDKUX.Components.SeparatorComponent;
import org.kisio.NavitiaSDKUX.Components.TextComponent;
import org.kisio.NavitiaSDKUX.Components.ViewComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;
import org.kisio.NavitiaSDKUX.Util.Metrics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@LayoutSpec
public class RowComponentSpec {
    @PropDefault
    static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop String departureTime,
        @Prop String arrivalTime,
        @Prop Integer totalDuration,
        @Prop Integer walkingDuration,
        @Prop Integer walkingDistance,
        @Prop List<Section> sections,
        @Prop Boolean hasArrow) {

        Component walkingSummaryComponent = ViewComponent.create(c).build();
        if (walkingDuration > 0) {
            walkingSummaryComponent = WalkingSummaryComponent.create(c)
                .distance(walkingDistance)
                .duration(walkingDuration)
                .build();
        }

        final ComponentLayout.ContainerBuilder builder = BaseViewComponent.create(c).testKey(testKey);
        builder
            .child(getHeaderComponent(c, departureTime, arrivalTime, totalDuration, hasArrow))
            .child(SeparatorComponent.create(c))
            .child(
                FriezeComponent.create(c)
                    .sections(sections)
            )
            .child(walkingSummaryComponent);
        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }

    static ComponentLayout.ContainerBuilder getHeaderComponent(ComponentContext c, String departureTime, String arrivalTime, Integer totalDuration, Boolean hasArrow) {
        final String timesText = Metrics.timeText(departureTime) + " - " + Metrics.timeText(arrivalTime);
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
                    .hasArrow(hasArrow)
            );

        return StylizedComponent.applyStyles(builder, journeyHeaderStyles);
    }

    static Map<String, Object> journeyHeaderStyles = new HashMap<>();
    static {
        journeyHeaderStyles.put("paddingTop", 16);
        journeyHeaderStyles.put("paddingBottom", 16);
    }

    static Map<String, Object> timesStyles = new HashMap<>();
    static {
        timesStyles.put("color", Configuration.colors.getDarkerGray());
        timesStyles.put("fontWeight", "bold");
    }

    static Map<String, Object> durationStyles = new HashMap<>();
    static {
        durationStyles.put("alignItems", YogaAlign.FLEX_END);
        durationStyles.put("justifyContent", YogaJustify.FLEX_END);
        durationStyles.put("flexGrow", 1);
    }
}