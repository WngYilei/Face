package com.xl.thread;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.xl.entity.*;
import com.xl.entity.Status;

import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/11/10 0010.
 */

public class UserLogin extends Thread {
    Handler handler;
    String email;
    String passwrod;

    public UserLogin(Handler handler, String email, String passwrod) {
        this.handler = handler;
        this.email = email;
        this.passwrod = passwrod;
    }

    @Override
    public void run() {
       login(email,passwrod);
    }
    public void login(String email, String passwrod){
        try{
            OkHttpClient okHttpClient = new OkHttpClient();
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("email",email);
            builder.add("password",passwrod);
            Request request = new Request.Builder()
                    .url("http://192.168.14.178:8080/NewHospitcal/Status")
                    .post(builder.build())
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            String json = response.body().string();
            JSONObject jsonObject = new JSONObject(json);
            Status status = new Status();
            status.setResult(jsonObject.getBoolean("Result"));
            status.setSta(jsonObject.getString("status"));
            Message m = new Message();
            m.what=100;
            m.obj=status;
            handler.sendMessage(m);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
