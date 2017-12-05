package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.PublicTransport.Description;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.yoga.YogaAlign;

import org.kisio.NavitiaSDK.models.Disruption;
import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.BusinessLogic.DisruptionLevel;
import org.kisio.NavitiaSDKUX.BusinessLogic.DisruptionMatcher;
import org.kisio.NavitiaSDKUX.Components.DisruptionBadgeComponent;
import org.kisio.NavitiaSDKUX.Components.HorizontalContainerComponent;
import org.kisio.NavitiaSDKUX.Components.IconComponent;
import org.kisio.NavitiaSDKUX.Components.TextComponent;
import org.kisio.NavitiaSDKUX.Components.ViewComponent;
import org.kisio.NavitiaSDKUX.Util.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@LayoutSpec
class DisruptionDescriptionComponentSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop Section section,
        @Prop List<Disruption> disruptions) {

        final ViewComponent.Builder builder = ViewComponent.create(c);

        return builder
            .styles(containerStyles)
            .children(getDisruptionComponents(c, disruptions))
            .buildWithLayout();
    }

    private static Component<?>[] getDisruptionComponents(ComponentContext c, List<Disruption> disruptions) {
        List<Component<?>> components = new ArrayList<>();

        for (Disruption disruption: disruptions) {
            components.add(getDisruptionComponent(c, disruption));
        }

        return components.toArray(new Component<?>[components.size()]);
    }

    private static Component<?> getDisruptionComponent(ComponentContext c, Disruption disruption) {
        final ArrayList<Disruption> disruptions = new ArrayList<>();
        disruptions.add(disruption);

        Map<String, Object> causeStyles = new HashMap<>(causeBaseStyles);
        DisruptionLevel disruptionLevel = DisruptionMatcher.getLevel(disruption);
        causeStyles.put("color", Color.getColorFromHexadecimal(disruptionLevel.getLevelColor()));

        return HorizontalContainerComponent.create(c)
            .styles(disruptionContainerStyles)
            .children(new Component<?>[]{
                IconComponent.create(c)
                    .styles(causeStyles)
                    .name(disruptionLevel.getIconName())
                    .build(),
                TextComponent.create(c)
                    .styles(causeStyles)
                    .text(disruption.getCause())
                    .build()
            })
            .build();
    }

    static Map<String, Object> containerStyles = new HashMap<>();
    static {
        containerStyles.put("paddingTop", 24);
    }

    static Map<String, Object> disruptionContainerStyles = new HashMap<>();
    static {
        disruptionContainerStyles.put("alignItems", YogaAlign.CENTER);
    }

    static Map<String, Object> causeBaseStyles = new HashMap<>();
    static {
        causeBaseStyles.put("fontSize", 15);
        causeBaseStyles.put("paddingLeft", 5);
        causeBaseStyles.put("fontWeight", "bold");
    }
}
