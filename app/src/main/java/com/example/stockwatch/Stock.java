package com.example.stockwatch;

import java.io.Serializable;

import javax.net.ssl.SSLContext;

public class Stock implements Serializable {
    private String ticker;
    private String name;
    private Double price;
    private Double netchange;
    private Double percentchange;


    public Stock(String ticker,String name, Double price, Double netchange,Double percentchange){
        this.ticker = ticker;
        this.name = name;
        this.price = price;
        this.netchange = netchange;
        this.percentchange = percentchange;
    }


    public String getTicker() {
        return ticker;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Double getNetchange() {
        return netchange;
    }

    public Double getPercentchange() {
        return percentchange;
    }




}
