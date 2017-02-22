package com.example.volmopc1.threadtest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    Handler handler;
    ProgressBar progressbar;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressbar = (ProgressBar) findViewById(R.id.progressBar);
        my m = new my();
        thread = new Thread(m);
        thread.start();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                progressbar.setProgress(msg.arg1);
            }
        };
    }

    class my implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000000; i++) {
                Message message = Message.obtain();
                message.arg1 = i;
                handler.sendMessage(message);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
