package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.PublicTransport.Details;

import android.text.TextUtils;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;

import org.kisio.NavitiaSDK.models.StopDateTime;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.LineDiagram.StopPointIconComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.SectionRowLayoutComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.BaseViewComponent;
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
        @Prop Integer color) {

        final ComponentLayout.ContainerBuilder builder = BaseViewComponent.create(c).testKey(testKey);

        builder.child(
            SectionRowLayoutComponent.create(c)
                .styles(containerStyles)
                .secondComponent(
                    StopPointIconComponent.create(c)
                        .color(color)
                        .outerFontSize(12)
                        .innerFontSize(0)
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

    static Map<String, Object> containerStyles = new HashMap<>();
    static {
        containerStyles.put("paddingBottom", 10);
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
