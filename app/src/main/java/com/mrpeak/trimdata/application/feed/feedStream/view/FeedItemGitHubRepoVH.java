package com.mrpeak.trimdata.application.feed.feedStream.view;

import android.view.View;
import android.widget.TextView;

import com.mrpeak.ppkit.android.ObservableUtil;
import com.mrpeak.trimdata.R;
import com.mrpeak.trimdata.application.feed.feedStream.IFeedStreamBO;
import com.mrpeak.trimdata.application.feed.feedStream.model.*;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by gaofeng on 16/3/2.
 */

public class FeedItemGitHubRepoVH extends FeedItemVH {
    private TextView repoName;
    private TextView repoStar;
    private TextView repoFork;
    private TextView repoOpenIssue;

    private FeedItemGitHubRepo repo;

    public FeedItemGitHubRepoVH(View itemView) {
        super(itemView);
        repoName = (TextView) itemView.findViewById(R.id.txtRepoName);
        repoStar = (TextView) itemView.findViewById(R.id.txtRepoStar);
        repoFork = (TextView) itemView.findViewById(R.id.txtRepoFork);
        repoOpenIssue = (TextView) itemView.findViewById(R.id.txtRepoOpenIssue);
    }

    @Override
    public void onViewHolderClick() {
        super.onViewHolderClick();

        if (bo instanceof IFeedStreamBO) {
            IFeedStreamBO ibo = (IFeedStreamBO)bo;
            ibo.gotoFeedDetail(repo.getItemId());
        }
    }

    @Override
    public void bindFeedItem(FeedItem item) {

        repo = (FeedItemGitHubRepo)item;

        //remove old binding first
        ObservableUtil.dispose(this);

        //create new binding
        Subscription s;

        s = repo.getRepoName().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        repoName.setText(s);
                    }
                });
        ObservableUtil.preserve(s, this);

        s = repo.getRepoStar().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        repoStar.setText(s);
                    }
                });
        ObservableUtil.preserve(s, this);

        repoStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        s = repo.getRepoFork().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        repoFork.setText(s);
                    }
                });
        ObservableUtil.preserve(s, this);

        s = repo.getRepoOpenIssue().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        repoOpenIssue.setText(s);
                    }
                });
        ObservableUtil.preserve(s, this);
    }

    @Override
    public void onDetachFromWindow() {
        super.onDetachFromWindow();

        //remove all bindings
        ObservableUtil.dispose(this);
    }

    @Override
    public void onAttachToWindow() {
        super.onAttachToWindow();
    }
}
