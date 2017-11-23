package com.xl.thread;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.crypto.AEADBadTagException;

/**
 * Created by Administrator on 2017/11/10 0010.
 */

public class UserRegister extends Thread {
    Handler handler;
    String id;
    String email;
    String passwrod;
    String name;
    String sex;
    String age;
    String idcard;
    String status;

    public UserRegister(Handler handler, String id, String email, String passwrod, String name, String sex, String age, String idcard, String status) {
        this.handler = handler;
        this.id = id;
        this.email = email;
        this.passwrod = passwrod;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.idcard = idcard;
        this.status = status;
    }

    @Override
    public void run(){
        login(id,email,passwrod,name,sex,age,idcard,status);
    }
    public void login(String id, String email, String passwrod, String name, String sex, String age, String idcard, String status){
        try {
            String path = "http://192.168.14.178:8080/NewHospitcal/UserRegister";
            StringBuilder sb = new StringBuilder(path);
            sb.append("?");
            sb.append("id=").append(id).append("&");
            sb.append("email=").append(email).append("&");
            sb.append("password=").append(passwrod).append("&");
            sb.append("name=").append(name).append("&");
            sb.append("sex=").append(sex).append("&");
            sb.append("age=").append(age).append("&");
            sb.append("idcard=").append(idcard).append("&");
            sb.append("status=").append(status);
            URL url = new URL(sb.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            if (conn.getResponseCode()==200){
                Message message = new Message();
                message.what=100;
                message.obj=true;
                handler.sendMessage(message);
            }else{
                Message message = new Message();
                message.what=100;
                message.obj=false;
                handler.sendMessage(message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
