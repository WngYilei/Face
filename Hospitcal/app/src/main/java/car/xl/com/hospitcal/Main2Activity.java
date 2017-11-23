package car.xl.com.hospitcal;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.print.PrintAttributes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xl.thread.UserRegister;

import java.io.InputStream;


public class Main2Activity extends AppCompatActivity {
    private Button btn;
    private EditText et_id;
    private EditText et_email;
    private EditText et_password;
    private EditText et_name;
    private EditText et_sex;
    private EditText et_age;
    private EditText et_idcard;
    private EditText et_status;
    private  Button btn_back;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null) {
            actionBar.hide();
        }
        context=this;
        et_id = (EditText) findViewById(R.id.et_uid);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        et_name = (EditText) findViewById(R.id.et_name);
        et_sex = (EditText) findViewById(R.id.et_sex);
        et_age = (EditText) findViewById(R.id.et_age);
        et_idcard = (EditText) findViewById(R.id.et_idcard);
        et_status = (EditText) findViewById(R.id.et_status);
         btn_back  = (Button) findViewById(R.id.btn_back);
        btn = (Button) findViewById(R.id.user_register1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = et_id.getText().toString();
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();
                String name = et_name.getText().toString();
                String sex = et_sex.getText().toString();
                String age = et_age.getText().toString();
                String idcard = et_idcard.getText().toString();
                String status = et_status.getText().toString();
                String ema = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
                String pwd = "^\\d{6,}$";
                if (email.matches(ema)&&password.matches(pwd)){
                Handler handler  = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        Boolean a = (Boolean) msg.obj;
                        if (a){
                            Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "注册失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                };
                new UserRegister(handler,id,email,password,name,sex,age,idcard,status).start();

            }else {
                    Toast.makeText(context, "邮箱或密码格式不正确", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Main2Activity.this,UserLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(Main2Activity.this,UserLoginActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
