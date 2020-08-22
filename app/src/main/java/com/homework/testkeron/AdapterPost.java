package com.homework.testkeron;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.homework.testkeron.model.PostModel;

import java.util.ArrayList;

public class AdapterPost extends RecyclerView.Adapter<AdapterPost.PostViewHolder> {

    private ArrayList<PostModel> items = new ArrayList<>();

    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setData(ArrayList<PostModel> data) {
        items = data;
        notifyDataSetChanged();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textAuthor;
        private TextView textCommentCount;
        private TextView textPostTime;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textAuthor = itemView.findViewById(R.id.text_author);
            textCommentCount = itemView.findViewById(R.id.text_comment_count);
            textPostTime = itemView.findViewById(R.id.text_post_time);
        }

        public void bind(PostModel model) {
            Context context = textCommentCount.getContext();
            textAuthor.setText(model.getAuthor());
            long hours = (System.currentTimeMillis() - model.getPostTime()) / (1000*60*60);
//            textPostTime.setText("" + hours + " hours ago");
            int resHours = hours == 1 ? R.string.hour_ago : R.string.hours_ago;
            textPostTime.setText(context.getString(resHours, hours));
            textCommentCount.setText(context.getString(R.string.comment_count, model.getCommentCount()));

//            textCommentCount.setText("Comment count: " + model.getCommentCount());

            Glide
                    .with(context)
                    .load(model.getThumbnail())
                    .centerCrop()
                    .into(imageView);
        }
    }
}
