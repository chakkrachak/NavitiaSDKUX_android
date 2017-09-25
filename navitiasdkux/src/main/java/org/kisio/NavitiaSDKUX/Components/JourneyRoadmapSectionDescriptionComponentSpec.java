package org.kisio.NavitiaSDKUX.Components;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaJustify;

import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDK.models.StopDateTime;
import org.kisio.NavitiaSDKUX.BusinessLogic.Modes;
import org.kisio.NavitiaSDKUX.Components.Primitive.HorizontalViewComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@LayoutSpec
public class JourneyRoadmapSectionDescriptionComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey).child(
            DescriptionComponent.create(c)
                .section(section)
        ).child(
            DetailsComponent.create(c)
                .section(section)
        );

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }
}

@LayoutSpec
class DescriptionComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey).child(
            JourneyRoadmapSectionLayoutComponent.create(c)
                .firstComponent(
                    DescriptionModeIconComponent.create(c)
                        .section(section)
                        .build())
                .secondComponent(
                    LineDiagramComponent.create(c)
                        .color(section.getDisplayInformations().getColor())
                        .build())
                .thirdComponent(
                    DescriptionContentComponent.create(c)
                        .section(section)
                        .build())
        );

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }
}

@LayoutSpec
class DetailsComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey).child(
            DetailsHeaderComponent.create(c)
                .section(section)
        ).child(
            ContainerComponent.create(c)
                .children(getIntermediateStops(c, section))
        ).child(
            DetailsFooterComponent.create(c)
                .section(section)
        );

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }

    static Component<?>[] getIntermediateStops(ComponentContext c, Section section) {
        List<Component<?>> components = new ArrayList<>();
        for (StopDateTime stopDateTime : section.getStopDateTimes()) {
            components.add(
                IntermediateStopPointComponent.create(c)
                    .stopDateTime(stopDateTime)
                    .build()
            );
        }
        return components.toArray(new Component<?>[components.size()]);
    }
}

@LayoutSpec
class DetailsHeaderComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey).child(
            JourneyRoadmapSectionLayoutComponent.create(c)
                .firstComponent(
                    ContainerComponent.create(c)
                        .build())
                .secondComponent(
                    LineDiagramComponent.create(c)
                        .color(section.getDisplayInformations().getColor())
                        .build())
                .thirdComponent(
                    ContentContainerForDetailsHeaderComponent.create(c)
                        .build())
        );

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }
}

@LayoutSpec
class DescriptionModeIconComponentSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop Section section) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey);

        builder.child(
            ModeComponent.create(c)
                .section(section)
                .styles(modeStyles)
        );

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, containerStyles);
        return styledBuilder.build();
    }

    static Map<String, Object> containerStyles = new HashMap<>();
    static {
        containerStyles.put("flexGrow", 1);
        containerStyles.put("alignItems", YogaAlign.CENTER);
        containerStyles.put("justifyContent", YogaJustify.CENTER);
    }

    static Map<String, Object> modeStyles = new HashMap<>();
    static {
        modeStyles.put("height", 28);
    }
}

@LayoutSpec
class LineDiagramComponentSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop String color) {

        final ComponentLayout.ContainerBuilder innerBuilder = ViewComponent.create(c);
        innerStyles.put("backgroundColor", org.kisio.NavitiaSDKUX.Util.Color.getColorFromHexadecimal(color));
        final ComponentLayout.Builder innerStyledBuilder = StylizedComponent.applyStyles(innerBuilder, innerStyles);

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c)
            .child(innerStyledBuilder);

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, containerStyles);
        return styledBuilder.build();
    }

    static Map<String, Object> containerStyles = new HashMap<>();
    static {
        containerStyles.put("flexGrow", 1);
        containerStyles.put("alignItems", YogaAlign.CENTER);
    }

    static Map<String, Object> innerStyles = new HashMap<>();
    static {
        innerStyles.put("flexGrow", 1);
        innerStyles.put("width", 4);
    }
}

@LayoutSpec
class LineDiagramForIntermediateStopPointComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey);

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }
}

@LayoutSpec
class DescriptionContentComponentSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop Section section) {

        final ContentContainerComponent.Builder builder = ContentContainerComponent.create(c).testKey(testKey)
            .children(new Component<?>[] {
                DescriptionContentModeComponent.create(c)
                    .section(section)
                    .build(),
                DescriptionContentDirectionComponent.create(c)
                    .section(section)
                    .build()
            });

        return builder.buildWithLayout();
    }
}

@LayoutSpec
class DescriptionContentModeComponentSpec {
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

@LayoutSpec
class DescriptionContentDirectionComponentSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop Section section) {

        final ComponentLayout.ContainerBuilder builder = HorizontalViewComponent.create(c);

        builder
            .child(
                IconComponent.create(c)
                    .name("direction")
                    .styles(iconStyles))
            .child(
                TextComponent.create(c)
                    .text(section.getDisplayInformations().getDirection())
                    .styles(directionStyles));

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, containerStyles);
        return styledBuilder.build();
    }

    static Map<String, Object> containerStyles = new HashMap<>();
    static {
        containerStyles.put("alignItems", YogaAlign.CENTER);
    }

    static Map<String, Object> iconStyles = new HashMap<>();
    static {
        iconStyles.put("fontSize", 12);
        iconStyles.put("marginRight", 5);
    }

    static Map<String, Object> directionStyles = new HashMap<>();
    static {
        directionStyles.put("fontSize", 15);
    }
}

@LayoutSpec
class ContentContainerForDetailsHeaderComponentSpec {
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
        containerStyles.put("padding", 5);
        containerStyles.put("paddingTop", 0);
    }
}

@LayoutSpec
class ContentContainerForIntermediateStopPointComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey);

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }
}

@LayoutSpec
class IntermediateStopPointComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop StopDateTime stopDateTime) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey);

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }
}

@LayoutSpec
class DetailsFooterComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey).child(
            JourneyRoadmapSectionLayoutComponent.create(c)
                .firstComponent(
                    ContainerComponent.create(c)
                        .build())
                .secondComponent(
                    LineDiagramComponent.create(c)
                        .color(section.getDisplayInformations().getColor())
                        .build())
                .thirdComponent(
                    ContentContainerForDetailsHeaderComponent.create(c)
                        .build())
        );

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }
}

@LayoutSpec
class ContentContainerComponentSpec {
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
