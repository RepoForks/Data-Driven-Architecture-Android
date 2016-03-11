package com.mrpeak.trimdata.dal.rawModel;

import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.inject.Inject;

import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

/**
 * Created by gaofeng on 16/2/29.
 */

@Table(name = "GitHubRepo")
public class GitHubRepository extends RawModelBase {

    @Column(name = "repoId", index = true)
    public Long repoId_c;

    @Column(name = "name")
    public String name_c;

    @Column(name = "star")
    public Integer star_c;

    @Column(name = "fork")
    public Integer fork_c;

    @Column(name = "openIssue")
    public Integer openIssue_c;

    @Override
    public void saveEntity() {
        save();
    }

    private BehaviorSubject<Long> repoId;
    private BehaviorSubject<String> name;
    private BehaviorSubject<Integer> star;
    private BehaviorSubject<Integer> fork;
    private BehaviorSubject<Integer> openIssue;

    public GitHubRepository(Long id, String name, int star, int fork, int openIssue) {
        initProperties(id, name, star, fork, openIssue);
    }

    public GitHubRepository() {
    }

    public void initModel() {
        initProperties(repoId_c, name_c, star_c, fork_c, openIssue_c);
    }

    private void initProperties(Long id, String name, int star, int fork, int openIssue) {

        this.repoId = BehaviorSubject.create(id);
        this.name = BehaviorSubject.create(name);
        this.star = BehaviorSubject.create(star);
        this.fork = BehaviorSubject.create(fork);
        this.openIssue = BehaviorSubject.create(openIssue);

        this.repoId.subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                repoId_c = aLong;
            }
        });

        this.name.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                name_c = s;
            }
        });

        this.star.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                star_c = integer;
            }
        });

        this.fork.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                fork_c = integer;
            }
        });

        this.openIssue.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                openIssue_c = integer;
            }
        });

    }

    public BehaviorSubject<Long> getRepoId() {
        return repoId;
    }

    public void setRepoId(Long repoId) {
        this.repoId.onNext(repoId);
    }

    public BehaviorSubject<String> getName() {
        return name;
    }

    public void setName(String name) {
        this.name.onNext(name);
    }

    public BehaviorSubject<Integer> getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star.onNext(star);
    }

    public BehaviorSubject<Integer> getFork() {
        return fork;
    }

    public void setFork(Integer fork) {
        this.fork.onNext(fork);
    }

    public BehaviorSubject<Integer> getOpenIssue() {
        return openIssue;
    }

    public void setOpenIssue(Integer openIssue) {
        this.openIssue.onNext(openIssue);
    }
}
