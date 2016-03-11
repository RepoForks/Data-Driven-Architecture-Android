package com.mrpeak.trimdata.application.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import roboguice.RoboGuice;
import roboguice.activity.RoboActivity;

public class BaseActivity extends RoboActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RoboGuice.setUseAnnotationDatabases(false);
    }
}
