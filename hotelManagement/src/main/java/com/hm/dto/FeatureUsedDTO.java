package com.hm.dto;

import java.io.Serializable;

/**
 * Created by Neeman on 15/10/2017.
 */
public class FeatureUsedDTO implements Serializable {

    private long userId;
    private String featureName;
    private double featurePrice;

    public FeatureUsedDTO(long userId, String featureName, double featurePrice) {
        this.userId = userId;
        this.featureName = featureName;
        this.featurePrice = featurePrice;
    }

    public FeatureUsedDTO() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public double getFeaturePrice() {
        return featurePrice;
    }

    public void setFeaturePrice(double featurePrice) {
        this.featurePrice = featurePrice;
    }

    @Override
    public String toString() {
        return "FeatureUsedDTO{" +
                "userId=" + userId +
                ", featureName='" + featureName + '\'' +
                ", featurePrice=" + featurePrice +
                '}';
    }
}
