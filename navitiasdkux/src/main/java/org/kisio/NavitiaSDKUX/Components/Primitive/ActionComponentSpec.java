package org.kisio.NavitiaSDKUX.Components.Primitive;

import android.content.Intent;
import android.support.annotation.AttrRes;
import android.support.annotation.StyleRes;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;

import com.facebook.litho.ClickEvent;
import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.Prop;

import org.kisio.NavitiaSDKUX.Controllers.JourneySolutionRoadmapActivity;

import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

@LayoutSpec
public class ActionComponentSpec {
    static Callable<?> action;

    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c, @Prop Component<?> child, @Prop Callable<?> actionToCall) {
        final ComponentLayout.ContainerBuilder builder = ViewComponent.create(c);

        action = actionToCall;

        return builder
            .child(child)
            .clickHandler(ActionComponent.onClick(c))
            .build();
    }

    @OnEvent(ClickEvent.class)
    static void onClick(ComponentContext c, @FromEvent View view) {
        try {
            action.call();
        } catch (Exception e) {
            Log.e("ActionComponentEx", e.getMessage());
        }
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
