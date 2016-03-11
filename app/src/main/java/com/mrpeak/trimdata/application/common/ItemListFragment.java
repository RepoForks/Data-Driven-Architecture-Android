package com.mrpeak.trimdata.application.common;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrpeak.trimdata.R;

import roboguice.fragment.RoboFragment;

public class ItemListFragment<T> extends BaseFragment {

    protected SwipeRefreshLayout mSwipeRefreshLayout = null;
    protected RecyclerView mRecyclerView = null;

    public ItemListFragment() {
    }

    @SuppressWarnings("unused")
    public static ItemListFragment newInstance() {
        ItemListFragment fragment = new ItemListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_itemlist_list, container, false);
        //setup recycler view
        View subView = view.findViewById(R.id.recyclerList);
        if (subView instanceof RecyclerView) {
            Context context = view.getContext();
            mRecyclerView = (RecyclerView) subView;
            mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
        //setup refresh view
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment_itemlist_swipeRefreshLayout);
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    ItemListFragment.this.onRefreshTriggered();
                }
            });
        }

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setRecyclerViewAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter) {
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(adapter);
        }
    }

    protected void onRefreshTriggered() {

    }

    protected void stopRefreshing() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
