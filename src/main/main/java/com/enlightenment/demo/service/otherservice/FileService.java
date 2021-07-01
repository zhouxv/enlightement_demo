package com.enlightenment.demo.service.otherservice;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileService {
    Boolean putObjectIntoFile(File file, Object object) throws Exception;

    Object getObjectFromFile(File file) throws Exception;

    /**
     * 判断文件是否存在,存在就删除,建一个新文件
     */
    void fileEDC(File file) throws Exception;

    /**
     * 保存样本文件,文件名是文件的哈希值
     */
    Boolean uploadSampleData(MultipartFile file, String fileName) throws Exception;

    /**
     * 保存样本文件,文件名是文件的哈希值
     */
    File getSampleFile(String fileName);

    /**
     保存文件,不规定文件名称
     */
    Boolean uploadDataSetCipher(MultipartFile file, String fileName) throws Exception;

    /**
     保存样本文件,文件名是文件的哈希值
     */
    File getDataCipherFile(String fileName);

    String prefixOfFile(String fileName);

    String suffixOfFile(String fileName);


}
