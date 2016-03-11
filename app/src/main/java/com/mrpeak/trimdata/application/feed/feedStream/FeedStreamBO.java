package com.mrpeak.trimdata.application.feed.feedStream;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import com.google.inject.Inject;
import com.mrpeak.trimdata.application.common.BaseBusinessObject;
import com.mrpeak.trimdata.application.feed.feedDetail.FeedDetailActivity;
import com.mrpeak.trimdata.application.feed.feedStream.model.FeedItemGitHubRepo;
import com.mrpeak.trimdata.dal.EventSource;
import com.mrpeak.trimdata.dal.rawModel.GitHubRepository;
import com.mrpeak.trimdata.service.ServiceFactory;
import com.mrpeak.trimdata.service.network.PPHttpRequest;
import com.mrpeak.trimdata.service.network.PPHttpRequestDelegate;
import com.mrpeak.trimdata.service.network.github.GitHubRepositoryListRequest;

import java.util.ArrayList;

import rx.functions.Action1;

/**
 * Created by gaofeng on 16/2/29.
 */
public class FeedStreamBO extends BaseBusinessObject implements IFeedStreamBO,
        PPHttpRequestDelegate,
        GitHubRepositoryListRequest.GitHubRepositoryListRequestDelegate {
    private final Context context;
    public FeedStreamBOListener mListener = null;

    @Inject
    public FeedStreamBO(final Context context) {
        this.context = context;

        //bind data event
        EventSource.getInstance().githubEvent.delete.subscribe(new Action1<GitHubRepository>() {
            @Override
            public void call(GitHubRepository repository) {
                //delete repo and refresh recycler view
                Log.d("", "github item deleted");
            }
        });
    }

    public void loadFeedsFromServer() {
        ServiceFactory.getInstance().gitHubService.loadAllRepositoriesFromServer(this);
    }

    public void loadFeedsFromDisk() {
        ArrayList<GitHubRepository> repos = ServiceFactory.getInstance().gitHubService.loadAllRepositories();
        ArrayList<FeedItemGitHubRepo> items = new ArrayList<>();

        for (int i = 0; i < repos.size(); i ++) {
            GitHubRepository repo = repos.get(i);
            FeedItemGitHubRepo item = new FeedItemGitHubRepo(repo);

            items.add(item);
        }

        if (dh instanceof IFeedStreamDH) {
            IFeedStreamDH handler = (IFeedStreamDH) dh;
            handler.setmItems(items);
        }
        if (mListener != null) {
            mListener.loadAllRepoFinished();
        }
    }

    @Override
    public void gotoFeedDetail(Long feedItemId) {
        Intent intent = new Intent(mContext, FeedDetailActivity.class);
        intent.putExtra(FeedDetailActivity.FEED_DETAIL_ID, feedItemId);
        mContext.startActivity(intent);
    }

    //region Http Request Callback
    @Override
    public void onRequestSuccess(PPHttpRequest req, int code) {

    }

    @Override
    public void onRequestFail(String msg) {
        Toast.makeText(mContext, "get repositories failed, try again later", Toast.LENGTH_SHORT).show();
        if (mListener != null) {
            mListener.loadAllRepoFailed();
        }
    }
    //endregion

    @Override
    public void getGitHubRepoFinished(ArrayList<GitHubRepository> repos) {
        ArrayList<FeedItemGitHubRepo> items = new ArrayList<>();
        for (GitHubRepository repo : repos) {
            FeedItemGitHubRepo item = new FeedItemGitHubRepo(repo);
            items.add(item);
        }

        if (dh instanceof IFeedStreamDH) {
            IFeedStreamDH handler = (IFeedStreamDH) dh;
            handler.setmItems(items);
        }
        if (mListener != null) {
            mListener.loadAllRepoFinished();
        }
    }



    public interface FeedStreamBOListener {
        void loadAllRepoFinished();
        void loadAllRepoFailed();
    }
}
