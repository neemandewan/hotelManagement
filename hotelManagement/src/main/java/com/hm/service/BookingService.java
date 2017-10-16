package com.hm.service;

import com.hm.dto.BookingInputDTO;
import com.hm.model.Booking;
import com.hm.model.Room;
import com.hm.model.RoomType;

import java.util.List;

/**
 * Created by Neeman on 10/10/2017.
 */
public interface BookingService {

    public Booking addBooking(Booking booking);
    public List<Booking> getBookings(Long userId);

    public List<RoomType> getRoomTypes();
    public RoomType getRoomType(Long id);

    public Booking updateById(Long bookId);
}
