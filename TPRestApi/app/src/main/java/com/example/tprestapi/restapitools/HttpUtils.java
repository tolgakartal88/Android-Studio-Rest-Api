package com.example.tprestapi.restapitools;

import com.example.tprestapi.ObjectDto;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class HttpUtils {

    public static final String URL = "https://jsonplaceholder.typicode.com/photos";

    public List<ObjectDto> performApiCall() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params =null;
        RequestHandle x = client.get(URL, params, new HttpUtilsListener() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                int scode = statusCode;
                String s= null;
                String s3="";
                List<ObjectDto> resultData = null;
                try {
                    s = new String(responseBody, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
                    //delete backslashes ( \ ) :
                    s = s.replaceAll("[\\\\]{1}[\"]{1}","\"");
                    //delete first and last double quotation ( " ) :
                    s = s.substring(s.indexOf("{"),s.lastIndexOf("}")+1);
                    s = "["+s+"]";
                    resultData = Arrays.asList(new Gson().fromJson(s, ObjectDto[].class));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
            //some code
        });
        return null;
    }
}