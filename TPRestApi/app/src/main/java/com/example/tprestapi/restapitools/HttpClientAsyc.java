package com.example.tprestapi.restapitools;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.tprestapi.ObjectDto;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class HttpClientAsyc extends HttpClientAsycLoad<URL,Integer, List<ObjectDto>> {
    @Override
    protected List<ObjectDto> doInBackground(URL url) {
        return  null;
    }

    @Override
    protected void onPostExecute(List<ObjectDto> objectDtos) {

    }
}
