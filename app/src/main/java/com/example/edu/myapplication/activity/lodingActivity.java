package com.example.edu.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.edu.myapplication.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class lodingActivity extends AppCompatActivity {

    @BindView(R.id.loginbtn)
    Button loginbtn;
    @BindView(R.id.registerbtn)
    Button registerbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loding);
        ButterKnife.bind(this);
    }





    @OnClick({R.id.loginbtn, R.id.registerbtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.loginbtn:
                Intent loginintent = new Intent(lodingActivity.this, LoginActivity.class);
                startActivity(loginintent);
                finish();
                break;
            case R.id.registerbtn:
                Intent regintent = new Intent(lodingActivity.this, RegisterActivity.class);
                startActivity(regintent);
                finish();
                break;
        }
    }
}
