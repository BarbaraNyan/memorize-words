package com.example.rememberenglishwords;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by NYAN on 27.06.2019.
 */

public class TopicActivity extends AppCompatActivity {
    protected RecyclerView topicRecyclerView;
    protected TopicController topicController;
    protected Button buttonViewTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_words);

        buttonViewTopic = findViewById(R.id.buttonViewTemporary);
        topicRecyclerView = findViewById(R.id.recyclerViewTopics);
        topicController = new TopicController();
        topicRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        topicRecyclerView.setAdapter(topicController);

        //buttonViewTopic = topicRecyclerView.findViewById(R.id.buttonViewTopic);

        //как каждой кнопке присвоить слушателя????
        buttonViewTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickButtonView();
            }
        });
        createBasicTopics();
    }

    private void createBasicTopics(){
        Topic topic = new Topic();
        for(int i =0; i< topic.infoTopic.size();i++) {
            topicController.topicList.add(topic.infoTopic.get(i));
        }
    }

    private void onClickButtonView(){
        startActivity(new Intent(this,WordsActivity.class));
    }
}
