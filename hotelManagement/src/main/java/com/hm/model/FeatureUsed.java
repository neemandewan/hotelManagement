package com.hm.model;

/**
 * Created by Neeman on 15/10/2017.
 */

import javax.persistence.*;

@Entity
@Table(name = "feature_used")
public class FeatureUsed {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private long userId;
    private String featureName;
    private double featurePrice;

    public FeatureUsed() {

    }

    public FeatureUsed(long userId, String featureName, double featurePrice) {
        this.userId = userId;
        this.featureName = featureName;
        this.featurePrice = featurePrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return "FeatureUsed{" +
                "id=" + id +
                ", userId=" + userId +
                ", featureName='" + featureName + '\'' +
                ", featurePrice=" + featurePrice +
                '}';
    }
}
