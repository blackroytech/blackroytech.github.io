package com.cs360.eboniroyalinventoryapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
   private Context context;
   Activity activity;
    private ArrayList item_id, item_name, quantity;


    RecycleAdapter(Activity activity, View.OnClickListener context,
                   ArrayList item_id,
                   ArrayList item_name,
                   ArrayList quantity){
        this.activity = activity;
        this.context = context;
        this.item_id = item_id;
        this.item_name = item_name;
        this.quantity = quantity;

    }





    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        //pass recycler view layout
        View view = inflater.inflate(R.layout.rec_view_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {



        holder.item_id_txt.setText(String.valueOf(item_id.get(position)));
        holder.item_name_txt.setText(String.valueOf(item_name.get(position)));
        holder.quantity_txt.setText(String.valueOf(quantity.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, updateActvity.class);
                intent.putExtra("id", String.valueOf(item_id.get(position)));
                intent.putExtra("name", String.valueOf(item_name.get(position)));
                intent.putExtra("quantity", String.valueOf(quantity.get(position)));
                activity.startActivityForResult(intent, 1);

            }
        });
    }

    @Override
    public int getItemCount() {
        return item_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView item_id_txt, item_name_txt, quantity_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_id_txt =itemView.findViewById(R.id.item_id_text);
            item_name_txt = itemView.findViewById(R.id.item_name_text);
            quantity_txt = itemView.findViewById(R.id.quantityNumber);
            mainLayout = itemView.findViewById(R.id.mainLayout);


        }
    }

}
