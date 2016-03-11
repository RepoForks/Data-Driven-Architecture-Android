package com.mrpeak.trimdata.application.feed.feedDetail;

import com.mrpeak.trimdata.application.feed.feedDetail.model.FeedDetailGitHubRepo;
import com.mrpeak.trimdata.application.feed.feedStream.model.FeedItem;
import com.mrpeak.trimdata.application.feed.feedStream.model.FeedItemGitHubRepo;
import com.mrpeak.trimdata.dal.rawModel.GitHubRepository;

/**
 * Created by gaofeng on 16/3/11.
 */
public interface IFeedDetailDH {
    void setFeedDetailItem(FeedDetailGitHubRepo repo);
    FeedDetailGitHubRepo getFeedDetailItem();
}
