package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.StopPoint;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDKUX.Components.ContainerComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;
import org.kisio.NavitiaSDKUX.Components.TextComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;

import java.util.HashMap;
import java.util.Map;

@LayoutSpec
public class DescriptionComponentSpec {
    @PropDefault
    static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop String stopPointLabel) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey).child(
            ContainerComponent.create(c)
                .styles(containerStyles)
                .children(new Component<?>[] {
                    TextComponent.create(c)
                        .styles(labelStyles)
                        .text(stopPointLabel)
                        .build()
                })

        );

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }

    static Map<String, Object> containerStyles = new HashMap<>();
    static {
        containerStyles.put("backgroundColor", Configuration.colors.getLighterGray());
        containerStyles.put("paddingHorizontal", 5);
        containerStyles.put("paddingTop", 14);
        containerStyles.put("paddingBottom", 14);
    }

    static Map<String, Object> labelStyles = new HashMap<>();
    static {
        labelStyles.put("color", Configuration.colors.getDarkText());
        labelStyles.put("fontWeight", "bold");
        labelStyles.put("fontSize", 15);
    }
}
