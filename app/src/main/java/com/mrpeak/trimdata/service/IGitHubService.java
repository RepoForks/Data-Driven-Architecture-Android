package com.mrpeak.trimdata.service;

import com.mrpeak.trimdata.dal.rawModel.GitHubRepository;
import com.mrpeak.trimdata.service.network.PPHttpRequestDelegate;

import java.util.ArrayList;

/**
 * Created by gaofeng on 16/3/4.
 */
public interface IGitHubService {
    public ArrayList<GitHubRepository> loadAllRepositories();
    public void loadAllRepositoriesFromServer(PPHttpRequestDelegate delegate);

    public GitHubRepository getRepositoryById(Long repoId);
    public void updateOrInsertRepository(GitHubRepository repo);

}
