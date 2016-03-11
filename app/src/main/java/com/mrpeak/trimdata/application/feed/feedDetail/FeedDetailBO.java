package com.mrpeak.trimdata.application.feed.feedDetail;

import android.content.Context;

import com.google.inject.Inject;
import com.mrpeak.trimdata.application.common.BaseBusinessObject;

/**
 * Created by gaofeng on 16/3/11.
 */
public class FeedDetailBO extends BaseBusinessObject implements IFeedDetailBO {

    @Inject
    public FeedDetailBO(Context mContext) {
        this.mContext = mContext;
    }
}
