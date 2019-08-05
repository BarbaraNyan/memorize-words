package com.example.rememberenglishwords;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NYAN on 27.06.2019.
 */

public class Topic {
    public String textAbout;
    public String numberWords;
    //public Image image;

    public List<Topic> infoTopic = new ArrayList<>();

    public Topic(){
        putBasicTopics();
    }
    public Topic(String textAbout, String numberWords){
        this.textAbout = textAbout;
        this.numberWords = numberWords;
    }

    public void putBasicTopics(){
        infoTopic.add(new Topic("every_day","10 words"));
        infoTopic.add(new Topic("it","15 words"));
        infoTopic.add(new Topic("Travelling","10 words"));
        infoTopic.add(new Topic("The Hardest","11 words"));
    }
}
