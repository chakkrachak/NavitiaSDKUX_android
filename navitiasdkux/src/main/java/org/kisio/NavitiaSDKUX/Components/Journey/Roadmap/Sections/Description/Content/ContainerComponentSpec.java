package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Description.Content;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;

import org.kisio.NavitiaSDKUX.Components.ContainerComponent;

import java.util.HashMap;
import java.util.Map;

@LayoutSpec
class ContainerComponentSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Component<?>[] children) {

        final ContainerComponent.Builder builder = ContainerComponent.create(c).testKey(testKey)
            .children(children)
            .styles(containerStyles);

        return builder.buildWithLayout();
    }

    static Map<String, Object> containerStyles = new HashMap<>();
    static {
        containerStyles.put("paddingHorizontal", 5);
        containerStyles.put("paddingTop", 14);
        containerStyles.put("paddingBottom", 18);
    }
}
