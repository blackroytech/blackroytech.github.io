package com.cs499.warehouseinventorymanagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//For Enhancement Two:
//This adapter will bridge the data and UI
//Source: Create dynamic lists with RecyclerView https://developer.android.com/develop/ui/views/layout/recyclerview
public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder> {
    // List
    private List<Items> itemsList;

    public InventoryAdapter(List<Items>itemsList){
        this.itemsList = itemsList;
    }




    // initialize dataset for adapter
    @NonNull
    @Override
    public InventoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                // inflate items layout, tells adapter where to use the layout
                .inflate(R.layout.items_layout, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull InventoryAdapter.ViewHolder holder, int position) {
        //getters and setters
        Items currentProduct = itemsList.get(position);
        //holder, sets text
        holder.skuView.setText("SKU: " + currentProduct.getSku());
        holder.productView.setText("Product: " + currentProduct.getProduct());
        holder.qtyView.setText("QTY: " +currentProduct.getQty());
    }

    @Override
    public int getItemCount() {
        //return list size
        return itemsList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView skuView, productView, qtyView;
        public ViewHolder(View view) {
            super(view);
            // Match Ids to items layout
            skuView = view.findViewById(R.id.PdSKU);
            productView = view.findViewById(R.id.Pdname);
            qtyView = view.findViewById(R.id.pdQTY);




        }

    }
}

