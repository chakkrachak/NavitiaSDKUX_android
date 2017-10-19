package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Transfer.Description;

import android.graphics.Bitmap;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.SectionRowLayoutComponent;
import org.kisio.NavitiaSDKUX.Components.TextComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;
import org.kisio.NavitiaSDKUX.R;
import org.kisio.NavitiaSDKUX.Util.Metrics;

import java.util.HashMap;
import java.util.Map;

@LayoutSpec
public class WaitingComponentSpec {
    @PropDefault
    static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section) {

        return SectionRowLayoutComponent.create(c).thirdComponent(
            TextComponent.create(c)
                .styles(containerStyles)
                .text(Metrics.durationText(c, section.getDuration()) + " " + c.getString(R.string.journey_roadmap_action_wait))
                .build()
        ).buildWithLayout();
    }

    static Map<String, Object> containerStyles = new HashMap<>();
    static {
        containerStyles.put("fontSize", 12);
        containerStyles.put("backgroundColor", Configuration.colors.getLighterGray());
        containerStyles.put("paddingHorizontal", 5);
        containerStyles.put("paddingTop", 14);
        containerStyles.put("marginBottom", 0);
    }
}
