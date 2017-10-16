package com.hm.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Neeman on 09/10/2017.
 */


public class HotelDTO implements Serializable {
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private int rating;
    @NotNull
    private boolean status;
    @NotNull
    private CategoryDTO category;

    @NotNull
    private String description;

    HotelDTO() {

    }

    public HotelDTO(String name, String address, int rating, boolean status, CategoryDTO category, String email, String description) {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.status = status;
        this.category = category;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "HotelDTO{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", rating=" + rating +
                ", status=" + status +
                ", category=" + category +
                ", description='" + description + '\'' +
                '}';
    }
}
