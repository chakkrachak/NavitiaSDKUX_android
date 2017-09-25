package org.kisio.NavitiaSDKUX.Components;

import android.graphics.Color;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;
import com.facebook.yoga.YogaAlign;

import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDK.models.StopDateTime;
import org.kisio.NavitiaSDKUX.Components.Primitive.LabelComponent;
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

        Map<String, Object> innerStyles = new HashMap<>();
        innerStyles.put("backgroundColor", Color.YELLOW);

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, innerStyles);
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

        Map<String, Object> innerStyles = new HashMap<>();
        innerStyles.put("backgroundColor", Color.BLUE);

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, innerStyles);
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

        Map<String, Object> innerStyles = new HashMap<>();
        innerStyles.put("backgroundColor", Color.GREEN);

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, innerStyles);
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
    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey);

        builder.child(ModeComponent.create(c).section(section));

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }
}

@LayoutSpec
class LineDiagramComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop String color) {

        Map<String, Object> innerStyles = new HashMap<>();
        innerStyles.put("backgroundColor", Color.WHITE);
        innerStyles.put("flexGrow", 1);
        innerStyles.put("alignItems", YogaAlign.CENTER);

        Map<String, Object> outerStyles = new HashMap<>();
        outerStyles.put("backgroundColor", org.kisio.NavitiaSDKUX.Util.Color.getColorFromHexadecimal(color));
        outerStyles.put("flexGrow", 1);
        outerStyles.put("width", 4);

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).child(
            ContainerComponent.create(c)
                .testKey(testKey)
                .styles(outerStyles)
                .children(new Component<?>[]{
                    ContainerComponent.create(c)
                        .children(new Component<?>[]{})
                        .styles(innerStyles)
                        .build()
                }));

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
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
    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey);

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }
}

@LayoutSpec
class ContentContainerForDetailsHeaderComponentSpec {
    static Boolean collapsed = true;

    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey).child(
            IconComponent.create(c)
                .name(collapsed ? "arrow-details-down" : "arrow-details-up")
        ).child(
            LabelComponent.create(c)
                .text("component.JourneyRoadmapSectionDescriptionComponent.detailsHeaderTitle")
        );

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
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
