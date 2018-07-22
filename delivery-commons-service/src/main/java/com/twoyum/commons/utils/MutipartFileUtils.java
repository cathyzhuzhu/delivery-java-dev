package com.twoyum.commons.utils;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import java.io.File;
import java.io.IOException;

@Component
public class MutipartFileUtils {
    /**
     * MultipartFile 转换成File
     *
     * @param multfile 原文件类型
     * @return File
     * @throws IOException
     */
    public File multipartToFile(MultipartFile multfile) throws IOException {
        CommonsMultipartFile cf = (CommonsMultipartFile)multfile;
        //这个myfile是MultipartFile的
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        File file = fi.getStoreLocation();
        //手动创建临时文件
        if(file.length() <  5 * 1024 * 1024){
            File tmpFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") +
                    file.getName());
            multfile.transferTo(tmpFile);
            return tmpFile;
        }
        return file;
    }
}
