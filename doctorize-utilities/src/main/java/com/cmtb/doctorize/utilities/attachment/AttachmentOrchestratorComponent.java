/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.utilities.attachment;

import com.cmtb.doctorize.domain.utilities.AttachmentDisplayObject;
import com.cmtb.doctorize.domain.utilities.TypeAssociationFileAttachmentEnum;
import com.cmtb.doctorize.utilities.thumbnail.ThumbnailException;
import com.cmtb.doctorize.utilities.thumbnail.ThumbnailGenerator;
import java.io.File;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author CMTB
 */
@Component
public class AttachmentOrchestratorComponent {

    @Resource(name = "AttachmentFactory")
    private AttachmentFactory attachmentFactory;

    @Resource(name = "ThumbnailGenerator")
    private ThumbnailGenerator thumbnailGenerator;

    @Resource(name = "CleanFiles")
    private CleanFiles cleanFiles;

    public File attachment(AttachmentDisplayObject attachment) {
        File file = null;
        try {
            file = attachmentFactory.CreateAttachment(attachment);

            if (attachment.getThumbnails() != null && !attachment.getThumbnails().isEmpty()) {
                thumbnailGenerator.createThumbnails(attachment.getThumbnails());
            }

            if (attachment.getTypeAssociationFile() == TypeAssociationFileAttachmentEnum.HAS_ONLY_FILE) {
                cleanFiles.deleteFiles(attachment.getPath(), attachment.getFileName());
            }

            return file;

        } catch (ThumbnailException e) {
            if (file != null) {
                file.delete();
            }
            throw e;
        } catch (Exception e) {
            throw new AttachmentException(e.getMessage());
        }
    }

    public List<String> deleteAttachments(String path, String excludeFile) {
        return cleanFiles.deleteFiles(path, excludeFile);
    }

    public Boolean deleteAttachments(String path) {
        return cleanFiles.deleteFile(path);
    }
    
    public Boolean deleteAttachmentsAndDirectory(String path) {
        return cleanFiles.deleteDirectory(path);
    }

}
