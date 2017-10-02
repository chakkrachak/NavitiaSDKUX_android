package org.kisio.NavitiaSDKUX.Components.Journey.Roadmap.Sections.Diagram;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;

import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;
import org.kisio.NavitiaSDKUX.Util.Color;

import java.util.HashMap;
import java.util.Map;

/**
 * NavitiaSDKUX_android
 *
 * Created by Johan Rouve on 26/09/2017.
 * Copyright © 2017 Kisio. All rights reserved.
 */

@LayoutSpec
public class SubLineDiagramComponentSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop String color) {

        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c);

        Map<String, Object> lineStyles = new HashMap<>(lineBaseStyles);
        lineStyles.put("backgroundColor", Color.getColorFromHexadecimal(color));

        final ComponentLayout.Builder styledBuilder = StylizedComponent.applyStyles(builder, lineStyles);
        return styledBuilder.build();
    }

    static Map<String, Object> lineBaseStyles = new HashMap<>();
    static {
        lineBaseStyles.put("flexGrow", 1);
        lineBaseStyles.put("width", 4);
    }
}
