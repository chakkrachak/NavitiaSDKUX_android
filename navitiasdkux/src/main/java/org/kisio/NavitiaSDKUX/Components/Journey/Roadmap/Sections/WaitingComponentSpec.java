package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;
import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.Components.TextComponent;
import org.kisio.NavitiaSDKUX.Components.ViewComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;
import org.kisio.NavitiaSDKUX.R;
import org.kisio.NavitiaSDKUX.Util.Metrics;

import java.util.HashMap;
import java.util.Map;

@LayoutSpec
public class WaitingComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section) {

        final ViewComponent.Builder builder = ViewComponent.create(c)
            .testKey(testKey)
            .styles(containerStyles)
            .children(new Component<?>[]{
                TextComponent.create(c)
                .styles(labelStyles)
                .text(Metrics.durationText(c, section.getDuration()) + " " + c.getString(R.string.journey_roadmap_action_wait))
                .build()
            });

        return builder.buildWithLayout();
    }

    static Map<String, Object> containerStyles = new HashMap<>();
    static Map<String, Object> labelStyles = new HashMap<>();
    static {
        containerStyles.put("paddingHorizontal", 6);
        containerStyles.put("paddingVertical", 16);
        labelStyles.put("fontSize", 15);
        labelStyles.put("color", Configuration.colors.getDarkGray());
    }
}
