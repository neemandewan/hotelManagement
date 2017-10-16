package com.hm.service;

import com.hm.dto.BookingInputDTO;
import com.hm.model.Booking;
import com.hm.model.Room;
import com.hm.model.RoomType;
import com.hm.repository.BookingRepository;
import com.hm.repository.RoomTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Neeman on 10/10/2017.
 */

@Service("bookingService")
public class BookingServiceImpl implements BookingService {

    @Autowired
    private Logger log;

    private Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Override
    public Booking addBooking(Booking booking) {
        log.info("Adding booking");
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookings(Long userId) {
        log.info("Getting list of booking");
        return bookingRepository.findAllByUserId(userId);
    }

    @Override
    public List<RoomType> getRoomTypes() {
        return roomTypeRepository.findAll();
    }

    @Override
    public RoomType getRoomType(Long id) {
        return roomTypeRepository.findOne(id);
    }

    @Override
    public Booking updateById(Long bookId) {
        bookingRepository.updateById(bookId);
        return null;
    }
}
