/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.utilities.thumbnail;

import com.cmtb.doctorize.domain.utilities.ThumbnailDisplayObject;
import java.io.File;
import java.util.List;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Component;

/**
 *
 * @author cmtb
 */
@Component("ThumbnailGenerator")
public class ThumbnailGenerator {

    public File createThumbnail(ThumbnailDisplayObject thumbnail) {
        try {
            String newFile=thumbnail.getPath() + thumbnail.getNewImage() + thumbnail.getExtension();
            if (!thumbnail.getForceSize()) {
                Thumbnails.of(thumbnail.getPath() + thumbnail.getOriginalImage())
                        .size(thumbnail.getWidth(), thumbnail.getHeigth())
                        .toFile(newFile);

            } else {
                Thumbnails.of(thumbnail.getPath() + thumbnail.getOriginalImage())
                        .forceSize(thumbnail.getWidth(), thumbnail.getHeigth())
                        
                        .toFile(newFile);

            }

            return new File(thumbnail.getPath() + thumbnail.getNewImage());
        } catch (Exception e) {
            throw new ThumbnailException(e.getMessage());
        }

    }

    public void createThumbnails(List<ThumbnailDisplayObject> thumbnails) {
        try {

            for (ThumbnailDisplayObject thumbnail : thumbnails) {
                this.createThumbnail(thumbnail);
            }

        } catch (Exception e) {
            throw new ThumbnailException(e.getMessage());
        }

    }

}
