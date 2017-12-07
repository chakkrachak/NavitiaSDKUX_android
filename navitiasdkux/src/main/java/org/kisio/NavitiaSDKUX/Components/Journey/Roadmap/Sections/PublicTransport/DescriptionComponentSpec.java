package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.PublicTransport;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDK.models.Disruption;
import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.Components.ContainerComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.PublicTransport.Description.DirectionComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.PublicTransport.Description.DisruptionDescriptionComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.PublicTransport.Description.ModeIconComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.PublicTransport.Description.ModeLineLabelComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.SectionRowLayoutComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.BaseViewComponent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@LayoutSpec
class DescriptionComponentSpec {
    @PropDefault
    static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section,
        @Prop List<Disruption> disruptions) {

        final ComponentLayout.ContainerBuilder builder = BaseViewComponent.create(c).testKey(testKey).child(
            SectionRowLayoutComponent.create(c)
                .firstComponent(
                    ModeIconComponent.create(c)
                        .section(section)
                        .build())
                .thirdComponent(
                    ContainerComponent.create(c)
                        .styles(containerStyles)
                        .children(new Component<?>[] {
                            ModeLineLabelComponent.create(c)
                                .disruptions(disruptions)
                                .section(section)
                                .build(),
                            DirectionComponent.create(c)
                                .section(section)
                                .build(),
                            DisruptionDescriptionComponent.create(c)
                                .section(section)
                                .disruptions(disruptions)
                                .build()
                        }))
        );

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }

    static Map<String, Object> containerStyles = new HashMap<>();
    static {
        containerStyles.put("paddingHorizontal", 4);
        containerStyles.put("paddingVertical", 12);
    }
}
