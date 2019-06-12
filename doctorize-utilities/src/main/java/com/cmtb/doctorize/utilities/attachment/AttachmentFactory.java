/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.utilities.attachment;

import com.cmtb.doctorize.domain.utilities.AttachmentDisplayObject;
import com.cmtb.doctorize.domain.utilities.StringDefaultImageData64;
import com.cmtb.doctorize.utilities.image.ImageGenerator;
import com.drew.imaging.ImageProcessingException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

/**
 *
 * @author CMTB
 */
@Component("AttachmentFactory")
public class AttachmentFactory {

    @Resource(name = "ImageGenerator")
    private ImageGenerator imageGenerator;

    public File CreateAttachment(AttachmentDisplayObject attachmentFile) throws IOException, ImageProcessingException {
        File file = null;
        String folder = attachmentFile.getPath();

        File fileFolder = new File(folder);
        if (!fileFolder.exists()) {
            fileFolder.mkdirs();
        }
        String fileOutput = folder + attachmentFile.getFileName();
        file = new File(fileOutput);

        switch (attachmentFile.getSourceFileAttachment()) {
            case DATA_IMAGE_64: {
                String imageData64 = attachmentFile.getDataImage64().replace(StringDefaultImageData64.acronym, "");
                imageData64 = imageData64.replace(StringDefaultImageData64.acronymJpg, "");
                imageData64 = imageData64.replace(StringDefaultImageData64.acronymJpeg, "");
                BufferedImage bufferedImage = imageGenerator.createImageFromDataBase64(imageData64);
                ImageIO.write(bufferedImage, attachmentFile.getExtension(), file);
                break;
            }
            case FILE: {
                FileCopyUtils.copy(attachmentFile.getBytes(), file);
                break;
            }
        }
 

        return file;
    }
}
