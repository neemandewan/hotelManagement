package com.hm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by krish on 10/12/2017.
 */
@Entity
@Table(name = "payment")
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="customer_id")
    private Long customerId;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "status")
    private boolean status;

    private Long bookingId;

    @Column(name="payment_type")
    private String paymentType;

    //private User user;
    public Payment(){}


    public Payment(Long customerId, Long amount, boolean status, Long bookingId, String paymentType) {
        this.customerId = customerId;
        this.amount = amount;
        this.status = status;
        this.bookingId = bookingId;
        this.paymentType = paymentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", amount=" + amount +
                ", status=" + status +
                ", bookingId=" + bookingId +
                ", paymentType='" + paymentType + '\'' +
                '}';
    }
}
