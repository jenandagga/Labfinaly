package com.example.labfinaly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference CourseRef = db.collection("Course");

    Adapter Adapter;

    String typeId = "";
    RecyclerView rvtask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       rvtask = findViewById(R.id.recyclerview);
       rvtask.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        CollectionReference CourseRef = db.collection("Datalist");

    }
    @Override
    public void onStart() {
        super.onStart();
      Adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
      Adapter.stopListening();
    }

}