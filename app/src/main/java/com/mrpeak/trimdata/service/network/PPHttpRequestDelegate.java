package com.mrpeak.trimdata.service.network;

/**
 * Created by gaofeng on 16/3/3.
 */
public interface PPHttpRequestDelegate {
    void onRequestSuccess(PPHttpRequest req, int code);
    void onRequestFail(String msg);
}
