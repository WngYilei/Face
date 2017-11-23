package car.xl.com.hospitcal;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xl.thread.UpdateStatusThread;
import com.xl.thread.UserLogin;
import com.xl.entity.Status;
import com.xl.toast.Toa;


public class Start extends AppCompatActivity {
    private EditText et_zhanghao;
    private EditText et_mima;
    private Button btn_jiechu;
    Context context;
    Toa toa = new Toa();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        context = this;
        et_zhanghao = (EditText) findViewById(R.id.et_zhanghao);
        et_mima = (EditText) findViewById(R.id.et_mima);
        btn_jiechu = (Button) findViewById(R.id.jiechu);
        btn_jiechu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String zhanghao = et_zhanghao.getText().toString();
                String mima = et_mima.getText().toString();
                Handler handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        Status status = (Status) msg.obj;
                        if (status.getResult()) {
                            Handler handler1 = new Handler() {
                                @Override
                                public void handleMessage(Message msg) {
                                    Boolean a = (Boolean) msg.obj;
                                    if (a) {
                                        toa.toast(context,"解除冻结成功");
                                    }
                                }
                            };
                            new UpdateStatusThread(handler1,zhanghao).start();
                        } else {
                           toa.toast(context,"账号或密码不正确");
                        }
                    }
                };
                new UserLogin(handler, zhanghao, mima).start();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(Start.this,UserLoginActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
