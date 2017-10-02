package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Description.Details;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;
import com.facebook.yoga.YogaAlign;

import org.kisio.NavitiaSDK.models.Section;
import org.kisio.NavitiaSDKUX.Components.HorizontalContainerComponent;
import org.kisio.NavitiaSDKUX.Components.IconComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Diagram.LineDiagramComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.LayoutComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;
import org.kisio.NavitiaSDKUX.Components.TextComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;

import java.util.HashMap;
import java.util.Map;

@LayoutSpec
class HeaderComponentSpec {
    @PropDefault static final Map<String, Object> styles = new HashMap<>();

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(optional = true) String testKey,
        @Prop(optional = true) Map<String, Object> styles,
        @Prop Boolean collapsed,
        @Prop Section section) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c).testKey(testKey).child(
            LayoutComponent.create(c)
                .secondComponent(
                    LineDiagramComponent.create(c)
                        .color(section.getDisplayInformations().getColor())
                        .build()
                )
                .thirdComponent(
                    HorizontalContainerComponent.create(c)
                        .styles(detailsHeaderContainerStyle)
                        .children(new Component<?>[] {
                            IconComponent.create(c)
                                .styles(collapserWayIconStyles)
                                .name(collapsed ? "arrow-details-down" : "arrow-details-up")
                                .build(),
                            TextComponent.create(c)
                                .styles(detailsHeaderTitleStyle)
                                .text("Détails")
                                .build()
                        })
                )
        );

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, styles);
        return styledBuilder.build();
    }

    static Map<String, Object> detailsHeaderContainerStyle = new HashMap<>();
    static {
        detailsHeaderContainerStyle.put("paddingHorizontal", 5);
        detailsHeaderContainerStyle.put("marginBottom", 10);
        detailsHeaderContainerStyle.put("alignItems", YogaAlign.CENTER);
    }

    static Map<String, Object> collapserWayIconStyles = new HashMap<>();
    static {
        collapserWayIconStyles.put("color", Configuration.colors.getGray());
        collapserWayIconStyles.put("fontSize", 12);
        collapserWayIconStyles.put("marginRight", 5);
    }

    static Map<String, Object> detailsHeaderTitleStyle = new HashMap<>();
    static {
        detailsHeaderTitleStyle.put("color", Configuration.colors.getGray());
        detailsHeaderTitleStyle.put("fontSize", 13);
        detailsHeaderTitleStyle.put("marginRight", 5);
    }
}
