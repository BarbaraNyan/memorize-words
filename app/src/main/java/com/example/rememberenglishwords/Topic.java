package com.example.rememberenglishwords;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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
    private int kol;
    //public Image image;

    public List<Topic> infoTopic = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public Topic(){}
    public Topic(String textAbout, String numberWords){
        this.textAbout = textAbout;
        this.numberWords = numberWords;
    }
    public void setNumber(int count){
        this.numberWords = String.valueOf(count);
    }
    //здесь будем добавлять кол-во слов в топике
    public void putTopics(Set<String> topics){
        for(String name: topics){
            infoTopic.add(new Topic(name, kol + " words"));
        }
    }
    public Topic setNumberWords(Topic topic){
        System.out.println(topic.textAbout);
        db.collection(topic.textAbout)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        kol = 5;
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot doc: task.getResult()) {
                                kol++;
                            }
                        }
                    }
                });
        System.out.println("kol11111111111111111111111111111 = "+kol);

        topic.numberWords = String.valueOf(kol + " words");
        return topic;
    }
    //add new topicName
    public void add(String newTopicName){
        infoTopic.add(new Topic(newTopicName,"10 words"));
    }
    //delete topic
    public void delete(String collectionName){
        db.collection(collectionName).document()
                .delete();
    }
}
