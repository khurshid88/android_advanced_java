package com.example.android_advanced_java.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_advanced_java.R;
import com.example.android_advanced_java.activity.NetworkActivity;
import com.example.android_advanced_java.activity.model.Poster;

import java.util.ArrayList;

public class PosterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    NetworkActivity activity;
    ArrayList<Poster> items;

    public PosterAdapter(NetworkActivity activity, ArrayList<Poster> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_poster_list, parent, false);
        return new PosterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Poster poster = items.get(position);

        if (holder instanceof PosterViewHolder) {
            LinearLayout ll_poster = ((PosterViewHolder) holder).ll_poster;
            ll_poster.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    activity.dialogPoster(poster);
                    return false;
                }
            });
            TextView tv_title = ((PosterViewHolder) holder).tv_title;
            TextView tv_body = ((PosterViewHolder) holder).tv_body;
            tv_title.setText(poster.getTitle().toUpperCase());
            tv_body.setText(poster.getBody());
        }
    }

    public class PosterViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public LinearLayout ll_poster;
        public TextView tv_title;
        public TextView tv_body;

        public PosterViewHolder(View v) {
            super(v);
            this.view = v;
            ll_poster = view.findViewById(R.id.ll_poster);
            tv_title = view.findViewById(R.id.tv_title);
            tv_body = view.findViewById(R.id.tv_body);
        }
    }
}
