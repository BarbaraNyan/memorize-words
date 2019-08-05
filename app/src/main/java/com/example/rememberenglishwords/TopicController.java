package com.example.rememberenglishwords;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NYAN on 27.06.2019.
 */

public class TopicController extends RecyclerView.Adapter {

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
//
//    public String getCollectionName(){
//        return collectionName;
//    }
}
