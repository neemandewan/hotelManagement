package com.hm.repository;

import com.hm.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Neeman on 09/10/2017.
 */
public interface ImageRepository extends JpaRepository<Image, Long> {
}
