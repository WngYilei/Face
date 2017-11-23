package com.xl.thread;

import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import java.io.InputStream;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/11/10 0010.
 */

public class BitmapThread extends Thread {
    Handler handler;
    String path;

    public BitmapThread(Handler handler, String path) {
        this.handler = handler;
        this.path = path;
    }

    @Override
    public void run() {
        bit(path);
    }

    public void bit(String path) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(path)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            InputStream is = response.body().byteStream();
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            Message message = new Message();
            message.what = 100;
            message.obj = bitmap;
            handler.sendMessage(message);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
