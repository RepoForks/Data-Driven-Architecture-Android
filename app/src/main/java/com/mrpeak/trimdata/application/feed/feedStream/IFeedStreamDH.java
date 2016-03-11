package com.mrpeak.trimdata.application.feed.feedStream;

import com.mrpeak.trimdata.application.feed.feedStream.model.FeedItem;

import java.util.ArrayList;

/**
 * Created by gaofeng on 16/3/2.
 */
public interface IFeedStreamDH {
    ArrayList<FeedItem> getmItems();
    <T extends FeedItem> void setmItems(ArrayList<T> items);
    <T extends FeedItem> void addItem(T item);
}
