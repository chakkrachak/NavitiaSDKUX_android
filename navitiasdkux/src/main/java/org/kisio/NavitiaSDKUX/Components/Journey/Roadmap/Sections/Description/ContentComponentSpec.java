package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Description;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;

import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Description.Content.ContainerComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Description.Content.ModeComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Description.Content.DirectionComponent;

@LayoutSpec
class ContentComponentSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop Section section) {

        final ContainerComponent.Builder builder = ContainerComponent.create(c).testKey(testKey)
            .children(new Component<?>[] {
                ModeComponent.create(c)
                    .section(section)
                    .build(),
                DirectionComponent.create(c)
                    .section(section)
                    .build()
            });

        return builder.buildWithLayout();
    }
}