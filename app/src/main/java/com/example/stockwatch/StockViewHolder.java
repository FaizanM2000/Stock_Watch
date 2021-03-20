package com.example.stockwatch;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class StockViewHolder extends RecyclerView.ViewHolder {


    TextView ticker;
    TextView name;
    TextView price;
    TextView netchange;
    TextView percentchange;

    public StockViewHolder(View itemView){
        super(itemView);
        ticker = itemView.findViewById(R.id.tickertext);
        name = itemView.findViewById(R.id.nametext);
        price = itemView.findViewById(R.id.pricetext);
        netchange = itemView.findViewById(R.id.netchangetext);
        percentchange = itemView.findViewById(R.id.percenttext);

    }

}
