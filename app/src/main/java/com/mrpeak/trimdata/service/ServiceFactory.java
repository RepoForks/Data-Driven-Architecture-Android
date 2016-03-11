package com.mrpeak.trimdata.service;

import android.app.Application;
import android.content.Context;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.mrpeak.trimdata.dal.DalFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by gaofeng on 16/2/29.
 */
public class ServiceFactory {
    private static ServiceFactory ourInstance = null;
    static {
        Injector injector = Guice.createInjector(new ServiceModule());
        ourInstance = injector.getInstance(ServiceFactory.class);
    }
    public static ServiceFactory getInstance() {
        return ourInstance;
    }

    public static final ScheduledExecutorService serviceExecutor = Executors.newSingleThreadScheduledExecutor();

    @Inject
    public IFeedStreamService feedStreamService;
    @Inject
    public IGitHubService gitHubService;


    public void initEnv(Application application) {
        DalFactory.getInstance().initDB(application);
    }
}
