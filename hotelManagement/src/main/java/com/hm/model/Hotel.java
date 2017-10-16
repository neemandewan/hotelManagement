package com.hm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Neeman on 09/10/2017.
 */

@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String name;
    private String address;
    private String description;
    private int rating;
    private boolean status;

    @ManyToOne
    private Category category;

    @JsonBackReference
    @ManyToOne
    private User manager;

    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy="hotel", orphanRemoval = true)
    @MapKeyColumn(name="id")
    private Map<Long, Room> rooms = new HashMap<Long, Room>();

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy="hotel", orphanRemoval = true)
    @MapKeyColumn(name="id")
    private Map<Long, Image> images = new HashMap<Long, Image>();

    public Hotel() {}

    public Hotel(String name, String address, int rating, Category category, boolean status, String description, Image image) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.rating = rating;
        this.category = category;
        this.status = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Map<Long, Room> getRooms() {
        return rooms;
    }

    public void setRooms(Map<Long, Room> rooms) {
        this.rooms = rooms;
    }

    public Map<Long, Image> getImages() {
        return images;
    }

    public void setImages(Map<Long, Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", status=" + status +
                ", category=" + category +
                ", manager=" + manager +
                ", rooms=" + rooms +
                ", images=" + images +
                '}';
    }
}
