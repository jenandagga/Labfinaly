package com.example.labfinaly;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.labfinaly.Itemlist.listId;


class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private String[] localDataSet;
    List<Itemtask> tasks;
    Context context;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView listType;
        CheckBox checkBox;
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            listType=view.findViewById(R.id.checkboxTitle);
            checkBox=view.findViewById(R.id.checkbox);
            textView = (TextView) view.findViewById(R.id.loginBtn);
        }
        public void setData(Itemtask taskItem){
            checkBox.setText(taskItem.title);
            if(taskItem.isChecked.equals("true")) {
                checkBox.setSelected(true);
            }else{
                checkBox.setSelected(false);


            }

        }
        public TextView getTextView() { return textView;
        }
    }

    public CustomAdapter(Context context,List<Itemtask> taskItems) {
        this.context=context;
        tasks = taskItems;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_task_em, viewGroup, false);

        return new ViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.setData(tasks.get(position));
        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        final Itemtask task=tasks.get(position);
        Log.e("###",listId);
        if(task.isChecked.equals("true")){
            viewHolder.checkBox.setChecked(true);
            viewHolder.checkBox.setPaintFlags(viewHolder.checkBox.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);


        }



        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Destask.class);
                intent.putExtra("title", tasks.get(position).title+"");
                intent.putExtra("description", tasks.get(position).description+"");
                intent.putExtra("id", tasks.get(position).id+"");
                v.getContext().startActivity(intent);
            }
        });







        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                    viewHolder.checkBox.setText(task.title);
                    viewHolder.checkBox.setPaintFlags(viewHolder.checkBox.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                    DatabaseReference mDatabase;
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    Map<String,String> ListData=new HashMap<>();
                    ListData.put("title",task.title);
                    ListData.put("id",task.id);
                    ListData.put("isChecked",isChecked+"");
                    mDatabase.child("users").child(mAuth.getUid()).child("Lists").child(listId+"").child("tasks").child(task.id+"").setValue(ListData);



                }
                else{
                    DatabaseReference mDatabase;
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    Map<String,String> ListData=new HashMap<>();
                    ListData.put("title",task.title);
                    ListData.put("id",task.id);
                    ListData.put("isChecked",isChecked+"");
                    mDatabase.child("users").child(mAuth.getUid()).child("Lists").child(listId).child("tasks").child(task.id+"").setValue(ListData);

                    viewHolder.checkBox.setText(task.title);
                    viewHolder.checkBox.setPaintFlags(viewHolder.checkBox.getPaintFlags()&(~Paint.STRIKE_THRU_TEXT_FLAG));



                }
            }
        });
//        viewHolder.getTextView().setText(localDataSet[position]);




    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
