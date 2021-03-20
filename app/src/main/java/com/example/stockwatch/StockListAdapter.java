package com.example.stockwatch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StockListAdapter extends RecyclerView.Adapter<StockViewHolder> {

    private ArrayList<Stock> stocklist;
    private MainActivity mainActivity;
    public StockListAdapter(MainActivity mainActivity, ArrayList<Stock> stockArrayList){
        this.mainActivity = mainActivity;
        this.stocklist = stockArrayList;
    }

    public StockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stock_layout, parent, false);

        itemView.setOnClickListener((View.OnClickListener) mainActivity);
        itemView.setOnLongClickListener((View.OnLongClickListener) mainActivity);

        return new StockViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull StockViewHolder holder, int position) {
        Stock n = stocklist.get(position);
        holder.ticker.setText(n.getTicker());
        holder.name.setText(n.getName());
        holder.percentchange.setText(String.format("%.1f",n.getPercentchange()));
        holder.netchange.setText(String.format("%.1f",n.getNetchange()));
        holder.price.setText(String.format("%.1f",n.getPrice()));

    }

    @Override
    public int getItemCount() {
        return stocklist.size();
    }


}
