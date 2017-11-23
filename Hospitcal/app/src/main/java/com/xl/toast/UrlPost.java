package com.xl.toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/11/14 0014.
 */

public class UrlPost {
    String json;

    public String up(String url, String name, String password) {
        try {
            StringBuilder sb = new StringBuilder(url);
            sb.append("?");
            sb.append("email=").append(name).append("&");
            sb.append("password").append(password);
            URL url1 = new URL(sb.toString());
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(is);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuffer buffer = new StringBuffer();
                String ss;
                while ((ss = bufferedReader.readLine()) != null)
                    buffer.append(ss);
                bufferedReader.close();
                inputStreamReader.close();
                is.close();
                json = buffer.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;

    }
}
