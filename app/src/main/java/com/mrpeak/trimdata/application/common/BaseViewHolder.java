package com.mrpeak.trimdata.application.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by gaofeng on 16/3/2.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    public Object bo = null;

    public BaseViewHolder(View itemView) {
        super(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewHolderClick();
            }
        });
    }

    public void onDetachFromWindow() {
    }
    public void onAttachToWindow() {
    }
    public void onViewHolderClick() {
    }

}
