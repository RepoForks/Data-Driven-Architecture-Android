package com.mrpeak.trimdata.service.cache;

import com.mrpeak.trimdata.dal.rawModel.GitHubRepository;
import com.mrpeak.trimdata.service.imp.GitHubServiceImp;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by gaofeng on 16/3/4.
 */
public class GitHubServiceCache extends GitHubServiceImp {
    private HashMap<Long, GitHubRepository> cachedRepoMap = new HashMap<>();



    @Override
    public ArrayList<GitHubRepository> loadAllRepositories() {
        ArrayList<GitHubRepository> repos = super.loadAllRepositories();
        synchronized(this) {
            for (GitHubRepository repo : repos) {
                cachedRepoMap.put(repo.getRepoId().getValue(), repo);
            }
        }
        return repos;
    }

    @Override
    public void updateOrInsertRepository(GitHubRepository repo) {
        GitHubRepository existingRepo = getRepositoryById(repo.getRepoId().getValue());
        if (existingRepo != null) {
            //application layer will receive property change event
            existingRepo.setName(repo.getName().getValue());
            existingRepo.setStar(repo.getStar().getValue());
            existingRepo.setFork(repo.getFork().getValue());
            existingRepo.setOpenIssue(repo.getOpenIssue().getValue());
        }
        super.updateOrInsertRepository(repo);
    }

    @Override
    public GitHubRepository getRepositoryById(Long repoId) {
        GitHubRepository existingRepo;
        synchronized (this) {
            existingRepo = cachedRepoMap.get(repoId);
        }
        if (existingRepo != null) {
            return existingRepo;
        }
        return super.getRepositoryById(repoId);
    }


}
