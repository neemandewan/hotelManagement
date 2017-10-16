package com.hm.service;

import com.hm.model.Hotel;
import com.hm.model.Image;

/**
 * Created by Neeman on 09/10/2017.
 */
public interface ImageService {
    public Image addImage(Image image);
    public void removeImage(Long id);
}
