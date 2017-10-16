package com.hm.service;

import com.hm.dto.HotelDTO;
import com.hm.model.Category;
import com.hm.model.Hotel;
import com.hm.repository.CategoryRepository;
import com.hm.repository.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Neeman on 09/10/2017.
 */

@Service("hotelService")
public class HotelServiceImpl implements HotelService {

    @Autowired
    private Logger log;

    @Autowired
    private HotelRepository hotelrepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Hotel addHotel(Hotel hotel) {
        hotelrepository.save(hotel);
        return hotel;
    }

    @Override
    public Hotel getHotel(Long id) {
        return hotelrepository.findOne(id);
    }

    @Override
    public List<Hotel> getHotels(Long userId)  {
        //return hotelrepository.findAllByUserId(userId, new PageRequest(1, 40)).getContent();
        return hotelrepository.findAllByUserId(userId);
    }

    @Override
    public Hotel deleteById(Long hotelId) {
        hotelrepository.delete(hotelId);
        return null;
    }

    @Override
    public Boolean updateHotelById(HotelDTO hotelDTO, Long id) {

        Boolean value = hotelrepository.exists(id);
        if(value) {
            try {
                hotelrepository.updateHotelById(hotelDTO.getAddress(),
                        hotelDTO.getName(),
                        hotelDTO.getRating(),
                        hotelDTO.isStatus(),
                        (int) hotelDTO.getCategory().getId(),
                        hotelDTO.getDescription(),
                        id);

                return true;

            }catch(Exception e) {
                log.error("error in hotel update");
            }
        }

        return false;

    }

    @Override
    public List<Category> getCategories(Long userId) {
        return categoryRepository.findAll();
    }

    @Override
    public List<Hotel> getHotels() {
        log.info("Getting list of hotels");
        return (List<Hotel>) hotelrepository.findAll();
    }
}
