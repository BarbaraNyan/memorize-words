package com.example.rememberenglishwords;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TranslateActivity extends AppCompatActivity {
    protected Button btn_translate;
    protected EditText text_english;
    protected TextView text_russian;
    protected Button btn_want_add;
    protected Button btn_want_back;
//    protected TextView text_translate;

    String collectionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        Intent intent = getIntent();
        collectionName = intent.getStringExtra("topic");

        btn_translate = findViewById(R.id.btn_translate);
        text_english = findViewById(R.id.text_english);
        text_russian = findViewById(R.id.text_russian);
        btn_want_add = findViewById(R.id.btn_want_add);
        btn_want_back = findViewById(R.id.btn_want_back);
//        text_translate = findViewById(R.id.text_translate);

        btn_translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                    StrictMode.setThreadPolicy(policy);
                    new myTasdk().execute();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btn_want_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //добавить в список Expandable новое слово
                Words words = new Words();
                words.addWord(collectionName, text_english.getText().toString(), text_russian.getText().toString());
                text_russian.setText("");
                text_english.setText("");
                Toast toast = Toast.makeText(getApplicationContext(),"Успешно добавлено!",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
                btn_want_add.setVisibility(View.INVISIBLE);
                btn_want_back.setVisibility(View.VISIBLE);
            }
        });

        btn_want_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private class myTasdk extends AsyncTask<Void, Void, List<String>> {
        String chosenTranslation = "";
        List<String> listWords;
        @Override
        protected List<String> doInBackground(Void... voids) {
            try {
                String word = text_english.getText().toString();
                Document document = Jsoup.connect("https://context.reverso.net/translation/english-russian/"+word)
                .header("User-Agent", "Mozilla/5.0")
                .get();
//                Document document = Jsoup.connect("https://context.reverso.net/translation/english-russian/hello/").get();
//                CharSequence[] items = new CharSequence[10];

                listWords = document.select("a.translation.ltr.dict").eachText();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return listWords;
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            super.onPostExecute(strings);
            final CharSequence[] itemsWords = listWords.toArray(new CharSequence[listWords.size()]);

            AlertDialog.Builder builder = new AlertDialog.Builder(TranslateActivity.this);
            builder.setTitle("Choose translation");
            builder.setItems(itemsWords, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    text_russian.setText(itemsWords[which]);
                    btn_want_add.setVisibility(View.VISIBLE);
                }
            });
            builder.show();
        }
    }
}

