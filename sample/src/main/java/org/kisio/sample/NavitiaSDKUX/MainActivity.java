package org.kisio.sample.NavitiaSDKUX;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.kisio.NavitiaSDKUX.Controllers.JourneySolutionsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.sdk_open);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Intent intent = new Intent(v.getContext(), JourneySolutionsActivity.class);

                intent.putExtra("initOriginId", "2.3665844;48.8465337");
                intent.putExtra("initDestinationId", "2.2979169;48.8848719");

                startActivity(intent);
            }
        });
    }
}
