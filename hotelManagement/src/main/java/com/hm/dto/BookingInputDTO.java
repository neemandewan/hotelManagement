package com.hm.dto;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Neeman on 10/10/2017.
 */
public class BookingInputDTO implements Serializable {

    private String beginDate;
    private String endDate;
    private int noOfRooms;
    private long roomType;

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(int noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public long getRoomType() {
        return roomType;
    }

    public void setRoomType(long roomType) {
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "BookingInputDTO{" +
                "beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", noOfRooms=" + noOfRooms +
                ", roomType=" + roomType +
                '}';
    }
}
