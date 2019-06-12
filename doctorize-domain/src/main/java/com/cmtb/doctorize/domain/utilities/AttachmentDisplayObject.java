/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.utilities;

import com.cmtb.doctorize.domain.shared.GlobalConfig;
import com.cmtb.doctorize.domain.shared.ModuleEnum;
import com.cmtb.doctorize.domain.shared.ProcessAttachmentEnum;
import com.cmtb.doctorize.domain.shared.ResourcesEnum;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CMTB
 */
public class AttachmentDisplayObject {

    private ModuleEnum module;
    private ProcessAttachmentEnum process;
    private Long identifier;
    private String fileName;
    private SourceFileAttachmentEnum sourceFileAttachment;
    private String dataImage64;
    private List<ThumbnailDisplayObject> thumbnails = new ArrayList<>();
    private TypeAssociationFileAttachmentEnum typeAssociationFile;
    private Integer height;
    private Integer width;
    private byte[] bytes;
    private Long size;

    public AttachmentDisplayObject() {
        this.typeAssociationFile = TypeAssociationFileAttachmentEnum.HAS_MANY_FILE;
    }

    public TypeAssociationFileAttachmentEnum getTypeAssociationFile() {
        return typeAssociationFile;
    }

    public void setTypeAssociationFile(TypeAssociationFileAttachmentEnum typeAssociationFile) {
        this.typeAssociationFile = typeAssociationFile;
    }

    public List<ThumbnailDisplayObject> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(List<ThumbnailDisplayObject> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public ModuleEnum getModule() {
        return module;
    }

    public void setModule(ModuleEnum module) {
        this.module = module;
    }

    public ProcessAttachmentEnum getProcess() {
        return process;
    }

    public void setProcess(ProcessAttachmentEnum process) {
        this.process = process;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public SourceFileAttachmentEnum getSourceFileAttachment() {
        return sourceFileAttachment;
    }

    public void setSourceFileAttachment(SourceFileAttachmentEnum sourceFileAttachment) {
        this.sourceFileAttachment = sourceFileAttachment;
    }

    public String getDataImage64() {
        return dataImage64;
    }

    public void setDataImage64(String dataImage64) {
        this.dataImage64 = dataImage64;
    }

    public String getExtension() {
        Integer index = this.fileName.lastIndexOf(".");
        Integer size = this.fileName.length();
        String extension = this.fileName.substring(index + 1, size);

        return extension;
    }
    
    public String getAbsolutePath() {

        StringBuilder builder = new StringBuilder();
        builder.append(ResourcesEnum.ROOT_FOLDER.getResourceName());
        builder.append(File.separator);
        builder.append(this.module.getModuleName());
        builder.append(File.separator);
        builder.append(this.process.getProcessName());
        builder.append(File.separator);
        builder.append(this.identifier);

        return builder.toString();
    }

    public String getPath() {
        if (GlobalConfig.ROOT_FILES.trim().isEmpty()) {
            File file = new File(ResourcesEnum.ROOT_FILES.getResourceName() + ResourcesEnum.ROOT_FOLDER.getResourceName());
            GlobalConfig.ROOT_FILES = file.getAbsolutePath();
            System.out.println(file.getAbsolutePath());
        }

        StringBuilder builder = new StringBuilder();
        builder.append(GlobalConfig.ROOT_FILES);
        builder.append(File.separator);
        builder.append(this.module.getModuleName());
        builder.append(File.separator);
        builder.append(this.process.getProcessName());
        builder.append(File.separator);
        builder.append(this.identifier);
        builder.append(File.separator);

        return builder.toString();
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public void validate() {
        if (this.sourceFileAttachment == SourceFileAttachmentEnum.FILE) {
            Long maxSize = this.process.getMaxSize();
            if (this.size > maxSize) {
                throw new IllegalArgumentException("Invalid maxSize");
            }
            String extension = getExtension();
            Boolean matchExtension = this.process.getPermittedExtensions().stream()
                    .anyMatch(x -> (x == null ? extension == null : x.equals(extension)));
            if (!matchExtension) {
                throw new IllegalArgumentException("Invalid extension");
            }
        }
    }

}
