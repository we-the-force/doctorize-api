/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.utilities.image;

import com.cmtb.doctorize.domain.utilities.SourceFileAttachmentEnum;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter; 
import org.springframework.stereotype.Component;

/**
 *
 * @author CMTB
 */
@Component("ImageGenerator")
public class ImageGenerator {

    public BufferedImage createImageFromDataBase64(String dataImage64) {
        BufferedImage bufferedImage = null;
        byte[] imageByte;
        try {
            dataImage64=dataImage64.replace(SourceFileAttachmentEnum.DATA_IMAGE_64.getSourceFileAttachmentName(), ""); 
            imageByte =  DatatypeConverter.parseBase64Binary(dataImage64);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            bufferedImage = ImageIO.read(bis);
            bis.close();
 
            
            return bufferedImage;
        } catch (Exception e) {
            throw new ImageGeneratorException(e.getMessage());
        }

    }

}
