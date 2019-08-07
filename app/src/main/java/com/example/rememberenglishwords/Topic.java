package com.example.rememberenglishwords;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by NYAN on 27.06.2019.
 */

public class Topic {
    public String textAbout;
    public String numberWords;
    //public Image image;

    public List<Topic> infoTopic = new ArrayList<>();

    public Topic(){}
    public Topic(String textAbout, String numberWords){
        this.textAbout = textAbout;
        this.numberWords = numberWords;
    }
    public void putTopics(Set<String> topics){
        for(String name: topics){
//            Log.i("MyTag","TOPIC:"+name);
            infoTopic.add(new Topic(name, "10 words"));
        }
    }
    //add new topicName
    public void add(String newTopicName){
        infoTopic.add(new Topic(newTopicName,"10 words"));
    }
}
