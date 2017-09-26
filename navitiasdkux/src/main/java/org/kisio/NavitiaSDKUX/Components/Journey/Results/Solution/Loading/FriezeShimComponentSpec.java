package org.kisio.NavitiaSDKUX.Components.Journey.Results.Solution.Loading;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;

import org.kisio.NavitiaSDKUX.Components.Primitive.StylizedComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;

import java.util.HashMap;
import java.util.Map;

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
