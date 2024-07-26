package com.example.tprestapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tprestapi.restapitools.HttpClientAsyc;
import com.example.tprestapi.restapitools.HttpUtils;
import com.example.tprestapi.restapitools.HttpUtilsListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    public static <T> List<T> convertArrayToList(T array[])
    {

        // Create an empty List
        List<T> list = new ArrayList<>();

        // Iterate through the array
        for (T t : array) {
            // Add each element into the list
            list.add(t);
        }

        // Return the converted List
        return list;
    }

    static List<ObjectDto> listObjectDto = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        final String[] URL = {"https://jsonplaceholder.typicode.com/photos"};
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params =null;
        RequestHandle x = client.get(URL[0], params, new HttpUtilsListener() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                int scode = statusCode;
                String s;
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
                Type listType = new TypeToken<List<ObjectDto>>() {}.getType();
                List<ObjectDto> result;
                result = new Gson().fromJson(s, listType);
                listObjectDto =result;
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                int i=0;
            }
            //some code
        });

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView list = findViewById(R.id.liste);
                List<ObjectDto> adp=listObjectDto;
                //List<ObjectDto> adp=listObjectDto.stream().limit(10).collect(Collectors.toList());
                ObjectDtoAdapter dtoAdapter = new ObjectDtoAdapter(MainActivity.this,adp);
                list.setAdapter(dtoAdapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                list.setLayoutManager(linearLayoutManager);
            }
        });

    }


}