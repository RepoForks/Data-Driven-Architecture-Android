package com.mrpeak.trimdata.application.feed.feedStream.model;

import com.mrpeak.trimdata.dal.EventSource;
import com.mrpeak.trimdata.dal.rawModel.GitHubRepository;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

/**
 * Created by gaofeng on 16/3/1.
 */
public class FeedItemGitHubRepo extends FeedItem {
    public BehaviorSubject<String> repoName;
    public BehaviorSubject<String> repoStar;
    public BehaviorSubject<String> repoFork;
    public BehaviorSubject<String> repoOpenIssue;

    public FeedItemGitHubRepo(final GitHubRepository repo) {
        super(FeedItem.FeedItemGitHubRepo, repo.getRepoId().getValue());
        this.repoName = BehaviorSubject.create("");
        this.repoStar = BehaviorSubject.create("");
        this.repoFork = BehaviorSubject.create("");
        this.repoOpenIssue = BehaviorSubject.create("");

        //option1: subscribe to property, dependent on permanent memory cache
//        repo.getName().subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                FeedItemGitHubRepo.this.getRepoName().onNext(repo.getName().getValue());
//            }
//        });
//
//        repo.getStar().subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                FeedItemGitHubRepo.this.getRepoStar().onNext("star:" + repo.getStar().getValue());
//            }
//        });
//
//        repo.getFork().subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                FeedItemGitHubRepo.this.getRepoFork().onNext("fork:" + repo.getFork().getValue());
//            }
//        });
//
//        repo.getOpenIssue().subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                FeedItemGitHubRepo.this.getRepoOpenIssue().onNext("openIssue:" + repo.getOpenIssue().getValue());
//            }
//        });


        //option2: subscribe to model instance
        updateFeedItemGitHubRepo(repo);
        EventSource.getInstance().githubEvent.update
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GitHubRepository>() {
                    @Override
                    public void call(GitHubRepository repo) {
                        updateFeedItemGitHubRepo(repo);
                    }
                });
    }

    private void updateFeedItemGitHubRepo(GitHubRepository repo) {
        if (repo.getRepoId().getValue() == getItemId()) {
            getRepoName().onNext(repo.getName().getValue());
            getRepoStar().onNext("star:" + repo.getStar().getValue());
            getRepoFork().onNext("fork:" + repo.getFork().getValue());
            getRepoOpenIssue().onNext("openIssue:" + repo.getOpenIssue().getValue());
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
