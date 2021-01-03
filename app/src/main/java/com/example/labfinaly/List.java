package com.example.labfinaly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class List  {
   public String title;
   public String id;
    List() {
    }
    List(String title,String id){

        this.title=title;
        this.id=id;

    }
    public void setIsTitle(String title){this.title=title;}
    public void setid(String id){this.id=id;}


}