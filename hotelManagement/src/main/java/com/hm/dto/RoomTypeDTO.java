package com.hm.dto;

import java.io.Serializable;

/**
 * Created by Neeman on 10/10/2017.
 */
public class RoomTypeDTO implements Serializable {

    private long id;
    private String description;
    private int occupancy;

    RoomTypeDTO() {

    }

    public RoomTypeDTO(long id, String description, int occupancy) {
        this.id = id;
        this.description = description;
        this.occupancy = occupancy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    @Override
    public String toString() {
        return "RoomTypeDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", occupancy=" + occupancy +
                '}';
    }
}
