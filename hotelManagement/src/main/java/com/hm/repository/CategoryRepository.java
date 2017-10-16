package com.hm.repository;

import com.hm.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Neeman on 09/10/2017.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
