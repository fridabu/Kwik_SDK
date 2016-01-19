package me.kwik.app;

import me.kwk.utils.FontsOverride;

/**
 * Created by Farid on 1/15/2016.
 */
public final class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "MONOSPACE", "OpenSans-Regular.ttf");
    }
}
