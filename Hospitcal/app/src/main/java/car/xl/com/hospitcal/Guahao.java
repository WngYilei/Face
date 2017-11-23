package car.xl.com.hospitcal;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.print.PrinterCapabilitiesInfo;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.xl.adapter.DocAdapter;
import com.xl.entity.Doctor;
import com.xl.thread.DocSelectThread;
import com.xl.thread.UserLogin;
import com.xl.thread.Yuyue;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.List;

public class Guahao extends AppCompatActivity {
    private Spinner spinner_room;
    private RadioGroup radioGroup;
    private Button btn_docselect;
    private TextView tv_doc;
    private EditText tv_miaoshu;
    private Button btn_date;
    private TextView tv_date;
    private Button btn_yuyue;
    private RadioButton rb_putong;
    private RadioButton rb_zhuanjia;
    StringBuffer stringBuilder;
    Context context;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guahao);
        context = this;
        init();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(Guahao.this,UserLoginActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    public void init() {
        spinner_room = (Spinner) findViewById(R.id.spinner_room);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        rb_putong = (RadioButton) findViewById(R.id.rb_putong);
        rb_zhuanjia = (RadioButton) findViewById(R.id.rb_zhuanjia);
        btn_docselect = (Button) findViewById(R.id.btn_doc);
        tv_doc = (TextView) findViewById(R.id.tv_doctor);
        tv_miaoshu = (EditText) findViewById(R.id.tv_miaoshu);
        btn_date = (Button) findViewById(R.id.btn_date);
        tv_date = (TextView) findViewById(R.id.tv_date);
        btn_yuyue = (Button) findViewById(R.id.btn_yuyue);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == rb_putong.getId()) {
                    type = rb_putong.getText().toString();
                } else if (checkedId == rb_zhuanjia.getId()) {
                    type = rb_zhuanjia.getText().toString();
                }
            }
        });
        btn_docselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(context);
                View view = inflater.inflate(R.layout.doclv, null);
                final ListView listView = view.findViewById(R.id.doclistview);
                Handler handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        final List<Doctor> list = (List<Doctor>) msg.obj;
                        Log.i("TAG", "handleMessage: "+list);
                        listView.setAdapter(new DocAdapter(context, list));
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                     Doctor doctor = list.get(position);
                                tv_doc.setText(doctor.getDname());
                            }
                        });
                    }
                };
                new DocSelectThread(handler).start();
             listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                 @Override
                 public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                 }
             });
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("医生选择");
                builder.setView(view);
                builder.setNegativeButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }
        });
        String miaoshu = tv_miaoshu.getText().toString();
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                Dialog dateDialog = new DatePickerDialog(Guahao.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                        // TODO Auto-generated method stub
                        stringBuilder = new StringBuffer("");
                        stringBuilder.append(arg1 + "-" + (arg2 + 1) + "-" + arg3);
                        tv_date.setText(stringBuilder);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dateDialog.setTitle("请选择日期");
                dateDialog.show();
            }
        });
          btn_yuyue.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String room = spinner_room.getSelectedItem().toString();
                  String doc = tv_doc.getText().toString();
                  String miaoshu = tv_miaoshu.getText().toString();
                  String date = tv_date.getText().toString();
                  Handler handler = new Handler(){
                      @Override
                      public void handleMessage(Message msg) {
                          int ma = (int) msg.obj;
                          Toast.makeText(context,"您的预约码是"+ma,Toast.LENGTH_SHORT).show();
                      }
                  };
                 new Yuyue(handler,type,room,doc,miaoshu,date).start();
              }
          });

    }
}
