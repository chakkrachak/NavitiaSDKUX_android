package org.kisio.NavitiaSDKUX.Screens;

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

import org.kisio.NavitiaSDK.NavitiaConfiguration;
import org.kisio.NavitiaSDK.NavitiaSDK;
import org.kisio.NavitiaSDK.invokers.ApiCallback;
import org.kisio.NavitiaSDK.invokers.ApiException;
import org.kisio.NavitiaSDK.models.Journey;
import org.kisio.NavitiaSDK.models.Journeys;
import org.kisio.NavitiaSDKUX.Components.AlertComponent;
import org.kisio.NavitiaSDKUX.Components.ContainerComponent;
import org.kisio.NavitiaSDKUX.Components.DateTimeButtonComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Results.FormComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Results.Solution.LoadingComponent;
import org.kisio.NavitiaSDKUX.Components.Journey.Results.SolutionComponent;
import org.kisio.NavitiaSDKUX.Components.ListViewComponent;
import org.kisio.NavitiaSDKUX.Components.Primitive.BaseViewComponent;
import org.kisio.NavitiaSDKUX.Components.ScreenHeaderComponent;
import org.kisio.NavitiaSDKUX.Components.ScrollViewComponent;
import org.kisio.NavitiaSDKUX.Config.Configuration;
import org.kisio.NavitiaSDKUX.R;
import org.kisio.NavitiaSDKUX.Util.Metrics;

import java.util.ArrayList;
import java.util.Arrays;
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

        origin.set((initOrigin != null) ? initOrigin : "");
        originId.set(initOriginId);
        destination.set((initDestination != null) ? initDestination : "");
        destinationId.set(initDestinationId);
        loaded.set(false);
        error.set(false);
        datetime.set(new Date());

        final NavitiaConfiguration navitiaConfiguration = new NavitiaConfiguration(Configuration.token);
        try {
            final NavitiaSDK navitiaSDK = new NavitiaSDK(navitiaConfiguration);
            retrieveJourneys(c, navitiaSDK, originId.get(), destinationId.get(), datetime.get());
        } catch (Exception e) {
            error.set(true);
            loaded.set(true);
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

        Component<?>[] journeyComponent;
        if (journeys != null) {
            journeyComponent = getJourneyComponent(c, journeys);
        } else if (error){
            journeyComponent = new Component<?>[]{
                AlertComponent.create(c)
                    .text(c.getString(R.string.screen_JourneySolutionsScreen_error))
                    .build()
            };
        } else {
            journeyComponent = new Component<?>[]{
                LoadingComponent.create(c).build(),
                LoadingComponent.create(c).build(),
                LoadingComponent.create(c).build(),
                LoadingComponent.create(c).build(),
            };
        }

        return BaseViewComponent.create(c)
            .child(
                ScreenHeaderComponent.create(c)
                    .styles(headerStyles)
                    .children(new Component<?>[]{
                        ContainerComponent.create(c)
                            .children(new Component<?>[]{
                            FormComponent.create(c)
                                .origin(origin.isEmpty()? originId : origin)
                                .destination(destination.isEmpty()? destinationId : destination)
                                .build(),
                            DateTimeButtonComponent.create(c).build(),
                        }).build()
                    })
            )
            .child(
                ScrollViewComponent.create(c)
                    .child(
                        ListViewComponent.create(c)
                            .children(journeyComponent)
                    )
            )
            .build()
        ;
    }

    static Component<?>[] getJourneyComponent(ComponentContext c, Journeys journeys) {
        List<Component<?>> components = new ArrayList<>();
        Integer index = 1;
        for (Journey journey : journeys.getJourneys()) {
            components.add(
                SolutionComponent.create(c)
                    .testKey("result-" + index)
                    .journey(journey)
                    .isTouchable(true)
                    .build()
            );
            index++;
        }

        return components.toArray(new Component<?>[components.size()]);
    }

    static Map<String, Object> headerStyles = new HashMap<>();
    static {
        headerStyles.put("backgroundColor", Configuration.colors.getTertiary());
    }

    // State Update

    @OnUpdateState
    static void updateJourneys(StateValue<Journeys> journeys, StateValue<Boolean> loaded, StateValue<String> origin, StateValue<String> destination, @Param Journeys result) {
        journeys.set(result);
        loaded.set(true);

        if (result.getJourneys().isEmpty() == false) {
            final Journey journey = result.getJourneys().get(0);

            if (journey.getSections().isEmpty() == false) {
                if (origin.get().isEmpty()) {
                    origin.set(journey.getSections().get(0).getFrom().getName());
                }
                if (destination.get().isEmpty()) {
                    destination.set(journey.getSections().get(journey.getSections().size() - 1).getFrom().getName());
                }
            }
        }
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
                .withFirstSectionMode(Arrays.asList("bike"))
                .withLastSectionMode(Arrays.asList("car"))
                .get(new ApiCallback<Journeys>() {
                    @Override
                    public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                        JourneySolutionsScreen.updateErrorAsync(c, true);
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
