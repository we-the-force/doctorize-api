/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.utilities.attachment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author cmtb
 */
@Component("CleanFiles")
public class CleanFiles {

    public List<String> deleteFiles(String path, String excludeFile) {

        List<String> deleteFiles = new ArrayList<>();

        File folder = new File(path);
        if ((!folder.isDirectory()) || (!folder.exists())) {
            return deleteFiles;
        }

        File[] files = folder.listFiles();
        for (File file : files) {
            if (!file.getName().toUpperCase().equals(excludeFile.toUpperCase())) {
                this.deleteFile(path + file.getName());
            }
        }

        return deleteFiles;
    }

    public Boolean deleteFile(String path) {
        File file = new File(path);
        Boolean deleted = file.delete();
        return deleted;
    }
    
    public Boolean deleteDirectory(String path) {
        File file = new File(path);
        
        if (!file.exists()){
            return false;
        }
        
        if (file.isDirectory()) { 
            for (File f : file.listFiles()) { 
                deleteFile(f.getPath());  
            } 
        } 
        
        Boolean deleted = file.delete();
        return deleted;
    }
}
