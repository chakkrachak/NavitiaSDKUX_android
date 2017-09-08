package org.kisio.NavitiaSDKUX.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;

import org.kisio.NavitiaSDK.models.Journey;
import org.kisio.NavitiaSDKUX.R;
import org.kisio.NavitiaSDKUX.Screens.JourneySolutionRoadmapScreen;
import org.kisio.NavitiaSDKUX.Screens.JourneySolutionsScreen;

public class JourneySolutionRoadmapActivity extends AppCompatActivity {
    private static final String TAG = JourneySolutionRoadmapActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);
            actionBar.setTitle(R.string.controller_JourneySolutionRoadmapController_title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        final ComponentContext c = new ComponentContext(this);
        final JourneySolutionRoadmapScreen.Builder builder = JourneySolutionRoadmapScreen.create(c);

        setProps(builder);

        try {
            final Component<JourneySolutionRoadmapScreen> screenComponent = builder.build();
            final LithoView lithoView = LithoView.create(this, screenComponent);
            setContentView(lithoView);
        } catch (IllegalStateException e) {
            Log.e(TAG, e.getMessage());
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setProps(JourneySolutionRoadmapScreen.Builder builder) {
        final Intent intent = getIntent();

        final Journey journey = (Journey)intent.getParcelableExtra("journey");
        if (journey != null) {
            builder.initJourney(journey);
        }
    }
}
