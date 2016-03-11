package com.mrpeak.trimdata.dal;

import com.google.inject.AbstractModule;
import com.mrpeak.trimdata.dal.imp.GitHubRepoDalImp;

/**
 * Created by gaofeng on 16/3/5.
 */
public class DalModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IGitHubRepoDal.class).to(GitHubRepoDalImp.class);
    }

    public DalModule() {

    }
}
