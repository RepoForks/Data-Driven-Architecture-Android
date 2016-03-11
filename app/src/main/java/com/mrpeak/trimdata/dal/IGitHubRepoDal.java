package com.mrpeak.trimdata.dal;

import com.mrpeak.trimdata.dal.rawModel.GitHubRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaofeng on 16/3/5.
 */
public interface IGitHubRepoDal extends IDalBase {
    void insertOrUpdateGitHubRepo(GitHubRepository repo);
    GitHubRepository getRepoById(Long repoId);
    List<GitHubRepository> loadAll();
}
