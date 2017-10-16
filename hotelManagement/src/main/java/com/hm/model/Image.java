package com.hm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Neeman on 09/10/2017.
 */

@Entity
@Table(name = "image")
public class Image {

    private String path;

    @JsonBackReference
    @ManyToOne
    private Hotel hotel;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private LocalDate insertion_date;

    public Image(){}

    public Image(String path, Hotel hotel, LocalDate insertion_date){
        this.path = path;
        this.hotel = hotel;
        this.insertion_date = insertion_date;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Image{" +
                "path='" + path + '\'' +
                ", hotel=" + hotel +
                ", id=" + id +
                ", insertion_date=" + insertion_date +
                '}';
    }
}
