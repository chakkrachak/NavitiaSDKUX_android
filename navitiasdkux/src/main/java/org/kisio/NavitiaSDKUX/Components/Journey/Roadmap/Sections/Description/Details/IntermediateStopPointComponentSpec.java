package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Description.Details;

import android.text.TextUtils;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDK.models.StopDateTime;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Diagram.LineDiagramForIntermediateStopPointComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.LayoutComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;
import org.kisio.NavitiaSDKUX.Components.TextComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;

import java.util.HashMap;
import java.util.Map;

@LayoutSpec
class IntermediateStopPointComponentSpec {
    @PropDefault
    static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop StopDateTime stopDateTime,
        @Prop String color) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey);

        builder.child(
            LayoutComponent.create(c)
                .secondComponent(
                    LineDiagramForIntermediateStopPointComponent.create(c)
                        .color(color)
                        .build()
                )
                .thirdComponent(
                    TextComponent.create(c)
                        .styles(stopPointLabelStyles)
                        .text(stopDateTime.getStopPoint().getLabel())
                )
        );

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }

    static Map<String, Object> stopPointLabelStyles = new HashMap<>();
    static {
        stopPointLabelStyles.put("fontSize", 12);
        stopPointLabelStyles.put("fontWeight", "bold");
        stopPointLabelStyles.put("color", Configuration.colors.getDarkerGray());
        stopPointLabelStyles.put("paddingLeft", 5);
        stopPointLabelStyles.put("paddingRight", 10);
        stopPointLabelStyles.put("maxLines", 1);
        stopPointLabelStyles.put("ellipsis", TextUtils.TruncateAt.END);
    }
}
