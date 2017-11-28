package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Waiting;

import android.graphics.Color;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;

import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaJustify;
import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.Components.IconComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.HorizontalViewComponent;
import org.kisio.NavitiaSDKUX.Components.TextComponent;
import org.kisio.NavitiaSDKUX.Components.ViewComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;
import org.kisio.NavitiaSDKUX.R;
import org.kisio.NavitiaSDKUX.Util.Metrics;

import java.util.HashMap;
import java.util.Map;

@LayoutSpec
class DescriptionComponentSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(
            ComponentContext c,
            @Prop Section section) {

        final ComponentLayout.ContainerBuilder builder = HorizontalViewComponent.create(c);

        builder
            .child(
                ViewComponent.create(c)
                    .styles(iconContainerStyles)
                    .children(new Component<?>[] {
                        IconComponent.create(c)
                            .name("clock")
                            .styles(iconStyles)
                            .build()
                }).build())
            .child(
                TextComponent.create(c)
                    .styles(labelStyles)
                    .text(Metrics.durationText(c, section.getDuration()) + " " + c.getString(R.string.journey_roadmap_action_wait))
                    .build());

        return builder.build();
    }

    static Map<String, Object> iconContainerStyles = new HashMap<>();
    static {
        iconContainerStyles.put("width", 50);
        iconContainerStyles.put("alignItems", YogaAlign.CENTER);
        iconContainerStyles.put("justifyContent", YogaJustify.CENTER);
    }

    static Map<String, Object> iconStyles = new HashMap<>();
    static {
        iconStyles.put("color", Configuration.colors.getGray());
        iconStyles.put("fontSize", 20);
    }

    static Map<String, Object> labelStyles = new HashMap<>();
    static {
        labelStyles.put("fontSize", 15);
        labelStyles.put("color", Configuration.colors.getGray());
        labelStyles.put("padding", 6);
    }
}
