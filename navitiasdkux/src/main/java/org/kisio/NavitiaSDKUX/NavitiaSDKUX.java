package org.kisio.NavitiaSDKUX;

import android.app.Application;

import com.facebook.litho.stetho.LithoWebKitInspector;
import com.facebook.soloader.SoLoader;
import com.facebook.stetho.Stetho;

public class NavitiaSDKUX extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SoLoader.init(this, false);

        Stetho.initialize(
            Stetho.newInitializerBuilder(this)
            .enableWebKitInspector(new LithoWebKitInspector(this))
            .build());
    }
}
