package com.hm.feature;

/**
 * Created by Neeman on 15/10/2017.
 */
public class Gym extends Feature {
    double price;

    public Gym(double price) {
        this.price = price;
    }

    @Override
    public String getName() {
        return "Gym";
    }

    @Override
    public double getPrice() {
        return price;
    }
}
