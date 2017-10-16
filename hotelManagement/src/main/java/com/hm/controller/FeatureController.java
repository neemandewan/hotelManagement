package com.hm.controller;

import com.hm.dto.FeatureUsedDTO;
import com.hm.dto.HotelDTO;
import com.hm.dto.Response;
import com.hm.feature.Feature;
import com.hm.model.*;
import com.hm.service.FeatureService;
import com.hm.util.FeatureFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Neeman on 15/10/2017.
 */

@Controller
@RequestMapping(value="/api/feature")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    @RequestMapping(value="/{userId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Response<List<FeatureUsed>> getFeatures(@PathVariable("userId") Long userId) {
        List<FeatureUsed> flist = featureService.getFeatureByUserId(userId);
        return Response.ok(flist, HttpStatus.OK.value(), HttpStatus.OK.name());
    }

    @RequestMapping(value="/{userId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Response<FeatureUsed> addFeature(@PathVariable("userId") Long userId, @Valid @RequestBody FeatureUsedDTO featureUsedDTO) {
        Feature feature = FeatureFactory.getFeature(featureUsedDTO.getFeatureName(), featureUsedDTO.getFeaturePrice());

        FeatureUsed featureUsed = new FeatureUsed();
        featureUsed.setFeatureName(feature.getName());
        featureUsed.setFeaturePrice(feature.getPrice());
        featureUsed.setUserId(userId);

        FeatureUsed featureUsed1 = featureService.addFeature(featureUsed);

        return Response.ok(featureUsed1, HttpStatus.OK.value(), HttpStatus.OK.name());
    }
}
