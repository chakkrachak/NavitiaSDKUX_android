package org.kisio.NavitiaSDKUX.Screens;

import android.support.v7.widget.OrientationHelper;
import android.util.Log;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.StateValue;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateInitialState;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnUpdateState;
import com.facebook.litho.annotations.Param;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.State;
import com.facebook.litho.widget.LinearLayoutInfo;
import com.facebook.litho.widget.Recycler;
import com.facebook.litho.widget.RecyclerBinder;
import com.facebook.yoga.YogaEdge;

import org.kisio.NavitiaSDK.NavitiaConfiguration;
import org.kisio.NavitiaSDK.NavitiaSDK;
import org.kisio.NavitiaSDK.invokers.ApiCallback;
import org.kisio.NavitiaSDK.invokers.ApiException;
import org.kisio.NavitiaSDK.models.Journey;
import org.kisio.NavitiaSDK.models.Journeys;
import org.kisio.NavitiaSDKUX.Components.AlertComponent;
import org.kisio.NavitiaSDKUX.Components.ContainerComponent;
import org.kisio.NavitiaSDKUX.Components.DateTimeButtonComponent;
import org.kisio.NavitiaSDKUX.Components.JourneyFormComponent;
import org.kisio.NavitiaSDKUX.Components.JourneySolutionComponent;
import org.kisio.NavitiaSDKUX.Components.JourneySolutionLoadingComponent;
import org.kisio.NavitiaSDKUX.Components.ListViewComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.ViewComponent;
import org.kisio.NavitiaSDKUX.Components.ScreenHeaderComponent;
import org.kisio.NavitiaSDKUX.Components.ScrollViewComponent;
import org.kisio.NavitiaSDKUX.Components.TextComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;
import org.kisio.NavitiaSDKUX.R;
import org.kisio.NavitiaSDKUX.Util.Metrics;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NavitiaSDKUX_android
 *
 * Created by Johan Rouve on 23/08/2017.
 * Copyright © 2017 Kisio. All rights reserved.
 */

@LayoutSpec
public class JourneySolutionsScreenSpec {
    @OnCreateInitialState
    static void createInitialState(
        ComponentContext c,
        StateValue<String> origin,
        StateValue<String> originId,
        StateValue<String> destination,
        StateValue<String> destinationId,
        StateValue<Journeys> journeys,
        StateValue<Boolean> loaded,
        StateValue<Boolean> error,
        StateValue<Date> datetime,
        @Prop(optional = true) String initOrigin,
        @Prop String initOriginId,
        @Prop(optional = true) String initDestination,
        @Prop String initDestinationId) {

        origin.set((initOrigin != null) ? initOrigin : initOriginId);
        originId.set(initOriginId);
        destination.set((initDestination != null) ? initDestination : initDestinationId);
        destinationId.set(initDestinationId);
        loaded.set(false);
        error.set(false);
        datetime.set(new Date());

        final NavitiaConfiguration navitiaConfiguration = new NavitiaConfiguration("0de19ce5-e0eb-4524-a074-bda3c6894c19");
        try {
            final NavitiaSDK navitiaSDK = new NavitiaSDK(navitiaConfiguration);
            retrieveJourneys(c, navitiaSDK, originId.get(), destinationId.get(), datetime.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @State String origin,
        @State String originId,
        @State String destination,
        @State String destinationId,
        @State Date datetime,
        @State Journeys journeys,
        @State Boolean loaded,
        @State Boolean error) {

        Component<?> journeyComponent;
        if (journeys != null) {
            journeyComponent = getJourneyComponent(c, journeys);
        } else if (error){
            journeyComponent = AlertComponent.create(c)
                .text(c.getString(R.string.screen_JourneySolutionsScreen_error))
                .build();
        } else {
            journeyComponent = JourneySolutionLoadingComponent.create(c).build();
        }


        return ViewComponent.create(c)
            .child(
                ScreenHeaderComponent.create(c)
                    .styles(headerStyles)
                    .children(new Component<?>[]{
                        ContainerComponent.create(c)
                            .children(new Component<?>[]{
                            JourneyFormComponent.create(c)
                                .origin(origin)
                                .destination(destination)
                                .build(),
                            DateTimeButtonComponent.create(c).build(),
                        }).build()
                    })
            )
            .child(
                ScrollViewComponent.create(c)
                    .child(journeyComponent)
            )
            .build()
        ;
    }

    static Component<?> getJourneyComponent(ComponentContext c, Journeys journeys) {
        List<Component<?>> components = new ArrayList<>();
        for (Journey journey : journeys.getJourneys()) {
            components.add(
                JourneySolutionComponent.create(c)
                    .journey(journey)
                    .build()
            );
        }

        return ListViewComponent.create(c)
            .children(components.toArray(new Component<?>[components.size()]))
            .build();
    }

    static Map<String, Object> headerStyles = new HashMap<>();
    static {
        headerStyles.put("backgroundColor", Configuration.colors.tertiary);
    }

    // State Update

    @OnUpdateState
    static void updateJourneys(StateValue<Journeys> journeys, StateValue<Boolean> loaded, @Param Journeys result) {
        journeys.set(result);
        loaded.set(true);
    }

    @OnUpdateState
    static void updateError(StateValue<Boolean> error, StateValue<Boolean> loaded, @Param Boolean hasError) {
        error.set(hasError);
        loaded.set(true);
    }

    // Http

    static void retrieveJourneys(final ComponentContext c, NavitiaSDK navitiaSDK, String originId, String destinationId, Date datetime) {
        try {
            navitiaSDK.journeysApi.newJourneysRequestBuilder()
                .withFrom(originId)
                .withTo(destinationId)
                .withDatetime(Metrics.getIsoDatetime(datetime))
                .get(new ApiCallback<Journeys>() {
                    @Override
                    public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                        JourneySolutionsScreen.updateErrorAsync(c, true);
                        Log.d("HTTP Fail", e.getLocalizedMessage());
                    }

                    @Override
                    public void onSuccess(Journeys result, int statusCode, Map<String, List<String>> responseHeaders) {
                        JourneySolutionsScreen.updateJourneysAsync(c, result);
                    }

                    @Override
                    public void onUploadProgress(long bytesWritten, long contentLength, boolean done) { }

                    @Override
                    public void onDownloadProgress(long bytesRead, long contentLength, boolean done) { }
                });
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
