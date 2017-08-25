package org.kisio.NavitiaSDKUX.Components.Primitive;

import android.support.annotation.AttrRes;
import android.support.annotation.StyleRes;

import com.facebook.litho.Column;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;

/**
 * NavitiaSDKUX_android
 *
 * Created by Johan Rouve on 23/08/2017.
 * Copyright © 2017 Kisio. All rights reserved.
 */

public class ViewComponent {
    private ViewComponent() {
    }

    public static ComponentLayout.ContainerBuilder create(
        ComponentContext c,
        @AttrRes int defStyleAttr,
        @StyleRes int defStyleRes) {
        return Column.create(c, defStyleAttr, defStyleRes);
    }

    public static ComponentLayout.ContainerBuilder create(ComponentContext c) {
        return create(c, 0, 0);
    }
}
