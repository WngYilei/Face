  package com.example.facedemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


import com.example.facedemo.faceservice.FaceService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;


  public class MainActivity extends AppCompatActivity implements View.OnClickListener{

        private String FILE_PATH = Environment.getExternalStorageDirectory().toString();
      private Button mainBtnCheck;
      private ImageButton mainImgbtnBg01, mainImgbtnBg02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBtnCheck = (Button) findViewById(R.id.main_btn_check);
        mainImgbtnBg01 = (ImageButton) findViewById(R.id.main_imgbtn_bg01);
        mainImgbtnBg02 = (ImageButton) findViewById(R.id.main_imgbtn_bg02);
        mainImgbtnBg01.setOnClickListener(this);
        mainImgbtnBg02.setOnClickListener(this);
        mainBtnCheck.setOnClickListener(this);
    }

      @Override
      public void onClick(View view) {
          switch (view.getId()){
              case R.id.main_btn_check:
                  Toast.makeText(this,"线程开始",Toast.LENGTH_LONG).show();
                  new Thread(){
                      public void run(){
                          handler.sendEmptyMessage(0);
                          String result = FaceService.match(FILE_PATH+"/image01.jpg",FILE_PATH+"/image02.jpg");

                          try {
                              JSONObject jo = new JSONObject(result);
                              String re = jo.getString("result");
                              JSONArray ja = new JSONArray(re);
                              JSONObject jo1 = ja.getJSONObject(0);
                              String val = jo1.getString("score");
                              Message msg = new Message();
                              msg.obj = "相似度："+val.substring(0,5);
                              msg.what = 1;
                              handler.sendMessage(msg);
                          } catch (JSONException e) {
                              e.printStackTrace();
                          }
                      }
                  }.start();

                  break;
              case R.id.main_imgbtn_bg01:
                  myCamera("/image01.jpg", 0);
                  break;
              case R.id.main_imgbtn_bg02:
                  myCamera("/image02.jpg", 1);
                  break;
          }

      }

      private Handler handler = new Handler(){
          @Override
          public void handleMessage(Message msg) {
              if(msg.what == 1){

                  String txt = msg.obj.toString();
                  mainBtnCheck.setText(txt);
              }else if(msg.what == 0){
                  mainBtnCheck.setText("正在比较请稍后……");
                  mainBtnCheck.setEnabled(false);
              }
          }
      };

      @Override
      protected void onActivityResult(int requestCode, int resultCode, Intent data) {
          Log.i("main","系统牌照完成");
          if(requestCode == 0){
              File file = new File(FILE_PATH+"/image01.jpg");
              Uri uri = Uri.fromFile(file);
              mainImgbtnBg01.setImageURI(uri);
              mainImgbtnBg01.setEnabled(false);
          } else if (requestCode == 1){
              File file = new File(FILE_PATH+"/image02.jpg");
              Uri uri = Uri.fromFile(file);
              mainImgbtnBg02.setImageURI(uri);
              mainImgbtnBg02.setEnabled(false);
          }
          super.onActivityResult(requestCode, resultCode, data);
      }

      public void myCamera(String imageName, int code ){
          // 使用Intent对象去调用系统的Camera
          Intent intent = new Intent();
          intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
          intent.addCategory(Intent.CATEGORY_DEFAULT);
          // 根据文件地址创建文件
          File file = new File(FILE_PATH+imageName);
          Uri uri = Uri.fromFile(file);
          intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
          startActivityForResult(intent, code);
      }

  }
