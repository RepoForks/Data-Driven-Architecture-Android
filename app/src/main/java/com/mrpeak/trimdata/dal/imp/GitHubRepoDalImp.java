package com.mrpeak.trimdata.dal.imp;

import com.activeandroid.query.Select;
import com.mrpeak.trimdata.dal.DalBase;
import com.mrpeak.trimdata.dal.EventSource;
import com.mrpeak.trimdata.dal.IGitHubRepoDal;
import com.mrpeak.trimdata.dal.rawModel.GitHubRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaofeng on 16/3/5.
 */
public class GitHubRepoDalImp extends DalBase implements IGitHubRepoDal {

    public void insertOrUpdateGitHubRepo(GitHubRepository repo) {
        GitHubRepository entity = getRepoById(repo.getRepoId().getValue());
        if (entity != null) {
            //do update
            entity.setName(repo.getName().getValue());
            entity.setStar(repo.getStar().getValue());
            entity.setFork(repo.getFork().getValue());
            entity.setOpenIssue(repo.getOpenIssue().getValue());

            entity.saveEntity();
            EventSource.getInstance().githubEvent.update.onNext(repo);
        } else {
            //do insert
            repo.saveEntity();
            EventSource.getInstance().githubEvent.insert.onNext(repo);
        }
    }

    public GitHubRepository getRepoById(Long repoId) {
        return new Select()
                .from(GitHubRepository.class)
                .where("repoId = ?", repoId)
                .executeSingle();
    }

    public List<GitHubRepository> loadAll() {
        List<GitHubRepository> all = new Select()
                .from(GitHubRepository.class)
                .execute();

        for(GitHubRepository repo : all) {
            repo.initModel();
        }

        return all;
    }
}
