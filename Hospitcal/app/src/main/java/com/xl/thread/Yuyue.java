package com.xl.thread;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Random;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2017/11/10 0010.
 */

public class Yuyue extends Thread {
    Handler handler;
    String room;
    String type;
    String dcotor;
    String miaoshu;
    String yuyuetime;

    public Yuyue(Handler handler, String room, String type, String dcotor, String miaoshu, String yuyuetime) {
        this.handler = handler;
        this.room = room;
        this.type = type;
        this.dcotor = dcotor;
        this.miaoshu = miaoshu;
        this.yuyuetime = yuyuetime;
    }

    @Override
    public void run() {
        Yuyue(room, type, dcotor, miaoshu, yuyuetime);
    }

    public void Yuyue(String room, String type, String dcotor, String miaoshu, String yuyuetime) {
        try {
            String path = "http://192.168.14.178:8080/NewHospitcal/Guaha";
            StringBuilder sb = new StringBuilder(path);
            sb.append("?");
            sb.append("room=").append(URLDecoder.decode(room,"GB2312")).append("&");
            sb.append("type=").append(URLDecoder.decode(type,"GB2312")).append("&");
            sb.append("doctor=").append(URLDecoder.decode(dcotor,"GB2312")).append("&");
            sb.append("miaoshu=").append(URLDecoder.decode(miaoshu,"GB2312")).append("&");
            sb.append("yuyuetime=").append(URLDecoder.decode(yuyuetime,"GB2312")).append("&");
            URL url =new URL(sb.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            if (conn.getResponseCode()==200){
                Random random = new Random();
                Message message = new Message();
                message.what = 100;
                message.obj = random.nextInt(1000);
                handler.sendMessage(message);
            }


//            OkHttpClient okHttpClient = new OkHttpClient();
//            FormBody.Builder builder = new FormBody.Builder();
//            builder.add("room", room);
//            builder.add("type", type);
//            builder.add("doctor", dcotor);
//            builder.add("miaoshu", miaoshu);
//            builder.add("yuyuetime", yuyuetime);
//            Request request = new Request.Builder()
//                    .url("http://192.168.14.178:8080/NewHospitcal/Guahao")
//                    .post(builder.build())
//                    .build();
//            okHttpClient.newCall(request).execute();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
