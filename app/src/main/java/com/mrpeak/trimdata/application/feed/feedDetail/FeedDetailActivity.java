package com.mrpeak.trimdata.application.feed.feedDetail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.inject.Inject;
import com.mrpeak.ppkit.android.ObservableUtil;
import com.mrpeak.trimdata.R;
import com.mrpeak.trimdata.application.common.BaseActivity;
import com.mrpeak.trimdata.application.feed.feedDetail.model.FeedDetailGitHubRepo;
import com.mrpeak.trimdata.application.feed.feedStream.IFeedStreamBO;
import com.mrpeak.trimdata.application.feed.feedStream.model.FeedItemGitHubRepo;
import com.mrpeak.trimdata.dal.rawModel.GitHubRepository;
import com.mrpeak.trimdata.service.ServiceFactory;

import org.w3c.dom.Text;

import roboguice.inject.InjectView;
import rx.Subscription;
import rx.functions.Action1;

public class FeedDetailActivity extends BaseActivity {

    public static final String FEED_DETAIL_ID = "feedDetailId";

    @Inject private FeedDetailBO bo;
    @Inject private FeedDetailDH dh;

    @InjectView(R.id.toolbar) Toolbar toolbar;
    @InjectView(R.id.txtRepoName) TextView txtRepoName;
    @InjectView(R.id.txtRepoStar) TextView txtRepoStar;
    @InjectView(R.id.txtRepoFork) TextView txtRepoFork;
    @InjectView(R.id.txtRepoOpenIssue) TextView txtRepoOpenIssue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_detail);

        Long itemId = getIntent().getLongExtra(FEED_DETAIL_ID, -1);
        if (itemId != -1) {
            GitHubRepository repo = ServiceFactory.getInstance().gitHubService.getRepositoryById(itemId);
            if (repo != null) {
                FeedDetailGitHubRepo item = new FeedDetailGitHubRepo(repo);
                dh.setFeedDetailItem(item);

                toolbar.setTitle(item.getRepoName().getValue());

                //do UI binding
                Subscription s;
                s = item.getRepoName().subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        txtRepoName.setText(s);
                    }
                });
                ObservableUtil.preserve(s, this);

                s = item.getRepoStar().subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        txtRepoStar.setText(s);
                    }
                });
                ObservableUtil.preserve(s, this);

                s = item.getRepoFork().subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        txtRepoFork.setText(s);
                    }
                });
                ObservableUtil.preserve(s, this);

                s = item.getRepoOpenIssue().subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        txtRepoOpenIssue.setText(s);
                    }
                });
                ObservableUtil.preserve(s, this);
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ObservableUtil.dispose(this);
    }
}
