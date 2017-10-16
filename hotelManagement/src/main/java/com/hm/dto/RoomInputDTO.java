package com.hm.dto;

/**
 * Created by Neeman on 10/10/2017.
 */
public class RoomInputDTO {
    private int floor;
    private String room_number;
    private long typeId;
    private String typeDescription;
    private long typeOccupacy;
    private int price;

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

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public long getTypeOccupacy() {
        return typeOccupacy;
    }

    public void setTypeOccupacy(long typeOccupacy) {
        this.typeOccupacy = typeOccupacy;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "RoomInputDTO{" +
                "floor=" + floor +
                ", room_number='" + room_number + '\'' +
                ", typeId=" + typeId +
                ", typeDescription='" + typeDescription + '\'' +
                ", typeOccupacy=" + typeOccupacy +
                ", price=" + price +
                '}';
    }
}
