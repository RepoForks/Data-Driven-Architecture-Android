package com.mrpeak.trimdata.dal;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.activeandroid.*;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * Created by gaofeng on 16/2/29.
 */
public class DalFactory {
    private static DalFactory ourInstance = null;
    static {
        Injector injector = Guice.createInjector(new DalModule());
        ourInstance = injector.getInstance(DalFactory.class);
    }
    public static DalFactory getInstance() {
        return ourInstance;
    }


    private SQLiteDatabase db;

    @Inject
    public IGitHubRepoDal gitHubRepoDal;

    public void initDB(Application application) {
        ActiveAndroid.initialize(application);
        Configuration dbConfiguration = new Configuration.Builder(application).setDatabaseName("trim.db").create();
        ActiveAndroid.initialize(dbConfiguration);
    }
}
