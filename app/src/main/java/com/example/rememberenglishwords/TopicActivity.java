package com.example.rememberenglishwords;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by NYAN on 27.06.2019.
 */

public class TopicActivity extends AppCompatActivity {
    protected RecyclerView topicRecyclerView;
    protected TopicController topicController;
    protected ImageButton buttonNewTopic;
    protected ImageButton buttonDeleteTopic;
    private String newTopicName;

    public static final String APP_TOPICS = "topics";
    public static final String APP_TOPICS_NAME = "topics_name";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    //для записи из SharedPreferences
    private Set<String> topicNames = new HashSet<>();
    //для добавления новых топиков(т.к. SharedPrferences возвращет неизменный Set)
    private Set<String> topicNamesTemp = new HashSet<>();
    Topic newTopic = new Topic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_words);
        setTitle("Topics");

        sharedPreferences = getSharedPreferences(APP_TOPICS, Context.MODE_PRIVATE);

        topicRecyclerView = findViewById(R.id.recyclerViewTopics);
        topicController = new TopicController();
        topicRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        topicRecyclerView.setAdapter(topicController);

        createBasicTopics();

        TopicActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                createBasicTopics();
                List<String> listDataHeader = new ArrayList<>();
                for(int i = 0; i<topicController.topicList.size(); i++) {
//            System.out.println(topicController.topicList.size());
                    Topic topic = topicController.topicList.get(i);
                    Words words = new Words();
                    words.readWord(topic.textAbout);
                    System.out.println(topic.textAbout);
                    listDataHeader = words.getListDataHeader();
                    System.out.println(listDataHeader.size());
                    topic.setNumber(listDataHeader.size());
                }
                topicController.notifyDataSetChanged();

            }
        });

        buttonNewTopic = findViewById(R.id.buttonNewTopic);
        buttonNewTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //alert dialog for new Topic name
                AlertDialog.Builder builder = new AlertDialog.Builder(TopicActivity.this);
                builder.setTitle("Type new topic name");
                //set up input
                final EditText input = new EditText(TopicActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                //set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //get new topic name
                        newTopicName = input.getText().toString();
                        newTopic.add(newTopicName);

                        //get and add new in topicNames
                        topicNames = getExistTopics();
                        topicNamesTemp.addAll(topicNames);
                        topicNamesTemp.add(newTopicName);

                        //update topicNames in SharedPreferences
                        editor = sharedPreferences.edit();
                        editor.putStringSet(APP_TOPICS_NAME, topicNamesTemp);
                        editor.commit();

                        //update topicList
                        topicController.topicList.add(new Topic(newTopicName,"10 words"));
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }
        });
//
//        buttonDeleteTopic = findViewById(R.id.buttonDeleteTopic);
//        buttonDeleteTopic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                newTopic.delete();
//            }
//        });
    }

    //get all topics from SharedPreferences
    private Set<String> getExistTopics(){
        if(sharedPreferences.contains(APP_TOPICS_NAME)) {
            return sharedPreferences.getStringSet(APP_TOPICS_NAME, new HashSet<String>());
        }
        return new HashSet<>();
    }
    //add all topics in topicList
    private void createBasicTopics(){
        topicNames = getExistTopics();
        topicNamesTemp.addAll(topicNames);
        if(topicNamesTemp.size()!=0) {
            newTopic.putTopics(topicNamesTemp);
            for (int i = 0; i < newTopic.infoTopic.size(); i++) {
//                Topic topic = newTopic.setNumberWords(newTopic.infoTopic.get(i));
                topicController.topicList.add(newTopic.infoTopic.get(i));
            }
        }
    }

    private void setNumberWords(Topic topic, int i){
        topic.setNumberWords(topic.infoTopic.get(i));
    }

    private void changeNumberWords(){

//        List<String> listDataHeader = new ArrayList<>();
//
//        for(int i = 0; i<topicController.topicList.size(); i++) {
//            Words words = new Words();
//            Topic topic = topicController.topicList.get(i);
//            words.readWord(topic.infoToptextAbout);
//            listDataHeader = words.getListDataHeader();
//            topic.numberWords = String.valueOf(listDataHeader.size());
//
////            topic.setNumberWords(topic);
//        }
//        topicController.notifyDataSetChanged();

    }
}
