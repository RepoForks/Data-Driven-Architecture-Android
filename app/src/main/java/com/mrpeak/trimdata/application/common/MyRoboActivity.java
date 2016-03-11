package com.mrpeak.trimdata.application.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.inject.Key;

import java.util.HashMap;
import java.util.Map;

import roboguice.RoboGuice;
import roboguice.event.EventManager;
import roboguice.inject.RoboInjector;
import roboguice.util.RoboContext;

public class MyRoboActivity extends AppCompatActivity implements RoboContext {

    protected HashMap<Key<?>, Object> scopedObjects = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        final RoboInjector injector = RoboGuice.getInjector(this);
//        injector.injectMembersWithoutViews( this );
        super.onCreate(savedInstanceState);

        RoboGuice.setUseAnnotationDatabases(false);
    }

    @Override
    public Map<Key<?>, Object> getScopedObjectMap() {
        return scopedObjects;
    }

    @Override
    protected void onDestroy() {
        try {
        }
        finally {
            try {
                RoboGuice.destroyInjector( this );
            }
            finally {
                super.onDestroy();
            }
        }
    }
}
