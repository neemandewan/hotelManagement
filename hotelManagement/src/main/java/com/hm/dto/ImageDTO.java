package com.hm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hm.model.Hotel;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Neeman on 09/10/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageDTO implements Serializable {
    private String path;
    private Hotel hotel;
    private LocalDate insertion_date;

    public ImageDTO(String path, Hotel hotel) {
        this.path = path;
        this.hotel = hotel;
        this.insertion_date = LocalDate.now();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public LocalDate getInsertion_date() {
        return insertion_date;
    }

    public void setInsertion_date(LocalDate insertion_date) {
        this.insertion_date = insertion_date;
    }

    @Override
    public String toString() {
        return "ImageDTO{" +
                "path='" + path + '\'' +
                ", hotel=" + hotel +
                ", insertion_date=" + insertion_date +
                '}';
    }
}
