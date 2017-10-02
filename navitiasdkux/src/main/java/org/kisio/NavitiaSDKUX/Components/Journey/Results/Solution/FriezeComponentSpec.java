package org.kisio.NavitiaSDKUX.Components.Journey.Results.Solution;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.Components.Journey.Results.Solution.Frieze.SectionAbstractComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.HorizontalViewComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;
import org.kisio.NavitiaSDKUX.Components.SeparatorComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@LayoutSpec
public class FriezeComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop List<Section> sections) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey);
        builder
            .child(
                SeparatorComponent.create(c)
            )
            .child(
                getSectionsComponent(c, sections)
            );

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }

    static ComponentLayout.Builder getSectionsComponent(ComponentContext c, List<Section> sections) {
        final ComponentLayout.ContainerBuilder builder = HorizontalViewComponent.create(c);
        for (Section section : sections) {
            if (section.getType().equals("public_transport") || section.getType().equals("street_network")) {
                builder.child(getSectionComponents(c, section));
            }
        }
        final Map<String, Object> computedStyles = StylizedComponent.mergeStyles(modeListStyles, styles);
        return StylizedComponent.applyStyles(builder, computedStyles);
    }

    static SectionAbstractComponent.Builder getSectionComponents(ComponentContext c, Section section) {
        return SectionAbstractComponent.create(c)
            .section(section);
    }

    static Map<String, Object> modeListStyles = new HashMap<>();
    static {
        modeListStyles.put("paddingTop", Configuration.metrics.marginL);
        modeListStyles.put("paddingBottom", Configuration.metrics.marginL);
        modeListStyles.put("flexGrow", 1);
        modeListStyles.put("marginEnd", Configuration.metrics.margin * -1);
    }
}