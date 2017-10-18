package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Transfer.Description;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.BaseViewComponent;
import org.kisio.NavitiaSDKUX.Components.TextComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;
import org.kisio.NavitiaSDKUX.R;
import org.kisio.NavitiaSDKUX.Util.Metrics;

import java.util.HashMap;
import java.util.Map;

@LayoutSpec
public class ModeDistanceLabelComponentSpec {
    @PropDefault
    static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section) {

        final ComponentLayout.ContainerBuilder builder = BaseViewComponent.create(c);
        final String timeLabel = Metrics.durationText(section.getDuration());

        builder
            .child(
                TextComponent.create(c)
                    .text(timeLabel + " " + c.getString(R.string.journey_roadmap_action_walk))
                    .styles(labelStyles));

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }

    static Map<String, Object> labelStyles = new HashMap<>();
    static {
        labelStyles.put("fontSize", 15);
        labelStyles.put("color", Configuration.colors.getDarkGray());
    }
}
