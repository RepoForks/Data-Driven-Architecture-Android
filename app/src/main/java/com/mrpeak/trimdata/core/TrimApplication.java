package com.mrpeak.trimdata.core;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.mrpeak.trimdata.service.ServiceFactory;

/**
 * Created by gaofeng on 16/3/5.
 */
public class TrimApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        TrimApplication.context = getApplicationContext();

        ServiceFactory.getInstance().initEnv(this);
    }

    public static Context getAppContext() {
        return TrimApplication.context;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
