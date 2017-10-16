package com.hm.controller;

import com.hm.dto.*;
import com.hm.model.Booking;
import com.hm.model.Hotel;
import com.hm.model.Room;
import com.hm.model.RoomType;
import com.hm.service.*;
import com.hm.util.DateRange;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Neeman on 10/10/2017.
 */

@Controller
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private final ModelMapper modelMapper;

    public BookingController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value="/{hotelId}/getRoomTypes", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Response<List<RoomType>> getRoomTypes(@PathVariable("hotelId") Long hotelId) {

        List<RoomType> roomTypeList = bookingService.getRoomTypes();
        return Response.ok(roomTypeList, HttpStatus.OK.value(), HttpStatus.OK.name());
    }

    @RequestMapping(value="/{hotelId}/all", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Response<List<Room>> getHotels(@PathVariable("hotelId") Long hotelId) {
        //User user = userService.findUserById(hotelId);
        List<Room> roomsList = roomService.getRooms(hotelId);
        return Response.ok(roomsList, HttpStatus.OK.value(), HttpStatus.OK.name());
    }

    @RequestMapping(value="/{userId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Response<List<Booking>> getBooking(@PathVariable("userId") Long userId) {
        List<Booking> booking = bookingService.getBookings(userId);
        return Response.ok(booking, HttpStatus.OK.value(), HttpStatus.OK.name());
    }

    @RequestMapping(value="/{hotelId}/newBook/{userId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Response<Booking> addBooking(@PathVariable("hotelId") Long hotelId, @PathVariable("userId") Long userId, @Valid @RequestBody BookingInputDTO bookingInputDTO) {
        System.out.println(bookingInputDTO);
        String[] strBegin = bookingInputDTO.getBeginDate().split("-");
        String[] strEnd = bookingInputDTO.getEndDate().split("-");

        Booking booking = new Booking();
        booking.setBegin_date(LocalDate.of(Integer.valueOf(strBegin[2]), Integer.valueOf(strBegin[0]), Integer.valueOf(strBegin[1])) );
        booking.setEnd_date(LocalDate.of(Integer.valueOf(strEnd[2]), Integer.valueOf(strEnd[0]), Integer.valueOf(strEnd[1])) );

        RoomType rt = bookingService.getRoomType(bookingInputDTO.getRoomType());
        System.out.println(rt);
        List<LocalDate> localDates = new DateRange(booking.getBegin_date(), booking.getEnd_date()).toList();

        for(LocalDate l: localDates) {
            System.out.println(l.getDayOfMonth());
        }

        booking.setUser(userService.findUserById(userId));
        Hotel hotel = hotelService.getHotel(hotelId);

        Map<Long,Room> roomsFromHotel = hotel.getRooms();

        List<Room> roomsAvailable = new ArrayList<Room>();

        int counter = 1;
        for(Long entry : roomsFromHotel.keySet()) {
            Room r = roomsFromHotel.get(entry);

            System.out.println(r.getType());

            Map<LocalDate, Long> room_bookings = r.getDays_reserved();

            boolean found = false;
            Iterator<LocalDate> itDates = localDates.iterator();

            while(itDates.hasNext()){
                LocalDate day = itDates.next();
                if(room_bookings.get(day) != null){
                    found = true;
                    break;
                }
            }

            if(!found && r.getType() == rt && counter <= bookingInputDTO.getNoOfRooms()) {

                System.out.println("got here");
                roomsAvailable.add(r);
                for(LocalDate date: localDates)
                    room_bookings.put(date, booking.getId());

                counter++;
            } else if(counter > bookingInputDTO.getNoOfRooms()) {
                System.out.println("got here: break");
                break;
            }
            System.out.println("got here: increased");

        }

        Set<Room> roomsBooking = new HashSet<Room>(roomsAvailable);
        booking.setRooms(roomsBooking);
        booking.setState(0);

        Booking newBooking = bookingService.addBooking(booking);

        if (newBooking == null) {
            return Response.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name());
        } else {
            return Response.ok(newBooking, HttpStatus.OK.value(), HttpStatus.OK.name());
        }
        //return null;
    }

    @RequestMapping(value="/{bookId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Response<Booking> payBook(@PathVariable("bookId") Long bookId) {

        Booking res = bookingService.updateById(bookId);
        return Response.ok(res, HttpStatus.OK.value(), HttpStatus.OK.name());
    }

    @RequestMapping(value="/{hotelId}/delete/{bookId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Response<Hotel> delHotel(@PathVariable("hotelId") Long hotelId, @PathVariable("roomId") Long roomId) {

        Room res = roomService.deleteById(roomId);
        return Response.ok(null, HttpStatus.OK.value(), HttpStatus.OK.name());
    }
}
