package org.kisio.NavitiaSDKUX.Controllers;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.MenuItem;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;

import org.kisio.NavitiaSDKUX.Config.Configuration;
import org.kisio.NavitiaSDKUX.R;
import org.kisio.NavitiaSDKUX.Screens.JourneySolutionsScreen;

public class JourneySolutionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);
            actionBar.setTitle(R.string.controller_JourneySolutionsController_title);
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
        Configuration.colors.setTertiary(fetchPrimaryColor());

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private int fetchPrimaryColor() {
        TypedValue typedValue = new TypedValue();

        TypedArray a = this.obtainStyledAttributes(typedValue.data, new int[] { R.attr.colorPrimary });
        int color = a.getColor(0, 0);

        a.recycle();

        return color;
    }
}
