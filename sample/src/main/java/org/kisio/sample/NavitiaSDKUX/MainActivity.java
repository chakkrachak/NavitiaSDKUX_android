package org.kisio.sample.NavitiaSDKUX;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.kisio.NavitiaSDKUX.Config.Configuration;
import org.kisio.NavitiaSDKUX.Controllers.JourneySolutionsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Insert your Navitia token
        Configuration.token = "";

        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.sdk_open);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Intent intent = new Intent(v.getContext(), JourneySolutionsActivity.class);

                intent.putExtra(JourneySolutionsActivity.IntentParameters.initOriginId.name(), "2.495689;48.742459");
                intent.putExtra(JourneySolutionsActivity.IntentParameters.initDestinationId.name(), "2.119853;48.820835");

                startActivity(intent);
            }
        });
    }
}
