package org.kisio.NavitiaSDKUX.Components.Journey.Results.Solution.Frieze;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.Components.LineCodeComponent;
import org.kisio.NavitiaSDKUX.Components.ModeComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.HorizontalViewComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.BaseViewComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;

import java.util.HashMap;
import java.util.Map;

@LayoutSpec
public class SectionAbstractComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section) {

        final ComponentLayout.ContainerBuilder builder = BaseViewComponent.create(c).testKey(testKey);
        Map<String, Object> containerStyles = new HashMap<>(containerBaseStyles);
        containerStyles.put("flexGrow", section.getDuration());
        builder
            .child(
                getSymbolComponents(c, section)
            )
            .child(
                SectionSegmentComponent.create(c)
                    .section(section)
            );

        final Map<String, Object> computedStyles = StylizedComponent.mergeStyles(containerStyles, styles);
        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, computedStyles);
        return styledBuilder.build();
    }

    static ComponentLayout.ContainerBuilder getSymbolComponents(ComponentContext c, Section section) {
        final ComponentLayout.ContainerBuilder builder = HorizontalViewComponent.create(c);
        builder.child(
            ModeComponent.create(c)
                .section(section)
                .styles(modeStyles)
        ).child(
            LineCodeComponent.create(c)
                .section(section)
        );
        return StylizedComponent.applyStyles(builder, viewStyles);
    }

    static Map<String, Object> containerBaseStyles = new HashMap<>();
    static {
        // containerBaseStyles.put("fontSize", 16);
        containerBaseStyles.put("marginEnd", Configuration.metrics.margin);
    }

    static Map<String, Object> viewStyles = new HashMap<>();
    static {
        viewStyles.put("marginBottom", 12);
    }

    static Map<String, Object> modeStyles = new HashMap<>();
    static {
        modeStyles.put("marginRight", Configuration.metrics.marginS);
        modeStyles.put("height", 28);
    }
}
