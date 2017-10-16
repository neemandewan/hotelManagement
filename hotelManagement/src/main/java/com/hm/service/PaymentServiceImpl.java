package com.hm.service;

import com.hm.model.Payment;
import com.hm.repository.PaymentRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by krish on 10/12/2017.
 */
@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private Logger log;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Payment payment) {
        paymentRepository.save(payment);
        log.info("Payment has been added");
        return payment;
    }

    @Override
    public List<Payment> getPayment(Long customerId) {
        log.info("returns list of all payments");
        return paymentRepository.findAllByCustomerId(customerId);
    }
}
