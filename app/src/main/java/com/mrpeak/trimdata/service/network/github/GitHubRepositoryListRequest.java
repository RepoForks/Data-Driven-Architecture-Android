package com.mrpeak.trimdata.service.network.github;

import com.mrpeak.trimdata.dal.rawModel.GitHubRepository;
import com.mrpeak.trimdata.service.ServiceFactory;
import com.mrpeak.trimdata.service.network.retrofitModel.RfRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * Created by gaofeng on 16/3/4.
 */
public class GitHubRepositoryListRequest extends GitHubRequest {
    public GitHubRepositoryListRequestDelegate reqDelegate = null;
    @Override
    protected void onRequestSuccess(Response<? extends Object> response) {
        if (response.isSuccess()) {
            ArrayList<GitHubRepository> repos = new ArrayList<>();
            List<RfRepository> items = (List<RfRepository>) response.body();
            for (RfRepository item : items) {
                GitHubRepository repo = new GitHubRepository(Long.parseLong(""+item.getId()),
                        item.getName(),
                        item.getStargazers_count(),
                        item.getForks_count(),
                        item.getOpen_issues_count());
                repos.add(repo);
            }

            //notify application layer
            if (reqDelegate != null) {
                reqDelegate.getGitHubRepoFinished(repos);
            }

            //save data
            for (GitHubRepository repo : repos) {
                ServiceFactory.getInstance().gitHubService.updateOrInsertRepository(repo);
            }

        } else {
            if (delegate != null) {
                delegate.onRequestFail("");
            }
        }
    }

    public interface GitHubRepositoryListRequestDelegate {
        public void getGitHubRepoFinished(ArrayList<GitHubRepository> repos);
    }
}
