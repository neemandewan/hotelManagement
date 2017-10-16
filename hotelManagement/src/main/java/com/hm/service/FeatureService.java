package com.hm.service;

import com.hm.model.FeatureUsed;

import java.util.List;

/**
 * Created by Neeman on 15/10/2017.
 */
public interface FeatureService {
    public FeatureUsed addFeature(FeatureUsed featureUsed);

    public List<FeatureUsed> getFeatureByUserId(Long userId);
}
