package com.mrpeak.trimdata.application.feed.feedStream;

import android.content.Context;

import com.google.inject.Inject;
import com.mrpeak.trimdata.application.common.BaseDataHandler;
import com.mrpeak.trimdata.application.feed.feedStream.model.FeedItem;

import java.util.ArrayList;

/**
 * Created by gaofeng on 16/2/29.
 */
public class FeedStreamDH extends BaseDataHandler implements IFeedStreamDH {
    private final Context context;

    private ArrayList<FeedItem> mItems = new ArrayList<>();

    @Inject
    public FeedStreamDH(final Context context) {
        this.context = context;
    }

    public ArrayList<FeedItem> getmItems() {
        return mItems;
    }
    public <T extends FeedItem> void setmItems(ArrayList<T> items) {
        this.mItems.clear();
        for (FeedItem item : items) {
            this.mItems.add(item);
        }
    }

    public <T extends FeedItem> void addItem(T item) {
        this.mItems.add(item);
    }
}

