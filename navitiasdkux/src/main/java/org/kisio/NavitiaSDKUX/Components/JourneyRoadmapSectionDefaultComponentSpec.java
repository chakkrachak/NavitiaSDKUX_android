package org.kisio.NavitiaSDKUX.Components;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDK.models.Place;
import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;
import org.kisio.NavitiaSDKUX.Util.Metrics;

import java.util.HashMap;
import java.util.Map;

@LayoutSpec
public class JourneyRoadmapSectionDefaultComponentSpec {
    @PropDefault
    static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section) {

        String fromText = "";
        String toText = "";
        if (section.getDepartureDateTime() != null && section.getFrom() != null) {
            fromText = "(" + Metrics.timeText(section.getDepartureDateTime()) + ") : (" + section.getFrom().getName() + ")";
        }
        if (section.getArrivalDateTime() != null && section.getTo() != null) {
            toText = "(" + Metrics.timeText(section.getArrivalDateTime()) + ") : (" + section.getTo().getName() + ")";
        }

        return ViewComponent.create(c).child(
            TextComponent.create(c)
                .styles(typeStyles)
                .text(section.getType())
                .build()
        ).child(
            SeparatorComponent.create(c)
                .styles(separatorStyles)
                .build()
        ).child(
            TextComponent.create(c)
                .text(fromText)
                .build()
        ).child(
            TextComponent.create(c)
                .text(toText)
                .build()
        ).build();
    }

    static Map<String, Object> separatorStyles = new HashMap<>();
    static {
        separatorStyles.put("marginBottom", 10);
    }

    static Map<String, Object> typeStyles = new HashMap<>();
    static {
        typeStyles.put("fontWeight", "bold");
    }
}
