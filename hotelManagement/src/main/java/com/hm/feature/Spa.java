package com.hm.feature;

/**
 * Created by Neeman on 15/10/2017.
 */
public class Spa extends Feature {

    double price;

    public Spa(double price) {
        this.price = price;
    }

    @Override
    public String getName() {
        return "Spa";
    }

    @Override
    public double getPrice() {
        return price;
    }
}
