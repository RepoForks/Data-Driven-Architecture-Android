package com.mrpeak.trimdata.application.feed.feedDetail.model;

import com.mrpeak.trimdata.application.feed.feedStream.model.FeedItem;
import com.mrpeak.trimdata.dal.EventSource;
import com.mrpeak.trimdata.dal.rawModel.GitHubRepository;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

/**
 * Created by gaofeng on 16/3/11.
 */
public class FeedDetailGitHubRepo extends FeedDetail {
    public BehaviorSubject<String> repoName;
    public BehaviorSubject<String> repoStar;
    public BehaviorSubject<String> repoFork;
    public BehaviorSubject<String> repoOpenIssue;

    public FeedDetailGitHubRepo(final GitHubRepository repo) {
        super(FeedItemGitHubRepo, repo.getRepoId().getValue());
        this.repoName = BehaviorSubject.create("");
        this.repoStar = BehaviorSubject.create("");
        this.repoFork = BehaviorSubject.create("");
        this.repoOpenIssue = BehaviorSubject.create("");

        //option2: subscribe to model instance
        updateFeedDetail(repo);
        EventSource.getInstance().githubEvent.update
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GitHubRepository>() {
                    @Override
                    public void call(GitHubRepository repo) {
                        updateFeedDetail(repo);
                    }
                });
    }

    private void updateFeedDetail(GitHubRepository repo) {
        if (repo.getRepoId().getValue() == getItemId()) {
            getRepoName().onNext("repository name:" + repo.getName().getValue());
            getRepoStar().onNext("total star:" + repo.getStar().getValue());
            getRepoFork().onNext("total fork:" + repo.getFork().getValue());
            getRepoOpenIssue().onNext("total openIssue:" + repo.getOpenIssue().getValue());
        }
    }

    public BehaviorSubject<String> getRepoName() {
        return repoName;
    }

    public BehaviorSubject<String> getRepoStar() {
        return repoStar;
    }

    public BehaviorSubject<String> getRepoFork() {
        return repoFork;
    }

    public BehaviorSubject<String> getRepoOpenIssue() {
        return repoOpenIssue;
    }
}
