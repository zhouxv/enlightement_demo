package com.enlightenment.demo.service.otherservice;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileService {
    Boolean putObjectIntoFile(File file, Object object) throws Exception;

    Object getObjectFromFile(File file) throws Exception;

    /**
     * 判断文件是否存在,存在就删除,建一个新文件
     *
     * @param file
     * @throws Exception
     */
    void fileEDC(File file) throws Exception;

    /**
     * 保存样本文件,文件名是文件的哈希值
     *
     * @param file
     * @param fileName
     * @return
     * @throws Exception
     */
    Boolean uploadSampleData(MultipartFile file, String fileName) throws Exception;

    /**
     * 保存文件,不规定文件名称
     *
     * @param file
     * @return
     * @throws Exception
     */
    Boolean uploadDataSetCipher(MultipartFile file, String fileName) throws Exception;

    String suffixOfFile(String fileName);
}
