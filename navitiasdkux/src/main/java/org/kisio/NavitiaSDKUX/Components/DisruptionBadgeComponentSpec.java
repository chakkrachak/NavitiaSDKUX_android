package org.kisio.NavitiaSDKUX.Components;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaPositionType;

import org.kisio.NavitiaSDK.models.Disruption;
import org.kisio.NavitiaSDKUX.Util.Color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@LayoutSpec
public class DisruptionBadgeComponentSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop List<Disruption> disruptions) {

        return ViewComponent.create(c)
            .styles(containerStyles)
            .children(new Component<?>[] {
                IconComponent.create(c)
                    .styles(circleStyles)
                    .name("circle-filled")
                    .build(),
                IconComponent.create(c)
                    .name("disruption-")
                    .build(),
            })
            .buildWithLayout();
    }

    static Map<String, Object> containerStyles = new HashMap<>();
    static {
        containerStyles.put("position", YogaPositionType.ABSOLUTE);
        containerStyles.put("alignItems", YogaAlign.CENTER);
        containerStyles.put("justifyContent", YogaJustify.CENTER);
        containerStyles.put("top", -9);
        containerStyles.put("end", -11);
    }

    static Map<String, Object> circleStyles = new HashMap<>();
    static {
        circleStyles.put("color", Color.getColorFromHexadecimal("FFFFFF"));
        circleStyles.put("fontSize", 18);
    }
}
