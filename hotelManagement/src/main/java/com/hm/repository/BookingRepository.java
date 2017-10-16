package com.hm.repository;

import com.hm.model.Booking;
import com.hm.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Neeman on 09/10/2017.
 */

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("select n from  Booking n WHERE user_user_id = :userId")
    public List<Booking> findAllByUserId(@Param("userId") Long userId);

    @Modifying
    @Query("update Booking b set b.state= ?1 WHERE id=?34")
    @Transactional
    public void updateById(@Param("bookingId") Long bookingId);
}
