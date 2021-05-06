package com.example.rememberenglishwords;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by NYAN on 27.06.2019.
 */

public class WordsActivity extends AppCompatActivity {

    protected ImageButton buttonAddNewWord;
    protected ImageButton btnAdd; //new Button for translate words from Reverso Context
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
        //устанавливаем это название топика в Title
        setTitle(collectionName);

        textAddWord = findViewById(R.id.textAddWord);
        textAddTranslation = findViewById(R.id.textAddTranslation);
        expListView = findViewById(R.id.expListView);
        buttonAddNewWord = findViewById(R.id.buttonAddNewWord);
        btnAdd = findViewById(R.id.btnAdd);


        words = new Words();
        words.readWord(collectionName);
        listDataChild = words.getListDataChild();
        listDataHeader = words.getListDataHeader();

//        customExpandableListViewAdapter = new CustomExpandableListViewAdapter(this,listDataHeader,listDataChild);
//        customExpandableListViewAdapter.notifyDataSetChanged();
//        expListView.setAdapter(customExpandableListViewAdapter);

        WordsActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                    words = new Words();
                    words.readWord(collectionName);
                    listDataChild = words.getListDataChild();
                    listDataHeader = words.getListDataHeader();

                    customExpandableListViewAdapter = new CustomExpandableListViewAdapter(WordsActivity.this,listDataHeader,listDataChild);
                    expListView.setAdapter(customExpandableListViewAdapter);
                    customExpandableListViewAdapter.notifyDataSetChanged();
                    expListView.invalidateViews();
                    expListView.refreshDrawableState();
                }
        });
        customExpandableListViewAdapter.notifyDataSetChanged();
        expListView.refreshDrawableState();

        //засунем в отедльный поток
//        new downloadListWords().execute();
//        expListView.setAdapter(customExpandableListViewAdapter);
//        setGroups();


        buttonAddNewWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //добавить в список Expandable новое слово
                words = new Words();
                words.addWord(collectionName, textAddWord.getText().toString(), textAddTranslation.getText().toString());
                textAddWord.setText("");
                textAddTranslation.setText("");
                Toast toast = Toast.makeText(getApplicationContext(),"Успешно добавлено!",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(),TranslateActivity.class);
                intent.putExtra("topic",collectionName);
                v.getContext().startActivity(intent);
                customExpandableListViewAdapter.notifyDataSetChanged();
//                startActivity(new Intent(WordsActivity.this, TranslateActivity.class));
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

    private class downloadListWords extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            words = new Words();
            words.readWord(collectionName);
            listDataChild = words.getListDataChild();
            listDataHeader = words.getListDataHeader();

//            customExpandableListViewAdapter = new CustomExpandableListViewAdapter(getApplicationContext(),listDataHeader,listDataChild);
            customExpandableListViewAdapter = new CustomExpandableListViewAdapter(WordsActivity.this,listDataHeader,listDataChild);
//            customExpandableListViewAdapter.notifyDataSetChanged();
            return null;
        }

        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
//            customExpandableListViewAdapter = new CustomExpandableListViewAdapter(getApplicationContext(),listDataHeader,listDataChild);
//            customExpandableListViewAdapter.notifyDataSetChanged();
            expListView.setAdapter(customExpandableListViewAdapter);
            customExpandableListViewAdapter.notifyDataSetChanged();
            expListView.invalidateViews();
            expListView.refreshDrawableState();
//            customExpandableListViewAdapter.notifyDataSetChanged();

        }
    }
    private void setGroups(){
//        words = new Words(listDataHeader,listDataChild);
        words = new Words();
        words.readWord(collectionName);
        listDataChild = words.getListDataChild();
        listDataHeader = words.getListDataHeader();

        customExpandableListViewAdapter = new CustomExpandableListViewAdapter(this,listDataHeader,listDataChild);
        customExpandableListViewAdapter.notifyDataSetChanged();
        expListView.setAdapter(customExpandableListViewAdapter);
    }

}
