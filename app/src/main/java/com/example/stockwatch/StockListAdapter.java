package com.example.stockwatch;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

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
        Stock s = stocklist.get(position);

        if(s.getNetchange()>0){
            holder.ticker.setTextColor(Color.parseColor("green"));
            holder.name.setTextColor(Color.parseColor("green"));
            holder.price.setTextColor(Color.parseColor("green"));
            holder.netchange.setTextColor(Color.parseColor("green"));
            holder.percentchange.setTextColor(Color.parseColor("green"));
            holder.arrow.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
            holder.arrow.setColorFilter(Color.parseColor("green"));
        }
        else{
            holder.ticker.setTextColor(Color.parseColor("red"));
            holder.name.setTextColor(Color.parseColor("red"));
            holder.price.setTextColor(Color.parseColor("red"));
            holder.netchange.setTextColor(Color.parseColor("red"));
            holder.percentchange.setTextColor(Color.parseColor("red"));
            holder.arrow.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
            holder.arrow.setColorFilter(Color.parseColor("red"));
        }
        holder.ticker.setText(s.getTicker());
        holder.name.setText(s.getName());
        holder.percentchange.setText("("+String.format(Locale.US, "%.2f",s.getPercentchange())+"%)");
        holder.netchange.setText(String.format(Locale.US,"%.2f",s.getNetchange()));
        holder.price.setText(" $"+String.format(Locale.US,"%.2f",s.getPrice()));


    }

    @Override
    public int getItemCount() {
        return stocklist.size();
    }


}
