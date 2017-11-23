package com.xl.toast;

import okhttp3.Cache;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/11/14 0014.
 */

public class OkhttpPost {
    String json;
    public String post(FormBody.Builder builder,String url){
        try{
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .post(builder.build())
                    .build();
            Response response = okHttpClient.newCall(request).execute();
           json = response.body().string();

    }catch(Exception e){
        e.printStackTrace();
    }

        return json;
    }
}
