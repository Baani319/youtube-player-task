package com.example.task5_1c.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task5_1c.R;
import com.example.task5_1c.adapters.PlaylistAdapter;
import com.example.task5_1c.database.VideoDatabase;
import com.example.task5_1c.models.VideoEntity;

import java.util.List;

public class iTubeFragment extends Fragment {

    private WebView webView;
    private EditText inputUrl;
    private Button btnLoad;
    private RecyclerView playlistRecycler;
    private PlaylistAdapter playlistAdapter;
    private VideoDatabase db;

    public iTubeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_itube, container, false);

        webView = view.findViewById(R.id.web_view);
        inputUrl = view.findViewById(R.id.input_url);
        btnLoad = view.findViewById(R.id.btn_load);
        playlistRecycler = view.findViewById(R.id.playlist_recycler);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        db = VideoDatabase.getInstance(requireContext());

        btnLoad.setOnClickListener(v -> {
            String url = inputUrl.getText().toString().trim();
            if (!TextUtils.isEmpty(url)) {
                String embedUrl = convertToEmbedUrl(url);
                loadVideo(embedUrl);
                db.videoDao().insert(new VideoEntity(url));
                loadPlaylist();
                inputUrl.setText("");
            } else {
                Toast.makeText(getContext(), "Please enter a video URL", Toast.LENGTH_SHORT).show();
            }
        });

        playlistRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        loadPlaylist();

        return view;
    }

    private void loadVideo(String url) {
        String html = "<iframe width=\"100%\" height=\"100%\" src=\"" + url + "\" frameborder=\"0\" allowfullscreen></iframe>";
        webView.loadData(html, "text/html", "utf-8");
    }

    private String convertToEmbedUrl(String url) {
        if (url.contains("watch?v=")) {
            return url.replace("watch?v=", "embed/");
        }
        return url;
    }

    private void loadPlaylist() {
        List<VideoEntity> allVideos = db.videoDao().getAllVideos();
        playlistAdapter = new PlaylistAdapter(allVideos, this::loadVideo);
        playlistRecycler.setAdapter(playlistAdapter);
    }
}
