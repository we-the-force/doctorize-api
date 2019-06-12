/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.shared;

import com.cmtb.doctorize.domain.shared.GlobalConfig;
import com.cmtb.doctorize.domain.shared.ModuleEnum;
import com.cmtb.doctorize.domain.shared.ProcessAttachmentEnum;
import com.cmtb.doctorize.domain.shared.ResourcesEnum;
import com.cmtb.doctorize.domain.utilities.AttachmentDisplayObject;
import com.cmtb.doctorize.utilities.attachment.AttachmentOrchestratorComponent;
import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author CMTB
 */
@Component("AttachmentComponent")
public class AttachmentComponentImpl implements AttachmentComponent {

    @Autowired
    private AttachmentOrchestratorComponent attachmentOrchestrator;

    @Override
    public boolean deleteAttachementByPath(String path) {
        if (GlobalConfig.ROOT_FILES.trim().isEmpty()) {
            File file = new File(ResourcesEnum.ROOT_FILES.getResourceName());
            GlobalConfig.ROOT_FILES = file.getAbsolutePath();
            System.out.println(file.getAbsolutePath());
        }

        String root = GlobalConfig.ROOT_FILES.replace(
                ResourcesEnum.ROOT_FOLDER.getResourceName(), "");
        StringBuilder builder = new StringBuilder();
        builder.append(root);
        builder.append(File.separator);
        builder.append(path);

        return attachmentOrchestrator.deleteAttachments(
                builder.toString());
    }
    
    @Override
    public boolean deleteAttachementAndDirectoryByPath(String path) {
        if (GlobalConfig.ROOT_FILES.trim().isEmpty()) {
            File file = new File(ResourcesEnum.ROOT_FILES.getResourceName());
            GlobalConfig.ROOT_FILES = file.getAbsolutePath();
            System.out.println(file.getAbsolutePath());
        }

        String root = GlobalConfig.ROOT_FILES.replace(
                ResourcesEnum.ROOT_FOLDER.getResourceName(), "");
        StringBuilder builder = new StringBuilder();
        builder.append(root);
        builder.append(File.separator);
        builder.append(path);

        return attachmentOrchestrator.deleteAttachmentsAndDirectory(builder.toString());
    }

    @Override
    public boolean deleteAttachmentWhenRelationIsOnlyFile(
            Long identifierId,
            ModuleEnum module,
            ProcessAttachmentEnum process) {
        AttachmentDisplayObject att = new AttachmentDisplayObject();
        att.setIdentifier(identifierId);
        att.setModule(module);
        att.setProcess(process);
        String path = att.getPath();
        List<String> deletedFiles = attachmentOrchestrator.deleteAttachments(path, "");
        return deletedFiles.size() > 0;
    }

    @Override
    public String attachment(AttachmentDisplayObject file,
            Boolean deleteBeforeAttachment) {

        String path = ""; 

        file.validate();

        if (((file.getDataImage64() == null) || (file.getDataImage64().trim().isEmpty()))
                && (file.getBytes() == null)) {
            return file.getFileName();
        } else {

            if (deleteBeforeAttachment) {
                attachmentOrchestrator.deleteAttachments(file.getPath(), "");
            }

            File newFile = attachmentOrchestrator.attachment(file);
            String nonSignificativePath = GlobalConfig.ROOT_FILES.replace(ResourcesEnum.ROOT_FOLDER.getResourceName(), "");

            path = newFile.getAbsolutePath().replace(nonSignificativePath, "");
            path = path.replace(file.getFileName(), "");
            path=path.replace("\\", "/");
        }
        return path;
    }
}
