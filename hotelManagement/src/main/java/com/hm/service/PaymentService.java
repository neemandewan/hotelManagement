package com.hm.service;

import com.hm.model.Payment;

import java.util.List;

/**
 * Created by krish on 10/12/2017.
 */
public interface PaymentService {

    public Payment addPayment(Payment payment);
    public List<Payment> getPayment(Long customerId);

}
