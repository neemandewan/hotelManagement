package com.hm.dto;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * Created by krish on 10/15/2017.
 */
public class PaymentDTO implements Serializable{

    private Long customerId;
    private Long bookingId;
    private Long amount;
    private boolean status;
    private String paymentType;

    public PaymentDTO() {
    }

    public PaymentDTO(Long customerId, Long bookingId, Long amount, boolean status, String paymentType) {
        this.customerId = customerId;
        this.bookingId = bookingId;
        this.amount = amount;
        this.status = status;
        this.paymentType = paymentType;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
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

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "customerId=" + customerId +
                ", bookingId=" + bookingId +
                ", amount=" + amount +
                ", status=" + status +
                ", paymentType='" + paymentType + '\'' +
                '}';
    }
}
