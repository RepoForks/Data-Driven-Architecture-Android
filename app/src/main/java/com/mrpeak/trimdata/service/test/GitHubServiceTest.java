package com.mrpeak.trimdata.service.test;

import com.mrpeak.trimdata.dal.rawModel.GitHubRepository;
import com.mrpeak.trimdata.service.cache.GitHubServiceCache;

import java.util.ArrayList;

/**
 * Created by gaofeng on 16/3/11.
 */

public class GitHubServiceTest extends GitHubServiceCache {
    @Override
    public ArrayList<GitHubRepository> loadAllRepositories() {
        ArrayList<GitHubRepository> repos = super.loadAllRepositories();
        //run some tests here...
        return repos;
    }
}
