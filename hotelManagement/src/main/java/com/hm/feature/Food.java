package com.hm.feature;

/**
 * Created by Neeman on 15/10/2017.
 */
public class Food extends Feature {
    double price;

    public Food(double price) {
        this.price = price;
    }

    @Override
    public String getName() {
        return "Food";
    }

    @Override
    public double getPrice() {
        return price;
    }
}
