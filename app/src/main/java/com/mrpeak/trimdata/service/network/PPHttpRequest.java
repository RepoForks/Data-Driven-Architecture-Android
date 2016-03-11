package com.mrpeak.trimdata.service.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by gaofeng on 16/3/3.
 */

public class PPHttpRequest {
    public static final int  HTTP_REQUEST_CONNECTION_TIMEOUT = 15 * 1000;
    public static final int  HTTP_REQUEST_READ_TIMEOUT = 15 * 1000;
    public static final int  HTTP_REQUEST_WRITE_TIMEOUT = 15 * 1000;

    protected PPHttpRequestDelegate delegate = null;

    public void executeRequest(Call call, PPHttpRequestDelegate del) {
        this.delegate = del;
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                PPHttpRequest.this.onRequestSuccess(response);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                PPHttpRequest.this.onRequestFail(t);
            }
        });
    }

    protected void onRequestFail(Throwable t) {
        if (delegate != null) {
            delegate.onRequestFail(t.getMessage());
        }
    }

    protected void onRequestSuccess(retrofit2.Response<? extends Object> response) {
        if (response.isSuccess()) {
            if (delegate != null) {
                delegate.onRequestSuccess(this, 0);
            }
        } else {
            if (delegate != null) {
                delegate.onRequestFail(response.message());
            }
        }
    }
}
