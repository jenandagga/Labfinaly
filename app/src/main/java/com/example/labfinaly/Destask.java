package com.example.labfinaly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Destask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_des_task);

        Intent intent = getIntent();

        String  id = intent.getStringExtra("id");

        String  title1 = intent.getStringExtra("title");
        String  description = intent.getStringExtra("description");


        TextView desc=findViewById(R.id.description);
        TextView title=findViewById(R.id.title);
        desc.setText(description);
        title.setText(title1);
    }
}