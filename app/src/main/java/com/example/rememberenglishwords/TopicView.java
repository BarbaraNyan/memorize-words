package com.example.rememberenglishwords;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by NYAN on 27.06.2019.
 */

public class TopicView extends RecyclerView.ViewHolder {
    TextView topicTextAbout;
    TextView topicNumberWords;
    Button buttonView;
//    ImageButton buttonDelete;
    Context context ;

    public TopicView(View itemView) {
        super(itemView);
        topicTextAbout = itemView.findViewById(R.id.topicTextAbout);
        topicNumberWords = itemView.findViewById(R.id.topicNumberWords);
        buttonView = itemView.findViewById(R.id.buttonViewTopic);
//        buttonDelete = itemView.findViewById(R.id.buttonDeleteTopic);
    }

    public void bind(Topic topic){
        topicTextAbout.setText(topic.textAbout);
        topicNumberWords.setText(topic.numberWords);
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("MINE",topicTextAbout.getText().toString());
                //переходим на WordsActivity
                Intent intent = new Intent (view.getContext(),WordsActivity.class);
                intent.putExtra("topic",topicTextAbout.getText().toString());
                view.getContext().startActivity(intent);
            }
        });
    }
}
