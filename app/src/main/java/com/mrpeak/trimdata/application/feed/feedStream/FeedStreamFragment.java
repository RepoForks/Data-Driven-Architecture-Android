package com.mrpeak.trimdata.application.feed.feedStream;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.view.View;

import com.google.inject.Inject;
import com.mrpeak.trimdata.application.common.ItemListFragment;
import com.mrpeak.trimdata.application.feed.feedStream.model.FeedItem;

public class FeedStreamFragment extends ItemListFragment implements FeedStreamBO.FeedStreamBOListener {

    @Inject
    private FeedStreamBO bo;
    @Inject
    public FeedStreamDH dh;
    @Inject
    private FeedStreamAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //do initialization
        bo.mContext = getContext();
        bo.dh = dh;
        bo.mListener = this;

        adapter.bo = bo;
        adapter.setmItems(dh.getmItems());

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setRecyclerViewAdapter(adapter);

        bo.loadFeedsFromDisk();

        bo.loadFeedsFromServer();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    @UiThread
    public void loadAllRepoFinished() {
        adapter.notifyDataSetChanged();
        stopRefreshing();
    }

    @Override
    @UiThread
    public void loadAllRepoFailed() {
        stopRefreshing();
    }

    @Override
    protected void onRefreshTriggered() {
        super.onRefreshTriggered();

        bo.loadFeedsFromServer();
    }
}
