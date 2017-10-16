package com.hm.service;

import com.hm.dto.RoomDTO;
import com.hm.model.Room;
import com.hm.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Neeman on 10/10/2017.
 */

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private Logger log;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public List<Room> getRooms(Long hotelId) {
        return roomRepository.getRoomByHotel(hotelId);
    }

    @Override
    public Room deleteById(Long roomId) {
        roomRepository.delete(roomId);
        return null;
    }

    @Override
    public Boolean updateRoomById(RoomDTO roomDTO, Long id) {

        Boolean value = roomRepository.exists(id);
        if(value) {
            try {
                roomRepository.updateRoomById(roomDTO.getFloor(),
                        roomDTO.getPrice(),
                        roomDTO.getRoom_number(),
                        (int) roomDTO.getType().getId(),
                        id);

                return true;

            }catch(Exception e) {
                log.error("error in room update");
            }
        }

        return false;
    }

    @Override
    public Boolean checkRoom(String room_number, Long hotelId) {
        Room room = roomRepository.checkRoomByHotelRoom(room_number, hotelId);
        if(room == null) {
            log.error("no rooms available");
            return false;
        }
        return true;
    }
}
