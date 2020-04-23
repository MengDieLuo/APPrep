package com.example.edu.News.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.edu.News.Adapter.CategoryAdapter;
import com.example.edu.News.Adapter.NewsAdapter;
import com.example.edu.News.Bean.News;
import com.example.edu.News.R;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class BckFragment extends Fragment implements NewsAdapter.CallBack {


    @BindView(R.id.list_view)
    ListView listView;


    private View view;

    private List<String> categoryList = new ArrayList<>();
    private List<News> newsList = new ArrayList<>();

    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;

    private NewsAdapter newsAdapter;

    public BckFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bck, container, false);
        initCategory();
        initView();

        newsAdapter = new NewsAdapter(getContext(), R.layout.news_item, newsList, this);
        listView = view.findViewById(R.id.list_view);
        listView.setAdapter(newsAdapter);

        return view;

    }


    private void initView() {
        recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(categoryList);
        recyclerView.setAdapter(categoryAdapter);
    }


    private void initCategory() {
        String[] categories = {
                "社会", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚"
        };
        for (int i = 0; i < categories.length; i++) {
            String category = categories[i];
            categoryList.add(category);
        }
    }


    @Override
    public void click(View view) {
        Toast.makeText(getContext(), "该新闻已删除！", Toast.LENGTH_SHORT).show();
        newsList.remove(Integer.parseInt(view.getTag().toString()));
        newsAdapter.notifyDataSetChanged();
    }
}
