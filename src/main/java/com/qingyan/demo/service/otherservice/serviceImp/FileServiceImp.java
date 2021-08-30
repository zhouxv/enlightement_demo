package com.qingyan.demo.service.otherservice.serviceImp;

import com.qingyan.demo.service.otherservice.FileService;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class FileServiceImp implements FileService {

    @Override
    public Boolean putObjectIntoFile(File file, Object object) throws Exception {

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(object);
        oos.flush();

        fos.close();
        oos.close();

        return true;
    }

    @Override
    public Object getObjectFromFile(File file) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        Object o = ois.readObject();

        fis.close();
        ois.close();

        return o;
    }

    @Override
    public void fileEDC(File file) throws Exception {
        if (file.exists()) {
            file.delete();
            file.createNewFile();
        }
    }

    @Override
    public File getSampleFile(String fileName) {
        String rootPath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "uploadFile/sampleDataSet";
        File rootFile = new File(rootPath);

        File[] files = rootFile.listFiles();
        for (File file : files) {
            if (prefixOfFile(file.getName()).equals(fileName)) return file;
        }

        return null;
    }

    @Override
    public File getDataCipherFile(String fileName) {
        String rootPath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "uploadFile/dataSetCipher";
        File rootFile = new File(rootPath);

        File[] files = rootFile.listFiles();
        for (File file : files) {

            if (prefixOfFile(file.getName()).equals(fileName)) return file;
        }
        return null;
    }

    // 上传的文件必须有后缀名,否则改方法接收不到
    @Override
    public Boolean uploadSampleData(MultipartFile file, String fileName) throws Exception {
        if (file.isEmpty()) {
            return false;
        }

        System.out.println(file.getOriginalFilename());
        // 1.以文件哈希值作为名字,要有文件后缀名
        String sampleFileName = fileName + suffixOfFile(file.getOriginalFilename());

        //2.指定样本数据集存储的绝对路径,不存在则创建
        String parentFilePath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "uploadFile/sampleDataSet";
        File parentFile = new File(parentFilePath);
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        //3.创建文件
        File sampleDataFile = new File(parentFile, sampleFileName);

        //4.转换请求数据到文件中
        file.transferTo(sampleDataFile);
        return true;
    }

    @Override
    public Boolean uploadDataSetCipher(MultipartFile file, String fileName) throws Exception {
        if (file.isEmpty()) {
            return false;
        }

        // 1.以文件哈希值作为名字,要有文件后缀名
        String sampleFileName = fileName + suffixOfFile(file.getOriginalFilename());

        //2.指定样本数据集存储的绝对路径,不存在则创建
        String parentFilePath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "uploadFile/dataSetCipher";

        File parentFile = new File(parentFilePath);
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        //3.创建文件
        File sampleDataFile = new File(parentFile, sampleFileName);

        //4.转换请求数据到文件中
        file.transferTo(sampleDataFile);
        return true;
    }

    @Override
    public String suffixOfFile(String fileName) {
        int i = fileName.lastIndexOf(".");
        System.out.println(". location: " + i);
        if (i == -1) {
            System.out.println("file no suffix");
            return "";
        } else {
            return fileName.substring(i);
        }
    }

    @Override
    public String prefixOfFile(String fileName) {
        int i = fileName.lastIndexOf(".");
        if (i == -1) {
            return fileName;
        } else {
            return fileName.substring(0, i);
        }
    }
}
