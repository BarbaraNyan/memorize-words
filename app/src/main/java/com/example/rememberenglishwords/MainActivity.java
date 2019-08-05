package com.example.rememberenglishwords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    protected Button allWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allWords = findViewById(R.id.all_words);
        allWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTopics();
            }
        });
    }

    private void openTopics(){
        startActivity(new Intent(this,TopicActivity.class));
    }
}
