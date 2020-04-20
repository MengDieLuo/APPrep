package com.example.edu.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.edu.myapplication.Fragment.HomeFragment;
import com.example.edu.myapplication.Fragment.MineFragment;
import com.example.edu.myapplication.Fragment.SetFragment;
import com.example.edu.myapplication.R;
import com.example.edu.myapplication.Utils.ApplicationUtil;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_content)
    FrameLayout mainContent;
    @BindView(R.id.img_home)
    ImageView imgHome;
    @BindView(R.id.text_home)
    TextView textHome;
    @BindView(R.id.layout_home)
    LinearLayout layoutHome;
    @BindView(R.id.img_set)
    ImageView imgSet;
    @BindView(R.id.text_setting)
    TextView textSetting;
    @BindView(R.id.layout_set)
    LinearLayout layoutSet;
    @BindView(R.id.img_mine)
    ImageView imgMine;
    @BindView(R.id.text_mine)
    TextView textMine;
    @BindView(R.id.layout_mine)
    LinearLayout layoutMine;


    private HomeFragment mainFragment;
    private SetFragment settingFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);

        ApplicationUtil.getInstance().addActivity(this);
    }


    @OnClick({R.id.layout_home, R.id.layout_set, R.id.layout_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_home:

                break;
            case R.id.layout_set:

                break;
            case R.id.layout_mine:

                break;
        }
    }
}
