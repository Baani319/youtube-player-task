package com.example.task5_1c.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task5_1c.R;
import com.example.task5_1c.adapters.NewsAdapter;
import com.example.task5_1c.models.NewsItem;

import java.util.ArrayList;
import java.util.List;

public class NewsDetailFragment extends Fragment {

    public NewsDetailFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detail, container, false);

        ImageView image = view.findViewById(R.id.detail_image);
        TextView desc = view.findViewById(R.id.detail_description);
        RecyclerView recyclerRelated = view.findViewById(R.id.recycler_related_news);

        if (getArguments() != null) {
            image.setImageResource(getArguments().getInt("imageResId"));
            desc.setText(getArguments().getString("description"));
        }

        recyclerRelated.setLayoutManager(new LinearLayoutManager(getContext()));

        List<NewsItem> relatedList = new ArrayList<>();
        relatedList.add(new NewsItem("Related 1", "Extra news info", R.drawable.placeholder));
        relatedList.add(new NewsItem("Related 2", "Something more", R.drawable.placeholder));

        recyclerRelated.setAdapter(new NewsAdapter(relatedList, item -> {
            // Optional: reopen detail with new item
        }));

        return view;
    }
}
