package com.example.rememberenglishwords;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.factor.bouncy.BouncyRecyclerView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NYAN on 27.06.2019.
 */

public class TopicController extends BouncyRecyclerView.Adapter {

    public List<Topic> topicList = new ArrayList<>();
    String collectionName;
    //как топики выглядят
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = R.layout.activity_all_words_topic;
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        return new TopicView(view);
    }

    //что за данные там лежат
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        Topic topic = topicList.get(position);
        ((TopicView)holder).bind(topic); //bind - привязать
//        ((TopicView) holder).buttonView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                collectionName = ((TopicView) holder).topicTextAbout.getText().toString();
//            }
//        });
    }

    //сколько
    @Override
    public int getItemCount() {
        return topicList.size();
    }

    @Override
    public void onItemMoved(int fromPosition, int toPosition) {
        Topic item = topicList.remove(fromPosition);
        topicList.add(toPosition, item);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemReleased(@NotNull RecyclerView.ViewHolder viewHolder) {
        viewHolder.itemView.setAlpha(1f);
    }

    @Override
    public void onItemSelected(@Nullable RecyclerView.ViewHolder viewHolder) {
        viewHolder.itemView.setAlpha(0.5f);
    }

    @Override
    public void onItemSwipedToEnd(@NotNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public void onItemSwipedToStart(@NotNull RecyclerView.ViewHolder viewHolder, int i) {
    }


//
//    public String getCollectionName(){
//        return collectionName;
//    }
}
