package com.mrpeak.trimdata.service;

import com.google.inject.AbstractModule;
import com.mrpeak.trimdata.service.cache.GitHubServiceCache;
import com.mrpeak.trimdata.service.imp.FeedStreamServiceImp;
import com.mrpeak.trimdata.service.imp.GitHubServiceImp;
import com.mrpeak.trimdata.service.test.FeedStreamServiceTest;

/**
 * Created by gaofeng on 16/3/2.
 */
public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IFeedStreamService.class).to(FeedStreamServiceImp.class);
        bind(IGitHubService.class).to(GitHubServiceCache.class);
    }

    public ServiceModule() {

    }
}

