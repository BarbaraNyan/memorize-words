package com.example.rememberenglishwords;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by NYAN on 28.06.2019.
 */

public class Words {
//    private ArrayList<String> englishWords = new ArrayList<>();
//    private ArrayList<String> russianWords = new ArrayList<>();
//
//    private HashMap<String, String> translationRead = new HashMap<>();
    private Map<String, String> translationWrite = new HashMap<>();

    private List<String> listDataHeader = new ArrayList<>();
    private HashMap<String, List<String>> listDataChild = new HashMap<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public Words(){
    }
    public Words(List<String> listDataHeader, HashMap<String,List<String>> listDataChild){
        this.listDataHeader = listDataHeader;
        this.listDataChild = listDataChild;
    }

    public void addWord(String collectionName, String english, String russian) {
        translationWrite.put(english, russian);
        db.collection(collectionName)
                .add(translationWrite)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        //Log.w(TAG, "Error adding document", e);
                    }
                    //старое
                    // ref.push().setValue(translationWrite);
                });
    }

    public void readWord(String collectionName){
        db.collection(collectionName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String,Object>wordRead = document.getData();
                                for ( Map.Entry<String, Object> entry : wordRead.entrySet()) {
                                    String english = entry.getKey();
                                    String russian = (String) entry.getValue();

                                    List<String> russians = new ArrayList<>();
                                    russians.add(russian);

                                    listDataHeader.add(english);
                                    listDataChild.put(english,russians);
                                }
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            //Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public List<String> getListDataHeader(){
        return  listDataHeader;
    }
    public HashMap<String, List<String>> getListDataChild(){
        return listDataChild;
    }
}
