package com.hm.controller;

import com.hm.dto.*;
import com.hm.model.Hotel;
import com.hm.model.Room;
import com.hm.model.User;
import com.hm.service.HotelService;
import com.hm.service.ImageService;
import com.hm.service.RoomService;
import com.hm.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Neeman on 10/10/2017.
 */

@Controller
@RequestMapping(value="/api/rooms")
public class RoomController {
    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private final ModelMapper modelMapper;

    public RoomController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value="/{hotelId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Response<List<Room>> getHotels(@PathVariable("hotelId") Long hotelId) {
        //User user = userService.findUserById(hotelId);
        List<Room> roomsList = roomService.getRooms(hotelId);
        return Response.ok(roomsList, HttpStatus.OK.value(), HttpStatus.OK.name());
    }

    @RequestMapping(value="/{hotelId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Response<Room> addRoom(@PathVariable("hotelId") Long hotelId, @Valid @RequestBody RoomInputDTO roomInputDTO) {
        System.out.println(roomInputDTO);
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setFloor(roomInputDTO.getFloor());
        roomDTO.setPrice(roomInputDTO.getPrice());
        roomDTO.setRoom_number(roomInputDTO.getRoom_number());

        RoomTypeDTO ro = new RoomTypeDTO(roomInputDTO.getTypeId(), roomInputDTO.getTypeDescription(), (int) roomInputDTO.getTypeOccupacy());

        roomDTO.setType(ro);

        roomDTO.setHotel(hotelService.getHotel(hotelId));
        Room rm = modelMapper.map(roomDTO, Room.class);

        Boolean checkRoomAv = roomService.checkRoom(roomDTO.getRoom_number(), hotelId);
        if(checkRoomAv) {
            return Response.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Room already exists");
        }else {
            Room newRoom = roomService.addRoom(rm);

            if (newRoom == null) {
                return Response.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name());
            } else {
                return Response.ok(newRoom, HttpStatus.OK.value(), HttpStatus.OK.name());
            }
        }
    }

    @RequestMapping(value="/{hotelId}/{roomId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Response<Hotel> updateHotel(@PathVariable("hotelId") Long hotelId, @PathVariable("roomId") Long roomId, @Valid @RequestBody RoomInputDTO roomInputDTO) {
        System.out.println(roomInputDTO);

        System.out.println(roomInputDTO);
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setFloor(roomInputDTO.getFloor());
        roomDTO.setPrice(roomInputDTO.getPrice());
        roomDTO.setRoom_number(roomInputDTO.getRoom_number());

        RoomTypeDTO ro = new RoomTypeDTO(roomInputDTO.getTypeId(), roomInputDTO.getTypeDescription(), (int) roomInputDTO.getTypeOccupacy());

        roomDTO.setType(ro);

        roomDTO.setHotel(hotelService.getHotel(hotelId));
        Room rm = modelMapper.map(roomDTO, Room.class);

        Boolean res = roomService.updateRoomById(roomDTO, roomId);

        if (res == false) {
            return Response.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name());
        } else {
            return Response.ok(null, HttpStatus.OK.value(), HttpStatus.OK.name());
        }
    }

    @RequestMapping(value="/{hotelId}/{roomId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Response<Hotel> delHotel(@PathVariable("hotelId") Long hotelId, @PathVariable("roomId") Long roomId) {

        Room res = roomService.deleteById(roomId);
        return Response.ok(null, HttpStatus.OK.value(), HttpStatus.OK.name());
    }
}
