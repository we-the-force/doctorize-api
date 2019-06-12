/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.user;

import com.cmtb.doctorize.core.shared.AttachmentComponent;
import com.cmtb.doctorize.domain.shared.ProcessAttachmentEnum;
import com.cmtb.doctorize.domain.user.User;
import com.cmtb.doctorize.domain.utilities.AttachmentDisplayObject;
import com.cmtb.doctorize.domain.utilities.AttachmentResultDisplayObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("UserAttachmentImagesComponent")
public class UserAttachmentImagesComponentImpl implements UserAttachmentImagesComponent {

    @Value("${sugardreams.user.default.avatar}")
    private String DEFAULT_AVATAR;

    @Resource(name = "AttachmentComponent")
    private AttachmentComponent attachmentComponent;

    @Override
    public AttachmentResultDisplayObject attachementPhoto(User user) {
        AttachmentDisplayObject file = new AttachmentDisplayObject();
        AttachmentResultDisplayObject results = new AttachmentResultDisplayObject();

        file.setProcess(ProcessAttachmentEnum.USER);

        if (user.getImageData()== null || user.getImageData().trim().isEmpty()) {
            file.setDataImage64(DEFAULT_AVATAR);
            file.setFileName("logo.png");
        } else {
            if (!user.getImageData().contains("data:image/")) {
                results.setUpdated(Boolean.FALSE);
                return results;
            }
            file.setFileName("logo.png");
            file.setDataImage64(user.getImageData());
        }

        file.setIdentifier(user.getId());
        file.setModule(ModuleEnum.USER);
        file.setTypeAssociationFile(TypeAssociationFileAttachmentEnum.HAS_MANY_FILE);
        file.setSourceFileAttachment(SourceFileAttachmentEnum.DATA_IMAGE_64);

        Map<String, UserPhotoThumbnailEnum> map = UserPhotoThumbnailEnum.getThumbnail();
        List<ThumbnailDisplayObject> thumbnails = new ArrayList<>();

        for (Map.Entry<String, UserPhotoThumbnailEnum> entry : map.entrySet()) {
            ThumbnailDisplayObject thumbnail = new ThumbnailDisplayObject();
            UserPhotoThumbnailEnum thum = entry.getValue();

            thumbnail.setOriginalImage(file.getFileName());
            thumbnail.setNewImage(thum.getFileName());
            thumbnail.setWidth(thum.getWidth());
            thumbnail.setHeigth(thum.getHeigth());
            thumbnail.setExtension(thum.getFileExtension());
            thumbnail.setPath(file.getPath());
            thumbnail.setForceSize(Boolean.FALSE);
            thumbnails.add(thumbnail);
        }

        file.setThumbnails(thumbnails);

        String path = attachmentComponent.attachment(file, Boolean.TRUE);

        results.setPath(path);
        results.setUpdated(Boolean.TRUE);
        return results;
    }

}
