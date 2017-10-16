package com.hm.model;

/**
 * Created by Neeman on 09/10/2017.
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hm.util.LocalDatePersistenceConverter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "booking")
@Transactional(propagation = Propagation.REQUIRED)
public class Booking {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate begin_date, end_date;

    private int state;

    @ManyToOne
    private User user;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Room> rooms = new HashSet<Room>();

    public Booking(){}

    public Booking(long id, LocalDate begin_date, LocalDate end_date, int state, User user){
        this.id = id;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.user = user;
        this.state = state;
    }

    public Hotel getHotel()
    {
        return rooms.iterator().next().getHotel();
    }

    public String getRoomType()
    {
        return rooms.iterator().next().getType().toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(LocalDate begin_date) {
        this.begin_date = begin_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public int isState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", begin_date=" + begin_date +
                ", end_date=" + end_date +
                ", state=" + state +
                ", user=" + user +
                ", rooms=" + rooms +
                '}';
    }
}
