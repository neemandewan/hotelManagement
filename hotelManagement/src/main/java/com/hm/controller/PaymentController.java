package com.hm.controller;

import com.hm.dto.HotelDTO;
import com.hm.dto.PaymentDTO;
import com.hm.dto.Response;
import com.hm.model.Payment;
import com.hm.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by krish on 10/15/2017.
 */

@Controller
@RequestMapping(value ="/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @Autowired
    private final ModelMapper modelMapper;

    public PaymentController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value ="/{customerId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Response<List<Payment>> findAllByUserId(@PathVariable("customerId") Long userId){

        if(userId == null){
            return Response.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name());
        }
        List<Payment> paymentList = paymentService.getPayment(userId);
        return Response.ok(paymentList, HttpStatus.OK.value(),HttpStatus.OK.name());

    }

    @RequestMapping(value="/{customerId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Response<Payment> addHotel(@PathVariable("customerId") Long customerId, @Valid @RequestBody PaymentDTO paymentDTO){

        System.out.println(paymentDTO);

        Payment pmt = modelMapper.map(paymentDTO, Payment.class);

        Payment payment = paymentService.addPayment(pmt);

        if (payment == null) {
            return Response.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name());
        } else {
            return Response.ok(payment, HttpStatus.OK.value(), HttpStatus.OK.name());
        }
    }
}
