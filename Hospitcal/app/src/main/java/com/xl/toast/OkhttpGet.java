package com.xl.toast;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/11/13 0013.
 */

public class OkhttpGet {
    String json;

    public String get(String url) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            json = response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

}
