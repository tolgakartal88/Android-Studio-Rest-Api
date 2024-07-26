package com.example.tprestapi.restapitools;

import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public abstract class HttpUtilsListener extends AsyncHttpResponseHandler {

    @Override
    public void onStart() {}

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {}

    @Override
    public void onRetry(int retryNo) {}
}