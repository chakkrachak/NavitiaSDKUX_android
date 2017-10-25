package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.StreetNetwork.Description;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;
import org.kisio.NavitiaSDKUX.Components.TextComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;
import org.kisio.NavitiaSDKUX.R;
import org.kisio.NavitiaSDKUX.Util.Metrics;

import java.util.HashMap;
import java.util.Map;

@LayoutSpec
public class ModeDistanceLabelComponentSpec {
    @PropDefault
    static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Section section) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c);

        builder
            .child(
                TextComponent.create(c)
                    .text(getDistanceLabel(c, section))
                    .styles(labelStyles));

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }

    static Map<String, Object> labelStyles = new HashMap<>();
    static {
        labelStyles.put("fontSize", 15);
        labelStyles.put("color", Configuration.colors.getDarkGray());
    }

    private static String getDistanceLabel(ComponentContext c, Section section) {
        String distanceLabel = Metrics.distanceText(Metrics.sectionLength(section.getPath()));
        switch (section.getMode()) {
            case "walking":
                distanceLabel += " " + c.getString(R.string.component_Journey_Roadmap_Sections_StreetNetwork_Description_mode_walking);
                break;
            case "bike":
                distanceLabel += " " + c.getString(R.string.component_Journey_Roadmap_Sections_StreetNetwork_Description_mode_bike);
                break;
            case "car":
                distanceLabel += " " + c.getString(R.string.component_Journey_Roadmap_Sections_StreetNetwork_Description_mode_car);
                break;
            case "bss":
                final String template = c.getString(R.string.component_Journey_Roadmap_Sections_StreetNetwork_Description_mode_bss);
                distanceLabel += " " + String.format(template, section);
                break;
            default:
                break;
        }
        return distanceLabel;
    }
}
