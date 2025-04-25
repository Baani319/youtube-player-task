package com.example.task5_1c.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.task5_1c.R;
import com.example.task5_1c.models.VideoEntity;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder> {

    private List<VideoEntity> playlist;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String url);
    }

    public PlaylistAdapter(List<VideoEntity> playlist, OnItemClickListener listener) {
        this.playlist = playlist;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.playlist_item, parent, false);
        return new PlaylistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistViewHolder holder, int position) {
        VideoEntity video = playlist.get(position);
        holder.urlText.setText(video.videoUrl);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(video.videoUrl));
    }

    @Override
    public int getItemCount() {
        return playlist.size();
    }

    public static class PlaylistViewHolder extends RecyclerView.ViewHolder {
        TextView urlText;

        public PlaylistViewHolder(@NonNull View itemView) {
            super(itemView);
            urlText = itemView.findViewById(R.id.url_text);
        }
    }
}
