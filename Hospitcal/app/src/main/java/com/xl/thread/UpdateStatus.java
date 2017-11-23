package com.xl.thread;

import android.app.DownloadManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/11/10 0010.
 */

public class UpdateStatus extends Thread {
    Handler handler;
    String email;

    public UpdateStatus(Handler handler, String email) {
        this.handler = handler;
        this.email = email;
    }

    @Override
    public void run() {
        update(email);
    }

    public void update(String email) {
        try {
            String path = "http://192.168.14.178:8080/NewHospitcal/UpdteStatus";
            StringBuilder sb = new StringBuilder(path);
            sb.append("?");
            sb.append("email=").append(email);
            URL url = new URL(sb.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            if (conn.getResponseCode() == 200) {
                Message msg = new Message();
                msg.what = 100;
                msg.obj = true;
                handler.sendMessage(msg);
            } else {
                Message msg = new Message();
                msg.what = 100;
                msg.obj = false;
                handler.sendMessage(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
