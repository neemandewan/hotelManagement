package com.hm.service;

import com.hm.dto.RoomDTO;
import com.hm.model.Room;
import com.hm.model.RoomType;

import java.util.List;

/**
 * Created by Neeman on 10/10/2017.
 */
public interface RoomService {

    public Room addRoom(Room room);
    public List<Room> getRooms(Long hotelId);
    public Room deleteById(Long roomId);
    public Boolean updateRoomById(RoomDTO roomDTO, Long id);
    Boolean checkRoom(String room_number, Long hotelId);

}
