package com.hm.model;

import javax.persistence.*;

/**
 * Created by Neeman on 09/10/2017.
 */

@Entity
@Table(name = "room_type")
public class RoomType {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String description;
    private int occupancy;

    protected RoomType() {}

    public RoomType(String description, int occupancy) {
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
        return description;
    }
}
