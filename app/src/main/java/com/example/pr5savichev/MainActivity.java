package com.example.pr5savichev;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView tv_timer;
    int time = 0;
    Timer timer;
    TimerTask mTimerTask;
    boolean active = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_timer = findViewById(R.id.tv_timer);
    }

    public void OnStart(View view){
        active = !active;
        if(active){
            if(timer != null){
                timer.cancel();
            }

            timer = new Timer();
            mTimerTask = new MyTimerTask();

            timer.schedule(mTimerTask, 0, 1000);
            Button button = findViewById(R.id.button);
            button.setText("СТОП");
        }
        else{
            if(timer!=null){
                timer.cancel();
            }
            timer = null;

            Button button = findViewById(R.id.button);
            button.setText("ПРОДОЛЖИТЬ");
        }
    }

    public void OnClear(View view){
        if(timer!=null){
            timer.cancel();
        }
        timer=null;
        time = 0;
        tv_timer.setText("00:00:00");

        Button button = findViewById(R.id.button);
        button.setText("НАЧАТЬ");
    }

    class MyTimerTask extends TimerTask{
        @Override
        public void run(){
            time++;

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String s_second = "";

                    int hour = time/60/60;
                    int min = time/60;
                    int second = time;
                    if(second < 10) s_second = "0"+second;
                    else s_second = String.valueOf(second);

                    String s_min = "";
                    if(min < 10) s_min = "0"+min;
                    else s_min = String.valueOf(min);

                    String s_hour = "";
                    if(hour < 10) s_hour = "0"+hour;
                    else s_hour = String.valueOf(hour);

                    tv_timer.setText(s_hour + ":" + s_min + ":" + s_second);
                }
            });
        }
    }
}