package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap;

import android.graphics.Color;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.Components.ContainerComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.DefaultComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.PublicTransportComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.TransferComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.BaseViewComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;

import java.util.HashMap;
import java.util.Map;

@LayoutSpec
public class SectionComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();
    @PropDefault static final Section destinationSection = null;

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section,
        @Prop(optional = true) Section destinationSection) {

        final ComponentLayout.ContainerBuilder builder = BaseViewComponent.create(c).testKey(testKey).child(
            ContainerComponent.create(c).styles(containerStyles).children(new Component<?>[] {
                getTypedSectionComponent(c, section, destinationSection)
            })
        );
        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }

    static Component<?> getTypedSectionComponent(ComponentContext c, Section section, Section destinationSection) {
        switch (section.getType()) {
            case "public_transport":
                return PublicTransportComponent.create(c)
                    .section(section)
                    .build();
            case "transfer":
                return TransferComponent.create(c)
                    .section(section)
                    .waitingSection(destinationSection)
                    .build();
            default:
                return DefaultComponent.create(c)
                    .section(section)
                    .build();
        }
    }

    static Map<String, Object> containerStyles = new HashMap<>();
    static {
        containerStyles.put("backgroundColor", Color.WHITE);
        containerStyles.put("paddingHorizontal", 4);
        containerStyles.put("paddingVertical", 4);
    }
}
