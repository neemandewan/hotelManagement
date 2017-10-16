package com.hm.repository;

import com.hm.model.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Neeman on 09/10/2017.
 */
public interface HotelRepository extends PagingAndSortingRepository<Hotel, Long> {
    @Query("select n from  Hotel n WHERE manager_user_id = :userId")
    public List<Hotel> findAllByUserId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query("update Hotel set address = :address, " +
            "name = :name, " +
            "rating = :rating, " +
            "status = :status, " +
            "category_id = :categoryId, " +
            "description = :description " +
            "WHERE id = :hotelId")
    public void updateHotelById(@Param("address") String address,
                                  @Param("name") String name,
                                  @Param("rating") int rating,
                                  @Param("status") boolean status,
                                  @Param("categoryId") int categoryId,
                                @Param("description") String description,
                                @Param("hotelId") long hotelId);
}
