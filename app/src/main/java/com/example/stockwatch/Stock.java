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

    public Stock(){}

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setNetchange(Double netchange) {
        this.netchange = netchange;
    }

    public void setPercentchange(Double percentchange) {
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
