package com.example.labfinaly;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class CustomAdapterList extends RecyclerView.Adapter<CustomAdapterList.ViewHolderList> {

    private String[] localDataSet;
    List<com.example.labfinaly.List> tasks;
Context context;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolderList extends RecyclerView.ViewHolder {
         TextView listTitle;

        public ViewHolderList(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            listTitle=view.findViewById(R.id.listTitle);

        }
public void setData(com.example.labfinaly.List taskItem){
    listTitle.setText(taskItem.title);


}
        public TextView getTextView() { return listTitle;
        }
    }

    public CustomAdapterList(Context context,List<com.example.labfinaly.List> taskItems) {
        this.context=context;
        tasks = taskItems;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolderList onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_list_em, viewGroup, false);

        return new ViewHolderList(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolderList viewHolder, final int position) {
viewHolder.setData(tasks.get(position));
        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        com.example.labfinaly.List task=tasks.get(position);

        viewHolder.listTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Itemlist.class);
                intent.putExtra("info", tasks.get(position).title+"");
                intent.putExtra("id", tasks.get(position).id+"");
                v.getContext().startActivity(intent);

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
