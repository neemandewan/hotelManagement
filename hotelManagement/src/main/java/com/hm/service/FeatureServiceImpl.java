package com.hm.service;

import com.hm.model.FeatureUsed;
import com.hm.repository.FeatureRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Neeman on 15/10/2017.
 */

@Service("featureService")
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    private Logger log;

    @Autowired
    private FeatureRepository featureRepository;

    @Override
    public FeatureUsed addFeature(FeatureUsed featureUsed) {
        log.info("Adding feature");
        featureRepository.save(featureUsed);
        return null;
    }

    @Override
    public List<FeatureUsed> getFeatureByUserId(Long userId) {
        log.info("Listing info");
        return featureRepository.getFeaturesByUserId(userId);
    }
}
