package com.mrpeak.trimdata.application.feed.feedStream;

/**
 * Created by gaofeng on 16/3/2.
 */
public interface IFeedStreamBO {
    void loadFeedsFromServer();
    void gotoFeedDetail(Long feedItemId);
}
