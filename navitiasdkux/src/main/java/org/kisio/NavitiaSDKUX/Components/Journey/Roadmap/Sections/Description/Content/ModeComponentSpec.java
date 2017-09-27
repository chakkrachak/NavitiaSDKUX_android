package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Description.Content;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.yoga.YogaAlign;

import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.BusinessLogic.Modes;
import org.kisio.NavitiaSDKUX.Components.LineCodeComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.HorizontalViewComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.TextComponent;

import java.util.HashMap;
import java.util.Map;

@LayoutSpec
class ModeComponentSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop Section section) {

        final ComponentLayout.ContainerBuilder builder = HorizontalViewComponent.create(c);

        builder
            .child(
                TextComponent.create(c)
                    .text(Modes.getPhysicalMode(section))
                    .styles(modeStyles))
            .child(
                LineCodeComponent.create(c)
                    .section(section));

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, containerStyles);
        return styledBuilder.build();
    }

    static Map<String, Object> containerStyles = new HashMap<>();
    static {
        containerStyles.put("alignItems", YogaAlign.CENTER);
    }

    static Map<String, Object> modeStyles = new HashMap<>();
    static {
        modeStyles.put("fontSize", 15);
        modeStyles.put("marginRight", 5);
    }
}