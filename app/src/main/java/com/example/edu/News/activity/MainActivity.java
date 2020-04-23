package com.example.edu.News.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.edu.News.Fragment.HomeFragment;
import com.example.edu.News.Fragment.MineFragment;
import com.example.edu.News.Fragment.SetFragment;
import com.example.edu.News.R;
import com.example.edu.News.Utils.ApplicationUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
    private List<Fragment> fragmentList = new ArrayList<>();

    private HomeFragment mainFragment;
    private SetFragment settingFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initview();
        initFragment();
        ApplicationUtil.getInstance().addActivity(this);
    }

    private void initview() {
        textHome.setTextColor(Color.rgb(200, 0, 0));
        imgHome.setImageResource(R.drawable.main_selected);
    }


    private void initFragment() {
        mainFragment = new HomeFragment();
        addFragment(mainFragment);
        showFragment(mainFragment);

    }

    /*添加fragment*/
    private void addFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.add(R.id.main_content, fragment).commit();
            fragmentList.add(fragment);
        }
    }

    /*显示fragment*/
    private void showFragment(Fragment fragment) {
        for (Fragment frag : fragmentList) {
            if (frag != fragment) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.hide(frag).commit();
            }
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.show(fragment).commit();
    }

    @OnClick({R.id.layout_home, R.id.layout_set, R.id.layout_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_home:

                if (mainFragment == null) {
                    mainFragment = new HomeFragment();
                }
                addFragment(mainFragment);
                showFragment(mainFragment);
                textHome.setTextColor(Color.RED);
                textSetting.setTextColor(Color.BLACK);
                textMine.setTextColor(Color.BLACK);

                imgHome.setImageResource(R.drawable.main_selected);
                imgSet.setImageResource(R.drawable.set);
                imgMine.setImageResource(R.drawable.mine);

                break;
            case R.id.layout_set:

                if (settingFragment == null) {
                    settingFragment = new SetFragment();
                }
                addFragment(settingFragment);
                showFragment(settingFragment);
                textSetting.setTextColor(Color.RED);
                textHome.setTextColor(Color.BLACK);
                textMine.setTextColor(Color.BLACK);

                imgHome.setImageResource(R.drawable.main);
                imgSet.setImageResource(R.drawable.setting_selected);
                imgMine.setImageResource(R.drawable.mine);

                break;
            case R.id.layout_mine:

                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                }
                addFragment(mineFragment);
                showFragment(mineFragment);
                textMine.setTextColor(Color.RED);
                textHome.setTextColor(Color.BLACK);
                textSetting.setTextColor(Color.BLACK);

                imgHome.setImageResource(R.drawable.main);
                imgSet.setImageResource(R.drawable.set);
                imgMine.setImageResource(R.drawable.mine_selected);

                break;
        }
    }
}
