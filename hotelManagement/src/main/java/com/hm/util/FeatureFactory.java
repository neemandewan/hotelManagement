package com.hm.util;

import com.hm.feature.*;

/**
 * Created by Neeman on 15/10/2017.
 */
final public class FeatureFactory {

    public static Feature getFeature(String s, double val) {
        switch (s.toLowerCase()) {
            case "food":
                return new Food(val);
            case "gym":
                return new Gym(val);
            case "spa":
                return new Spa(val);
            case "swim":
                return new Swim(val);
        }

        return null;
    }
}
