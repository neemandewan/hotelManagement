package com.hm.dto;

import com.hm.model.Hotel;

import java.io.Serializable;

/**
 * Created by Neeman on 10/10/2017.
 */
public class RoomDTO implements Serializable {
    private long id;
    private int floor;
    private String room_number;
    private RoomTypeDTO type;
    private Hotel hotel;
    private int price;

    public RoomDTO() {

    }

    public RoomDTO(long id, int floor, String room_number, RoomTypeDTO type, Hotel hotel, int price) {
        this.id = id;
        this.floor = floor;
        this.room_number = room_number;
        this.type = type;
        this.hotel = hotel;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public RoomTypeDTO getType() {
        return type;
    }

    public void setType(RoomTypeDTO type) {
        this.type = type;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "id=" + id +
                ", floor=" + floor +
                ", room_number='" + room_number + '\'' +
                ", type=" + type +
                ", hotel=" + hotel +
                ", price=" + price +
                '}';
    }
}
