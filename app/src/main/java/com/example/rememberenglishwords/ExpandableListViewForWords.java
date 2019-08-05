package com.example.rememberenglishwords;//package com.example.rememberwords3;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseExpandableListAdapter;
//import android.widget.SimpleExpandableListAdapter;
//import android.widget.TextView;
//
//import com.google.firebase.firestore.FirebaseFirestore;
//
//import java.util.HashMap;
//
///**
// * Created by NYAN on 27.06.2019.
// */
//
//public class ExpandableListViewForWords extends BaseExpandableListAdapter {
//    private Context context;
//    Words words = new Words();
//
//    FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//    HashMap<String, String> translation = new HashMap<>();
//
//    public ExpandableListViewForWords(Context context){
//        this.context = context;
//    }
//    @Override
//    public int getGroupCount() {
//        return 3;
//    }
//
//    @Override
//    public int getChildrenCount(int i) {
//        return 1;
//    }
//
//    @Override
//    public Object getGroup(int i) {
//        return null;
//    }
//
//    @Override
//    public Object getChild(int i, int i1) {
//        return null;
//    }
//
//    @Override
//    public long getGroupId(int i) {
//        return i;
//    }
//
//    @Override
//    public long getChildId(int i, int i1) {
//        return i1;
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return true;
//    }
//
//    @Override
//    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
//        if(view == null){
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflater.inflate(R.layout.activity_words_item,null);
//        }
//        translation = words.readWord(db);
//        int pos = 0;
//
//        for(HashMap.Entry<String, String> e: translation.entrySet()){
//            ((TextView) view.findViewById(R.id.english_word)).setText(e.getKey());
//        }
//
//        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(this,)
////        switch (i){
////            case 0: {
////                ((TextView) view.findViewById(R.id.english_word)).setText("coincidence");
////            } break;
////            case 1: {
////                ((TextView) view.findViewById(R.id.english_word)).setText("consequences");
////            } break;
////            case 2: {
////                ((TextView) view.findViewById(R.id.english_word)).setText("braid");
////            } break;
////
////        }
//        return view;
//    }
//
//    @Override
//    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        view = inflater.inflate(R.layout.activity_words_subitem,null);
//
//        translation = words.readWord(db);
//        for(HashMap.Entry<String, String> e: translation.entrySet()){
//            ((TextView) view.findViewById(R.id.russian_word)).setText(e.getValue());
//        }
//
////        switch (i){
////            case 0: {
////                switch (i1) {
////                    case 0: {
////                        ((TextView) view.findViewById(R.id.russian_word)).setText("Стечение обстоятельств");
////                    } break;
////                }
////            } break;
////            case 1: {
////                switch (i1) {
////                    case 0: {
////                        ((TextView) view.findViewById(R.id.russian_word)).setText("Совпадение");
////                    } break;
////                }
////            } break;
////            case 2:{
////                switch (i1){
////                    case 0:{
////                        ((TextView) view.findViewById(R.id.russian_word)).setText("Заплести");
////                    } break;
////                }
////            } break;
////        }
//        return view;
//    }
//
//    @Override
//    public boolean isChildSelectable(int i, int i1) {
//        return false;
//    }
//}
