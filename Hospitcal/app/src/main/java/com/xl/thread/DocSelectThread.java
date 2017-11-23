package com.xl.thread;

import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;

import com.xl.entity.Doctor;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/11/10 0010.
 */

public class DocSelectThread extends Thread {
    Handler handler;

    public DocSelectThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        select();
    }

    public void select() {
        List<Doctor> list = new ArrayList<>();
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://192.168.14.178:8080/NewHospitcal/DocSelect")
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            String json = response.body().string();
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Doctor doctor = new Doctor();
                doctor.setDid(jsonObject.getInt("did"));
                doctor.setDname(jsonObject.getString("dname"));
                doctor.setDtitle(jsonObject.getString("title"));
                doctor.setDroom(jsonObject.getString("room"));
                doctor.setImg(jsonObject.getString("dicon"));
                list.add(doctor);
            }
            Message message = new Message();
            message.what = 100;
            message.obj = list;
            handler.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
