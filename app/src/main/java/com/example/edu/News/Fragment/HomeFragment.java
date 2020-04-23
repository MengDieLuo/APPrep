package com.example.edu.News.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.edu.News.Adapter.FragmentAdapter;
import com.example.edu.News.R;
import com.example.edu.News.Utils.Count;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


public class HomeFragment extends Fragment {

    private View view;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> titleList;
    private List<Fragment> fragmentList;

    private FragmentAdapter myfragmentAdapter;

    private TeachFragment teach_fragment;
    private EnterFragment2 enter_fragment;
    private SportFragment sport_fragment;
    private MiniFragment mini_fragment;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initView();
        fragmentChange();
        Count.getInstance().setTime(System.currentTimeMillis());
        return view;
    }
    private void initView() {
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);

    }
    private void fragmentChange() {
        fragmentList = new ArrayList<>();

        teach_fragment = new TeachFragment();
        enter_fragment = new EnterFragment2();
        sport_fragment = new SportFragment();
        mini_fragment = new MiniFragment();


        fragmentList.add(teach_fragment);
        fragmentList.add(enter_fragment);
        fragmentList.add(sport_fragment);
        fragmentList.add(mini_fragment);

        titleList = new ArrayList<>();
        titleList.add("科技");
        titleList.add("娱乐");
        titleList.add("体育");
        titleList.add("奇闻");

        myfragmentAdapter = new FragmentAdapter(getActivity().getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(myfragmentAdapter);

        //将tabLayout与viewPager连起来
        tabLayout.setupWithViewPager(viewPager);
    }

}
