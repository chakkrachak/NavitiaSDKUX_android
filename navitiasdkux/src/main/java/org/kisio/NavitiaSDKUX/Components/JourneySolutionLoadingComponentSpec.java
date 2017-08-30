package org.kisio.NavitiaSDKUX.Components;

import android.graphics.Color;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaJustify;

import org.kisio.NavitiaSDKUX.Components.Primitive.HorizontalViewComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * NavitiaSDKUX_android
 *
 * Created by Johan Rouve on 25/08/2017.
 * Copyright © 2017 Kisio. All rights reserved.
 */

@LayoutSpec
public class JourneySolutionLoadingComponentSpec {
    @PropDefault
    static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) Map<String, Object> styles) {

        final ListRowComponent.Builder builder = ListRowComponent.create(c);
        builder
            .child(
                ShimCardContentComponent.create(c)
            )
            .styles(listStyles);
        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder.withLayout(), styles);
        return styledBuilder.build();
    }

    static Map<String, Object> listStyles = new HashMap<>();
    static {
        listStyles.put("backgroundColor", Color.WHITE);
        listStyles.put("padding", Configuration.metrics.marginL);
        listStyles.put("paddingTop", 9);
        listStyles.put("marginBottom", Configuration.metrics.margin);
    }
}

@LayoutSpec
class ShimCardContentComponentSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c);
        builder
            .child(
                getJourneyHeaderComponent(c)
            )
            .child(
                SeparatorComponent.create(c)
            )
            .child(
                getJourneyFriezeComponent(c)
            )
            .child(
                ShimComponent.create(c)
                    .width(181)
                    .height(17)
            )
        ;
        return builder.build();
    }

    static ComponentLayout.ContainerBuilder getJourneyFriezeComponent(ComponentContext c) {
        final ComponentLayout.ContainerBuilder builder = HorizontalViewComponent.create(c);
        builder
            .child(
                FriezeShimComponent.create(c)
                    .duration(87)
            )
            .child(
                FriezeShimComponent.create(c)
                    .duration(130)
            )
            .child(
                FriezeShimComponent.create(c)
                    .duration(115)
            )
        ;

        return StylizedComponent.applyStyles(builder, journeyFriezeStyle);
    }

    static ComponentLayout.ContainerBuilder getJourneyHeaderComponent(ComponentContext c) {
        final ComponentLayout.ContainerBuilder durationBuilder = ViewComponent.create(c);
        durationBuilder
            .child(
                ShimComponent.create(c)
                    .width(34)
                    .height(26)
            )
            .child(
                ShimComponent.create(c)
                    .width(21)
                    .height(12)
            );


        final ComponentLayout.ContainerBuilder builder = HorizontalViewComponent.create(c);
        builder
            .child(
                ShimComponent.create(c)
                    .width(95)
                    .height(17)
            )
            .child(
                StylizedComponent.applyStyles(durationBuilder, journeyDurationStyles)
            );

        return StylizedComponent.applyStyles(builder, journeyHeaderStyles);
    }

    static Map<String, Object> journeyHeaderStyles = new HashMap<>();
    static {
        journeyHeaderStyles.put("alignItems", YogaAlign.CENTER);
        journeyHeaderStyles.put("justifyContent", YogaJustify.SPACE_BETWEEN);
        journeyHeaderStyles.put("height", 46);
    }

    static Map<String, Object> journeyDurationStyles = new HashMap<>();
    static {
        journeyDurationStyles.put("alignSelf", YogaAlign.STRETCH);
        journeyDurationStyles.put("justifyContent", YogaJustify.SPACE_AROUND);
        journeyDurationStyles.put("alignItems", YogaAlign.FLEX_END);
    }

    static Map<String, Object> journeyFriezeStyle = new HashMap<>();
    static {
        journeyFriezeStyle.put("marginEnd", Configuration.metrics.margin * -1);
        journeyFriezeStyle.put("alignItems", YogaAlign.CENTER);
        journeyFriezeStyle.put("justifyContent", YogaJustify.SPACE_BETWEEN);
        journeyFriezeStyle.put("height", 80);
    }
}

@LayoutSpec
class ShimComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop(optional = true) Integer width,
        @Prop(optional = true) Integer height) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c);

        if (width != null) {
            shimStyles.put("width", width);
        }
        if (height != null) {
            shimStyles.put("height", height);
        }

        final Map<String, Object> computedStyles = StylizedComponent.mergeStyles(shimStyles, styles);
        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, computedStyles);
        return styledBuilder.build();
    }

    static Map<String, Object> shimStyles = new HashMap<>();
    static {
        shimStyles.put("backgroundColor", Configuration.colors.getLighterGray());
    }
}

@LayoutSpec
class FriezeShimComponentSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop Integer duration) {

        final ShimComponent.Builder builder = ShimComponent.create(c);

        shimStyles.put("flexGrow", duration);

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder.withLayout(), shimStyles);
        return styledBuilder.build();
    }

    static Map<String, Object> shimStyles = new HashMap<>();
    static {
        shimStyles.put("height", 45);
        shimStyles.put("marginEnd", Configuration.metrics.margin);
    }
}