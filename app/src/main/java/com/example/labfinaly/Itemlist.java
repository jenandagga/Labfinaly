package com.example.labfinaly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.labfinaly.Itemtask;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Itemlist extends AppCompatActivity {
    List<Itemtask> tasks=new ArrayList<Itemtask>();
    CustomAdapter adapter=   new CustomAdapter(Itemlist.this,tasks);
public static String listId="";
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_lest);
        Intent intent = getIntent();

        String  id = intent.getStringExtra("id");
listId=id;

        String  message = intent.getStringExtra("info");
        TextView listTitle=findViewById(R.id.listTitle);
        final TextView desc=findViewById(R.id.desc);
        final TextView title=findViewById(R.id.taskTitle);
        listTitle.setText(message);
        final LinearLayout addLayer=findViewById(R.id.addLayer);
        Button addList=findViewById(R.id.addtask);
        Button add=findViewById(R.id.add);
        count=tasks.size();
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
                EditText title = findViewById(R.id.taskTitle);
                RecyclerView rv=findViewById(R.id.task_rv);
                rv.setAdapter(new CustomAdapter(Itemlist.this,tasks));
                rv.setLayoutManager(new LinearLayoutManager(Itemlist.this));
                addLayer.setVisibility(View.GONE);
                title.setText("");
            }
        });




        final FirebaseAuth mAuth = FirebaseAuth.getInstance();

        RecyclerView rv=findViewById(R.id.task_rv);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(Itemlist.this));
        FirebaseDatabase.getInstance().getReference("users").child(mAuth.getUid()).child("Lists").child(id).child("tasks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tasks.clear();

                for (DataSnapshot datasnapshot:snapshot.getChildren()){
//                    Log.e("",datasnapshot.getValue(taskItem.class)+"");
                    Itemtask list=datasnapshot.getValue(Itemtask.class);
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
//                EditText title = findViewById(R.id.listTitle);

                addLayer.setVisibility(View.GONE);

                addtoList(title.getText()+"",desc.getText()+"",mAuth.getUid());

              //  title.setText("");
            }
        });

















    }



    public void addtoList(String title,String discription,String userid ){
        Intent intent = getIntent();

        String  id = intent.getStringExtra("id");

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

        String key=mDatabase.child("users").child(userid).child("Lists").child(id).child("tasks").push().getKey();
        ListData.put("id",key);
        ListData.put("isChecked","false");

        mDatabase.child("users").child(userid).child("Lists").child(id).child("tasks").child(key).setValue(ListData);

        tasks.add(new Itemtask(title+"",discription+"",key,"false"));




    }
}