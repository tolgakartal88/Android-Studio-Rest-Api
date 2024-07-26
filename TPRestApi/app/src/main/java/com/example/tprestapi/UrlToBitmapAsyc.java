package com.example.tprestapi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class UrlToBitmapAsyc implements Runnable {

    String _url;
    Context _context;
    ImageView _imageView;
    public UrlToBitmapAsyc(Context context, String url, ImageView imageView) {
        _url=url;
        _context=context;
        _imageView = imageView;
    }

    @Override
    public void run() {

        try {
            URL connection = new URL(this._url);

            InputStream input = connection.openStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Bitmap resized = Bitmap.createScaledBitmap(myBitmap, 150, 150, true);
            _imageView.setImageBitmap(resized);
        } catch (Exception ignored) {

        }
    }
}
