package com.example.edu.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.widget.Button;

import com.example.edu.myapplication.R;
import com.example.edu.myapplication.Utils.SharedPre;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class splashActivity extends AppCompatActivity {

    @BindView(R.id.btn_jump)
    Button btnJump;

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                //判断用户是否登录
                boolean userIsLogin = (boolean) SharedPre.getParam(splashActivity.this,
                        SharedPre.IS_LOGIN, false);
                if (userIsLogin) {
                    Intent intent = new Intent(splashActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(splashActivity.this, lodingActivity.class);
                    startActivity(intent);
                }

                finish();
            } else if (msg.what == 0) {
                thread.interrupt();
            }

        }

    };

    final Message message = new Message();
    final Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                message.what = 1;
                handler.sendMessage(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        thread.start();

    }


    @OnClick(R.id.btn_jump)
    public void onViewClicked() {

        message.what = 0;
        handler.sendMessage(message);
        //判断用户是否登录
        boolean userIsLogin = (boolean) SharedPre.getParam(splashActivity.this,
                SharedPre.IS_LOGIN, false);
        if (userIsLogin) {
            Intent intent = new Intent(splashActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(splashActivity.this, lodingActivity.class);
            startActivity(intent);
        }
        finish();
    }
}
