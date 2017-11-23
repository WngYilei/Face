package car.xl.com.hospitcal;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xl.entity.Status;
import com.xl.thread.UpdateStatus;
import com.xl.thread.UserLogin;
import com.xl.toast.Toa;

public class UserLoginActivity extends AppCompatActivity {
    private Button login;
    private Button register;
    private EditText et_logemail;
    private EditText et_logpassword;
    private Button btn_updatestatus;
    Context context;
    int count;
    private long starttime;
    Status status;
    Toa toa = new Toa();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        context = this;
        login = (Button) findViewById(R.id.user_login);
        register = (Button) findViewById(R.id.user_register);
        et_logemail = (EditText) findViewById(R.id.et_logemail);
        et_logpassword = (EditText) findViewById(R.id.et_logpassword);
        btn_updatestatus = (Button) findViewById(R.id.btn_updatestatus);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                String email = et_logemail.getText().toString();
                String password = et_logpassword.getText().toString();
                if (count > 3) {
                    Handler handler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            Boolean a = (Boolean) msg.obj;
                            if (a) {
                                toa.toast(context, "此账号被冻结");
                            }
                        }
                    };
                    new UpdateStatus(handler, email).start();
                } else {
                    Handler handler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            status = new Status();
                            status = (Status) msg.obj;
                            Boolean a = status.getResult();
                            String sta = status.getSta();
                            if (sta.equals("0")) {
                                toa.toast(context, "此账号被冻结");
                            } else {
                                if (a) {
                                    toa.toast(context, "登陆成功");
                                    Intent intent = new Intent();
                                    intent.setClass(UserLoginActivity.this, Guahao.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    toa.toast(context, "登陆失败");
                                }
                            }
                        }
                    };
                    new UserLogin(handler, email, password).start();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(UserLoginActivity.this, Main2Activity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_updatestatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(UserLoginActivity.this, Start.class);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        Long curr = System.currentTimeMillis();
        if (curr - starttime > 3000) {
            starttime = curr;
            toa.toast(context, "再次点击退出");
        } else {
            super.onBackPressed();
        }

    }
}
