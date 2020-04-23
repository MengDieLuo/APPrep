package com.example.edu.News.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.edu.News.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ts on 18-8-20.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHodler> {
    private List<String> categoryList;

    static class ViewHodler extends RecyclerView.ViewHolder {

        TextView category;

        public ViewHodler(View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.category_text);
    }
    }

    public CategoryAdapter(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item, parent, false);
        final ViewHodler hodler = new ViewHodler(view);
        hodler.category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hodler.category.setTextColor(Color.RED);
                hodler.category.setTextSize(20);
            }
        });
        return hodler;
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {
        String category = categoryList.get(position);
        holder.category.setText(category);

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

}
