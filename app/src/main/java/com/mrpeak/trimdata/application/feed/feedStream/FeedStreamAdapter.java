package com.mrpeak.trimdata.application.feed.feedStream;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.inject.Inject;
import com.mrpeak.trimdata.R;
import com.mrpeak.trimdata.application.common.BaseRecyclerViewAdapter;
import com.mrpeak.trimdata.application.common.BaseViewHolder;
import com.mrpeak.trimdata.application.feed.feedStream.model.FeedItem;
import com.mrpeak.trimdata.application.feed.feedStream.model.FeedItemGitHubRepo;
import com.mrpeak.trimdata.application.feed.feedStream.view.FeedItemGitHubRepoVH;
import com.mrpeak.trimdata.application.feed.feedStream.view.FeedItemVH;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaofeng on 16/3/2.
 */
public class FeedStreamAdapter extends BaseRecyclerViewAdapter {
    private ArrayList<FeedItem> mItems;

    private final Context mContext;
    private final LayoutInflater mInflator;

    @Inject
    public FeedStreamAdapter(final Context context) {

        mContext = context;
        mInflator = LayoutInflater.from(context);

        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        FeedItem item  = mItems.get(position);
        long itemId = item.getItemId();
        return itemId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder vh = null;
        if (viewType == FeedItem.FeedItemGitHubRepo) {
            View view = mInflator.inflate(R.layout.fragment_itemlist_github_repo, parent, false);
            vh = new FeedItemGitHubRepoVH(view);
        }

        if (vh != null) {
            vh.bo = bo;
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FeedItemVH) {
            FeedItemVH vh = (FeedItemVH)holder;
            FeedItem item  = mItems.get(position);
            vh.bindFeedItem(item);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getFeedItemType(position);
    }

    @Override
    public int getItemCount() {
        int count = mItems.size();
        return count;
    }

    private int getFeedItemType(int position) {
        if (position > mItems.size() - 1) {
            return FeedItem.FeedItemUnknown;
        }
        FeedItem item = mItems.get(position);
        return item.getItemType();
    }

    public List<FeedItem> getmItems() {
        return mItems;
    }

    public void setmItems(ArrayList<FeedItem> mItems) {
        this.mItems = mItems;
    }
}
