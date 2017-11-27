package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Waiting;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;

import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.Components.IconComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.HorizontalViewComponent;
import org.kisio.NavitiaSDKUX.Components.TextComponent;
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
                IconComponent.create(c)
                    .name("clock")
                    .styles(iconStyles)
                    .build())
            .child(
                TextComponent.create(c)
                    .styles(labelStyles)
                    .text(Metrics.durationText(c, section.getDuration()) + " " + c.getString(R.string.journey_roadmap_action_wait))
                    .build()
            );

        return builder.build();
    }

    static Map<String, Object> iconStyles = new HashMap<>();
    static Map<String, Object> labelStyles = new HashMap<>();
    static {
        iconStyles.put("color", Configuration.colors.getDarkGray());
        labelStyles.put("fontSize", 15);
        labelStyles.put("color", Configuration.colors.getDarkGray());
    }
}
