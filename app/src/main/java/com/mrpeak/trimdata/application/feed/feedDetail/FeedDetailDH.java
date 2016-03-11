package com.mrpeak.trimdata.application.feed.feedDetail;

import android.content.Context;
import android.support.annotation.IntDef;

import com.google.inject.Inject;
import com.mrpeak.trimdata.application.common.BaseDataHandler;
import com.mrpeak.trimdata.application.feed.feedDetail.model.FeedDetailGitHubRepo;
import com.mrpeak.trimdata.application.feed.feedStream.model.FeedItemGitHubRepo;

/**
 * Created by gaofeng on 16/3/11.
 */
public class FeedDetailDH extends BaseDataHandler implements IFeedDetailDH {
    private FeedDetailGitHubRepo mRepo;

    @Inject
    public FeedDetailDH(Context context) {
        mContext = context;
    }

    @Override
    public void setFeedDetailItem(FeedDetailGitHubRepo repo) {
        mRepo = repo;
    }

    @Override
    public FeedDetailGitHubRepo getFeedDetailItem() {
        return mRepo;
    }
}
