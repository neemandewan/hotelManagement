package com.hm.feature;

/**
 * Created by Neeman on 15/10/2017.
 */
public class Swim extends Feature {
    double price;

    public Swim(double price) {
        this.price = price;
    }

    @Override
    public String getName() {
        return "Swim";
    }

    @Override
    public double getPrice() {
        return price;
    }
}
