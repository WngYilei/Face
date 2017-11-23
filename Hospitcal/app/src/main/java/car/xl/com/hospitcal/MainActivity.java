package car.xl.com.hospitcal;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar bar = getSupportActionBar();
        if (bar!=null){
            bar.hide();
        }
        setContentView(R.layout.activity_main);
        Timer timer = new Timer();
        TimerTask tk = new TimerTask() {
            Intent intent = new Intent(MainActivity.this,Welcome.class);
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        };
        timer.schedule(tk,3000);
    }
}
