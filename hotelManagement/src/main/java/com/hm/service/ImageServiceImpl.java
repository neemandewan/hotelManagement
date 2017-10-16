package com.hm.service;

import com.hm.model.Image;
import com.hm.repository.ImageRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Neeman on 09/10/2017.
 */

@Service("imageService")
public class ImageServiceImpl implements ImageService {

    @Autowired
    private Logger log;

    @Autowired
    private ImageRepository imageRepository;


    @Override
    public Image addImage(Image image) {
        log.info("Adding image");

        imageRepository.save(image);
        return image;
    }

    @Override
    public void removeImage(Long id) {
        log.info("removing image");

        imageRepository.delete(id);
    }
}
