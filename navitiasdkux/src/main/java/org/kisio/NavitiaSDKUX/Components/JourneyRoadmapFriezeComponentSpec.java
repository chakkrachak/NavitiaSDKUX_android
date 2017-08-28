package org.kisio.NavitiaSDKUX.Components;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDK.models.LinkSchema;
import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.Components.Primitive.HorizontalViewComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;
import org.kisio.NavitiaSDKUX.Util.Color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NavitiaSDKUX_android
 *
 * Created by Johan Rouve on 25/08/2017.
 * Copyright © 2017 Kisio. All rights reserved.
 */

@LayoutSpec
public class JourneyRoadmapFriezeComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop List<Section> sections) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c);
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

    static JourneySectionAbstractComponent.Builder getSectionComponents(ComponentContext c, Section section) {
        final JourneySectionAbstractComponent.Builder builder = JourneySectionAbstractComponent.create(c)
            .modeIcon(getModeIcon(section))
            .duration(section.getDuration());

        if (section.getDisplayInformations() != null) {
            builder
                .lineCode(section.getDisplayInformations().getCode())
                .color(Color.getColorFromHexadecimal(section.getDisplayInformations().getColor()));
        }

        return builder;
    }

    static Map<String, Object> modeListStyles = new HashMap<>();
    static {
        modeListStyles.put("paddingTop", Configuration.metrics.marginL);
        modeListStyles.put("paddingBottom", Configuration.metrics.marginL);
        modeListStyles.put("flexGrow", 1);
        modeListStyles.put("marginEnd", Configuration.metrics.margin * -1);
    }

    static String getModeIcon(Section section) {
        switch (section.getType()) {
            case "public_transport":
                return getPhysicalMode(section.getLinks());
            case "transfer":
                return section.getTransferType();
            case "waiting":
                return section.getType();
            default:
                return section.getMode();
        }
    }

    static String getPhysicalMode(List<LinkSchema> links) {
        final String id = getPhysicalModeId(links);
        final String[] modeData = id.split(":");
        return (modeData.length > 1) ? modeData[1].toLowerCase() : "";
    }

    private static String getPhysicalModeId(List<LinkSchema> links) {
        for (LinkSchema link : links) {
            if (link.getType().equals("physical_mode")) {
                return link.getId();
            }
        }
        return "<not_found>";
    }
}