package com.mrpeak.trimdata.application.feed.feedDetail.model;

/**
 * Created by gaofeng on 16/3/11.
 */
public class FeedDetail {
    public static final int FeedItemNormal = 0;
    public static final int FeedItemGitHubRepo = 1;
    public static final int FeedItemUnknown = 1000;

    private int itemType;
    private Long itemId;

    public FeedDetail(int itemType, Long itemId) {
        this.itemType = itemType;
        this.itemId = itemId;
    }

    public int getItemType() {
        return itemType;
    }

    public Long getItemId() {
        return itemId;
    }

}
