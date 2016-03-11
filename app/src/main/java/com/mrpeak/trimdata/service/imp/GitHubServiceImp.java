package com.mrpeak.trimdata.service.imp;

import com.mrpeak.trimdata.dal.DalFactory;
import com.mrpeak.trimdata.dal.EventSource;
import com.mrpeak.trimdata.dal.rawModel.GitHubRepository;
import com.mrpeak.trimdata.service.IGitHubService;
import com.mrpeak.trimdata.service.ServiceBase;
import com.mrpeak.trimdata.service.network.PPHttpRequestDelegate;
import com.mrpeak.trimdata.service.network.github.GitHubRepositoryListRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaofeng on 16/3/4.
 */
public class GitHubServiceImp extends ServiceBase implements IGitHubService {
    public GitHubServiceImp() {

    }

    public ArrayList<GitHubRepository> loadAllRepositories() {
        ArrayList<GitHubRepository> repos = new ArrayList();
        List<GitHubRepository> entities = DalFactory.getInstance().gitHubRepoDal.loadAll();
        repos.addAll(entities);
        return repos;
    }

    @Override
    public void loadAllRepositoriesFromServer(PPHttpRequestDelegate delegate) {
        String username = "music4kid";
        GitHubRepositoryListRequest req = new GitHubRepositoryListRequest();
        if (delegate instanceof  GitHubRepositoryListRequest.GitHubRepositoryListRequestDelegate) {
            req.reqDelegate = (GitHubRepositoryListRequest.GitHubRepositoryListRequestDelegate) delegate;
        }
        req.executeRequest(req.getGitHubServiceProvider().userReposList(username), delegate);
    }


    @Override
    public void updateOrInsertRepository(GitHubRepository repo) {
        DalFactory.getInstance().gitHubRepoDal.insertOrUpdateGitHubRepo(repo);
    }

    @Override
    public GitHubRepository getRepositoryById(Long repoId) {
        return DalFactory.getInstance().gitHubRepoDal.getRepoById(repoId);
    }
}
