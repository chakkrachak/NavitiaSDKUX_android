package org.kisio.NavitiaSDKUX.Controllers;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;

import org.kisio.NavitiaSDKUX.Config.Configuration;
import org.kisio.NavitiaSDKUX.R;
import org.kisio.NavitiaSDKUX.Screens.JourneySolutionsScreen;

public class JourneySolutionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getSupportActionBar().setElevation(0);
        this.getSupportActionBar().setTitle(R.string.controller_JourneySolutionsController_title);
        Configuration.colors.setTertiary(Color.parseColor("#0E3356"));

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
