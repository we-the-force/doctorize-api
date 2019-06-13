/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.user;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author cmtb
 */
public enum UserPhotoThumbnailEnum {
    $_50(50, 50, "50", ".png"),
    $_100(100, 100, "100", ".png"),
    $_200(200, 200, "200", ".png");

    private final int width;
    private final int heigth;
    private final String fileName;
    private final String fileExtension;

    public int getWidth() {
        return width;
    }

    public int getHeigth() {
        return heigth;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public static Map<String, UserPhotoThumbnailEnum> getThumbnails() {
        return thumbnails;
    }

    UserPhotoThumbnailEnum(int width, int heigth, String fileName, String fileExtension) {
        this.width = width;
        this.heigth = heigth;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
    }

    private static final Map<String, UserPhotoThumbnailEnum> thumbnails = new HashMap<>();

    //this is new
    public static Map<String, UserPhotoThumbnailEnum> getThumbnail() {
        return thumbnails;
    }

    static {
        thumbnails.put(String.valueOf(UserPhotoThumbnailEnum.$_50.fileName), UserPhotoThumbnailEnum.$_50);
        thumbnails.put(String.valueOf(UserPhotoThumbnailEnum.$_100.fileName), UserPhotoThumbnailEnum.$_100);
        thumbnails.put(String.valueOf(UserPhotoThumbnailEnum.$_200.fileName), UserPhotoThumbnailEnum.$_200);

    }
}
