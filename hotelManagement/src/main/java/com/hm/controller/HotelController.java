package com.hm.controller;

import com.hm.dto.*;
import com.hm.model.*;
import com.hm.service.HotelService;
import com.hm.service.ImageService;
import com.hm.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Created by Neeman on 09/10/2017.
 */
@Controller
@RequestMapping(value="/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    @Autowired
    private final ModelMapper modelMapper;

    public HotelController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value="/all", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Response<List<Hotel>> getAllHotels() {
        List<Hotel> hotelList = hotelService.getHotels();
        return Response.ok(hotelList, HttpStatus.OK.value(), HttpStatus.OK.name());
    }

    @RequestMapping(value="/{userId}/all", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Response<List<Hotel>> getHotels(@PathVariable("userId") Long userId) {
        User user = userService.findUserById(userId);
        List<Hotel> hotelList = hotelService.getHotels(userId);
        return Response.ok(hotelList, HttpStatus.OK.value(), HttpStatus.OK.name());
    }

    @RequestMapping(value="/{userId}/getCategory", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Response<List<Category>> getCategories(@PathVariable("userId") Long userId) {
        List<Category> categoryList = hotelService.getCategories(userId);
        return Response.ok(categoryList, HttpStatus.OK.value(), HttpStatus.OK.name());
    }

    @RequestMapping(value="/{id}/new", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Response<Hotel> addHotel(@PathVariable("id") Long id, @Valid @RequestBody HotelDTO hotelDTO) {
        System.out.println(hotelDTO);
        User user = userService.findUserById(id);
        Hotel htl = modelMapper.map(hotelDTO, Hotel.class);
        htl.setManager(user);
        Hotel newHotel = hotelService.addHotel(htl);

        if (newHotel == null) {
            return Response.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name());
        } else {
            return Response.ok(newHotel, HttpStatus.OK.value(), HttpStatus.OK.name());
        }
    }

    @RequestMapping(value="/{id}/update/{hotelId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Response<Hotel> updateHotel(@PathVariable("id") Long id, @PathVariable("hotelId") Long hotelId, @Valid @RequestBody HotelDTO hotelDTO) {
        System.out.println(hotelDTO);
        Boolean res = hotelService.updateHotelById(hotelDTO, hotelId);

        if (res == false) {
            return Response.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name());
        } else {
            return Response.ok(null, HttpStatus.OK.value(), HttpStatus.OK.name());
        }
    }

    @RequestMapping(value="/{id}/delete/{hotelId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Response<Hotel> delHotel(@PathVariable("id") Long id, @PathVariable("hotelId") Long hotelId) {

        Hotel res = hotelService.deleteById(hotelId);
        return Response.ok(null, HttpStatus.OK.value(), HttpStatus.OK.name());
    }

    // Upload image based on id of hotel
    @RequestMapping(value="/{hotelId}/image/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Response<Image> addImage(@PathVariable("hotelId") Long hotelId, @Valid @RequestBody ImageInputDTO imageInputDTO) {
        System.out.println(imageInputDTO);
        Hotel hotel = hotelService.getHotel(hotelId);
        ImageDTO imageDTO = new ImageDTO(imageInputDTO.getPath(), hotel);

        Image im = modelMapper.map(imageDTO, Image.class);
        Image res = imageService.addImage(im);

        if (res == null) {
            return Response.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name());
        } else {
            return Response.ok(res, HttpStatus.OK.value(), HttpStatus.OK.name());
        }
    }

    @RequestMapping(value="/{hotelId}/removeImage/{id_image}", method=RequestMethod.GET)
    public @ResponseBody
    Response<Image>  deleteImage(@PathVariable("hotelId") long hotelId, @PathVariable("id_image") long id_image) {
        imageService.removeImage(id_image);
        return Response.ok(null, HttpStatus.OK.value(), HttpStatus.OK.name());
    }
}
