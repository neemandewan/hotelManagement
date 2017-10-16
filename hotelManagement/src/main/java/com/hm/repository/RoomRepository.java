package com.hm.repository;

import com.hm.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Neeman on 09/10/2017.
 */
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT r FROM Room r WHERE room_number = :room_number AND hotel_id = :hotelId")
    public Room checkRoomByHotelRoom(@Param("room_number") String room_number, @Param("hotelId") Long hotelId);


    @Query("SELECT r FROM Room r WHERE hotel_id = :hotelId")
    public List<Room> getRoomByHotel(@Param("hotelId") Long hotelId);

    @Modifying
    @Transactional
    @Query("update Room set floor = :floor, " +
            "price = :price, " +
            "room_number = :roomNumber, " +
            "type_id = :typeId " +
            "WHERE id = :roomId")
    public void updateRoomById(@Param("floor") int floor,
                                @Param("price") int price,
                                @Param("roomNumber") String roomNumber,
                                @Param("typeId") int typeId,
                                @Param("roomId") long roomId);
}
