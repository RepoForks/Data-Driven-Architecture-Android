package com.mrpeak.trimdata.application.feed.feedStream.model;

/**
 * Created by gaofeng on 16/3/1.
 */


public class FeedItem {
    public static final int FeedItemNormal = 0;
    public static final int FeedItemGitHubRepo = 1;
    public static final int FeedItemUnknown = 1000;

    private int itemType;
    private Long itemId;
    private int displayHeight;

    public FeedItem(int itemType, Long itemId) {
        this.itemType = itemType;
        this.itemId = itemId;
    }

    public int getItemType() {
        return itemType;
    }

    public Long getItemId() {
        return itemId;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }
}
