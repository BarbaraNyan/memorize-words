package com.example.rememberenglishwords;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by NYAN on 27.06.2019.
 */

public class WordsActivity extends AppCompatActivity {

    protected ImageButton buttonAddNewWord;
    protected EditText textAddWord;
    protected EditText textAddTranslation;
    Words words;
//    FirebaseFirestore db = FirebaseFirestore.getInstance();

    CustomExpandableListViewAdapter customExpandableListViewAdapter;
    List<String> listDataHeader = new ArrayList<>();
    HashMap<String, List<String>> listDataChild = new HashMap<>();
    ExpandableListView expListView;
    String collectionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        //получаем название топика
        Intent intent = getIntent();
        collectionName = intent.getStringExtra("topic");

        textAddWord = findViewById(R.id.textAddWord);
        textAddTranslation = findViewById(R.id.textAddTranslation);
        expListView = findViewById(R.id.expListView);
        buttonAddNewWord = findViewById(R.id.buttonAddNewWord);

        setGroups();

        buttonAddNewWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //добавить в список Expandable новое слово
                words = new Words();
               // collectionName = "every_day";
                words.addWord(collectionName, textAddWord.getText().toString(), textAddTranslation.getText().toString());
                //words.addWord(myRef,textAddWord.getText().toString(),"перевод");
            }
        });
//        //можно убрать
//        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
//                return false;
//            }
//        });
    }

    private void setGroups(){
        words = new Words(listDataHeader,listDataChild);
        // collectionName = "every_day";
        words.readWord(collectionName);
        listDataChild = words.getListDataChild();
        listDataHeader = words.getListDataHeader();

        customExpandableListViewAdapter = new CustomExpandableListViewAdapter(this,listDataHeader,listDataChild);
        customExpandableListViewAdapter.notifyDataSetChanged();
        expListView.setAdapter(customExpandableListViewAdapter);
    }
}
