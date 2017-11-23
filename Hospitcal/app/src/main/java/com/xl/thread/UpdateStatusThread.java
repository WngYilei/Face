package com.xl.thread;

import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2017/11/11 0011.
 */

public class UpdateStatusThread extends Thread {
    Handler handler;
    String email;

    public UpdateStatusThread(Handler handler, String email) {
        this.handler = handler;
        this.email = email;
    }

    @Override
    public void run() {
        update(email);
    }

    public void update(String email) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("email", email);
            Request request = new Request.Builder()
                    .url("http://192.168.14.178:8080/NewHospitcal/UpdateStatus")
                    .post(builder.build())
                    .build();
            okHttpClient.newCall(request).execute();
            Message message  = new Message();
            message.what=100;
            message.obj=true;
            handler.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
