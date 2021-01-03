package com.example.labfinaly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Itemtask  {
    public String title;
    public String description;
   public String isChecked;
    public String id;

    public  Itemtask (){}
        public Itemtask (String title,String description,String id,String isChecked){

        this.title=title;
        this.description=description;
        this.isChecked=isChecked;
        this.id=id;

    }
    public void setIsChecked(String  isChecked){this.isChecked=isChecked;}
    public void setIsTitle(String title){this.title=title;}
    public void setdescription(String description){this.description=description;}


}