package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Transfer;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.PublicTransport.Description.ModeIconComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.SectionRowLayoutComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Transfer.Description.ModeDurationLabelComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.BaseViewComponent;

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
        @Prop Section section) {

        final ComponentLayout.ContainerBuilder builder = BaseViewComponent.create(c).testKey(testKey).child(
            SectionRowLayoutComponent.create(c)
                .styles(containerStyles)
                .firstComponent(
                    ModeIconComponent.create(c)
                        .section(section)
                        .build())
                .thirdComponent(
                    ModeDurationLabelComponent.create(c)
                        .section(section)
                        .build())
        );

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }

    static Map<String, Object> containerStyles = new HashMap<>();
    static {
        containerStyles.put("paddingHorizontal", 4);
        containerStyles.put("paddingVertical", 24);
    }
}
