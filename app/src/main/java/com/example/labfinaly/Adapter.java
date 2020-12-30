package com.example.labfinaly;

import android.content.Context;
import android.view.ViewGroup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;

import java.text.BreakIterator;

public class Adapter extends FirestoreRecyclerAdapter<Datalist,Adapter.AdapterHolder> {

    private Context context;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Adapter(@NonNull FirestoreRecyclerOptions<Datalist> options) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterHolder adapterHolder, int i, @NonNull Datalist datalist) {
        AdapterHolder.typeone.setText(datalist.getTextone());
        AdapterHolder.typetwo.setText(datalist.getTexttwo());

    }

    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new AdapterHolder(root);

    }

    public static class AdapterHolder extends RecyclerView.ViewHolder {


        public static TextView typeone;
         public static TextView  typetwo;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            typeone = itemView.findViewById(R.id.textone);
        typetwo = itemView.findViewById(R.id.texttwo);

        }
    }

}