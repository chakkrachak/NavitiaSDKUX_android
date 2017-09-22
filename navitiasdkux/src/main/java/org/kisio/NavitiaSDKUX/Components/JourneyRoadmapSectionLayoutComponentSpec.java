package org.kisio.NavitiaSDKUX.Components;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDKUX.Components.Primitive.HorizontalViewComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;

import java.util.HashMap;
import java.util.Map;

@LayoutSpec
public class JourneyRoadmapSectionLayoutComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop(optional = true) Component<?> firstComponent,
        @Prop(optional = true) Component<?> secondComponent,
        @Prop(optional = true) Component<?> thirdComponent) {

        final ComponentLayout.ContainerBuilder builder = HorizontalViewComponent.create(c).testKey(testKey);

        final ComponentLayout.ContainerBuilder builderFirstComponent = ViewComponent.create(c);
        if (firstComponent != null) {
            builderFirstComponent.child(firstComponent);
        }
        builder.child(StylizedComponent.applyStyles(builderFirstComponent, firstComponentStyles));

        final ComponentLayout.ContainerBuilder builderSecondComponent = ViewComponent.create(c);
        if (secondComponent != null) {
            builderSecondComponent.child(secondComponent);
        }
        builder.child(StylizedComponent.applyStyles(builderSecondComponent, secondComponentStyles));

        final ComponentLayout.ContainerBuilder builderThirdComponent = ViewComponent.create(c);
        if (thirdComponent != null) {
            builderThirdComponent.child(thirdComponent);
        }
        builder.child(StylizedComponent.applyStyles(builderThirdComponent, thirdComponentStyles));

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }

    static Map<String, Object> firstComponentStyles = new HashMap<>();
    static {
        firstComponentStyles.put("width", 50);
    }

    static Map<String, Object> secondComponentStyles = new HashMap<>();
    static {
        secondComponentStyles.put("width", 20);
    }

    static Map<String, Object> thirdComponentStyles = new HashMap<>();
    static {
        thirdComponentStyles.put("flexGrow", 1);
        thirdComponentStyles.put("flexShrink", 1);
    }
}
