package com.hm.repository;

import com.hm.model.Hotel;
import com.hm.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by krish on 10/12/2017.
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    public List<Payment> findAllByCustomerId(@Param("customerId") Long customerId);


}
