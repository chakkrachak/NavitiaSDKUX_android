package org.kisio.NavitiaSDKUX.Components;

import android.graphics.Color;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.StateValue;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateInitialState;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;
import com.facebook.litho.annotations.State;

import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;

import java.util.HashMap;
import java.util.Map;

@LayoutSpec
public class JourneyRoadmapSectionComponentSpec {
    @PropDefault
    static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section) {

        return ViewComponent.create(c).child(
            ContainerComponent.create(c).styles(containerStyles).children(new Component<?>[] {
                getTypedSectionComponent(c, section)
            })
        ).build();
    }

    static Component<?> getTypedSectionComponent(ComponentContext c, Section section) {
        switch (section.getType()) {
            case "public_transport":
                return JourneyRoadmapSectionPublicTransportComponent.create(c).section(section).build();
            default:
                return JourneyRoadmapSectionDefaultComponent.create(c).section(section).build();
        }
    }

    static Map<String, Object> containerStyles = new HashMap<>();
    static {
        containerStyles.put("backgroundColor", Color.WHITE);
        containerStyles.put("padding", Configuration.metrics.marginL);
        containerStyles.put("paddingTop", 4);
        containerStyles.put("marginBottom", Configuration.metrics.margin);
    }
}
