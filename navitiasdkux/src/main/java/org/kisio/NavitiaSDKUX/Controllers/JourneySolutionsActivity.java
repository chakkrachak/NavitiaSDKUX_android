package org.kisio.NavitiaSDKUX.Controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;

import org.kisio.NavitiaSDKUX.Screens.JourneySolutionsScreen;

public class JourneySolutionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ComponentContext c = new ComponentContext(this);

        final LithoView lithoView = LithoView.create(
            this /* context */,
            JourneySolutionsScreen.create(c)
                .initOriginId("2.3665844;48.8465337")
                .initDestinationId("2.2979169;48.8848719")
                .build()
        );

        setContentView(lithoView);
    }
}
