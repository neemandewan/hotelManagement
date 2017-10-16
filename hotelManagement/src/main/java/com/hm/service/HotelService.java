package com.hm.service;

import com.hm.dto.HotelDTO;
import com.hm.model.Category;
import com.hm.model.Hotel;

import java.util.List;

/**
 * Created by Neeman on 09/10/2017.
 */
public interface HotelService {

    public Hotel addHotel(Hotel hotel);
    public Hotel getHotel(Long id);
    public List<Hotel> getHotels(Long userId);

    public Hotel deleteById(Long hotelId);
    public Boolean updateHotelById(HotelDTO hotelDTO, Long id);

    public List<Category> getCategories(Long userId);

    public List<Hotel> getHotels();
}
