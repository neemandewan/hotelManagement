package com.hm.repository;

import com.hm.model.FeatureUsed;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Neeman on 15/10/2017.
 */
public interface FeatureRepository extends PagingAndSortingRepository<FeatureUsed, Long> {

    @Query("SELECT f FROM FeatureUsed f WHERE user_id=:userId")
    public List<FeatureUsed> getFeaturesByUserId(@Param("userId") Long userId);
}
