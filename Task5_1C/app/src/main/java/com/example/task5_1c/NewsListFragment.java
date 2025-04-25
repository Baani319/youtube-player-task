package com.example.task5_1c.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task5_1c.R;
import com.example.task5_1c.adapters.NewsAdapter;
import com.example.task5_1c.models.NewsItem;

import java.util.ArrayList;
import java.util.List;

public class NewsListFragment extends Fragment {

    private RecyclerView recyclerTopStories, recyclerNews;

    public NewsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);

        recyclerTopStories = view.findViewById(R.id.recycler_top_stories);
        recyclerNews = view.findViewById(R.id.recycler_news);

        // Setup horizontal layout
        recyclerTopStories.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerNews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Dummy data
        List<NewsItem> newsList = new ArrayList<>();
        newsList.add(new NewsItem("Top Story 1", "Breaking news details...", R.drawable.placeholder));
        newsList.add(new NewsItem("Top Story 2", "More news content here...", R.drawable.placeholder));

        // Handle click to navigate to NewsDetailFragment
        NewsAdapter.OnItemClickListener listener = item -> {
            Bundle bundle = new Bundle();
            bundle.putString("title", item.getTitle());
            bundle.putString("description", item.getDescription());
            bundle.putInt("imageResId", item.getImageResId());

            NewsDetailFragment detailFragment = new NewsDetailFragment();
            detailFragment.setArguments(bundle);

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, detailFragment)
                    .addToBackStack(null)
                    .commit();
        };

        NewsAdapter adapter = new NewsAdapter(newsList, listener);
        recyclerTopStories.setAdapter(adapter);
        recyclerNews.setAdapter(adapter);

        return view;
    }
}
