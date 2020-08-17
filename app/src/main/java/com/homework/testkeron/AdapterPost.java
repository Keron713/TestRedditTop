package com.homework.testkeron;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.homework.testkeron.model.PostModel;

import java.util.ArrayList;

public class AdapterPost extends RecyclerView.Adapter<AdapterPost.ViewHolder> {

    private ArrayList<PostModel> items = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textAuthor;
        private TextView textCommentCount;
        private TextView textPostTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textAuthor = itemView.findViewById(R.id.text_author);
            textCommentCount = itemView.findViewById(R.id.text_comment_count);
            textPostTime = itemView.findViewById(R.id.text_post_time);
        }

        public void bind(PostModel model) {

        }
    }
}
