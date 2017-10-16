package com.hm.dto;

/**
 * Created by Neeman on 10/10/2017.
 */
public class ImageInputDTO {
    private String path;

    ImageInputDTO() {

    }

    public ImageInputDTO(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ImageInputDTO{" +
                "path='" + path + '\'' +
                '}';
    }
}
