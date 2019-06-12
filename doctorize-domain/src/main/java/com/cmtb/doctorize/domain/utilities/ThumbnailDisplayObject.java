/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.utilities;

/**
 *
 * @author CMTB
 */
public class ThumbnailDisplayObject {

    private String path;
    private String originalImage;
    private String newImage;
    private Integer width;
    private Integer heigth;
    private String extension;
    private Boolean forceSize;

    public ThumbnailDisplayObject() {
        this.forceSize = false;
    }

    public String getExtension() {
        return this.extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getOriginalImage() {
        return this.originalImage;
    }

    public void setOriginalImage(String originalImage) {
        this.originalImage = originalImage;
    }

    public String getNewImage() {
        return this.newImage;
    }

    public void setNewImage(String newImage) {
        this.newImage = newImage;
    }

    public Integer getWidth() {
        return this.width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeigth() {
        return this.heigth;
    }

    public void setHeigth(Integer heigth) {
        this.heigth = heigth;
    }

    public void setForceSize(Boolean forceSize) {
        this.forceSize = forceSize;
    }

    public Boolean getForceSize() {
        return this.forceSize;
    }
}
