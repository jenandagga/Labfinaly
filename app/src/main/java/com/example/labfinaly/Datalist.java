package com.example.labfinaly;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Datalist extends AppCompatActivity {
List<com.example.labfinaly.List> tasks=new ArrayList<com.example.labfinaly.List>();
    CustomAdapterList adapter=   new CustomAdapterList(Datalist.this,tasks);

int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);
        final LinearLayout addLayer=findViewById(R.id.addLayer);
        Button addList=findViewById(R.id.addList);
        Button add=findViewById(R.id.add);
        count=tasks.size();


        final FirebaseAuth mAuth = FirebaseAuth.getInstance();

   RecyclerView rv=findViewById(R.id.list_rs);
         rv.setAdapter(adapter);
         tasks.clear();
        rv.setLayoutManager(new LinearLayoutManager(Datalist.this));
        FirebaseDatabase.getInstance().getReference("users").child(mAuth.getUid()).child("Lists").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tasks.clear();

                for (DataSnapshot datasnapshot:snapshot.getChildren()){
Log.e("",datasnapshot.getValue(com.example.labfinaly.List.class)+"");
                    com.example.labfinaly.List list=datasnapshot.getValue(com.example.labfinaly.List.class);
                    tasks.add(list);

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLayer.setVisibility(View.VISIBLE);
            }
        });

        addList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                EditText title = findViewById(R.id.listTitle);

addLayer.setVisibility(View.GONE);

                addList(title.getText()+"",mAuth.getUid());

                title.setText("");
            }
        });







        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child(mAuth.getUid()+"\\tasks");



        myRef.addChildEventListener(new ChildEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.e("MMMMMMM",snapshot.getKey()+"");




            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.e("child",snapshot.getRef()+"");
                //    createNotification(id+"",id+"","حالة طلبك قيد التنفيذ222"+"");

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







    }

    public void addList(String title,String id ){

        DatabaseReference mDatabase;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
        } else {
            // No user is signed in
        }
// ...
        Map<String,String> ListData=new HashMap<>();
        ListData.put("title",title);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        String key=mDatabase.child("users").child(id).child("Lists").push().getKey();
        ListData.put("id",key);

        mDatabase.child("users").child(id).child("Lists").child(key).setValue(ListData);

        tasks.add(new com.example.labfinaly.List(title+"",key+""));




    }


}