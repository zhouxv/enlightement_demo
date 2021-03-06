package com.qingyan.demo.service.otherservice.serviceImp;

import com.qingyan.demo.service.otherservice.FileService;
import com.sun.istack.internal.NotNull;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
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
        ApplicationHome applicationHome = new ApplicationHome(getClass());
        String rootPath = applicationHome.getSource().getParentFile().getAbsolutePath() + "/uploadFile/sampleDataSet";
        File rootFile = new File(rootPath);

        File[] files = rootFile.listFiles();
        for (File file : files) {
            if (prefixOfFile(file.getName()).equals(fileName)) return file;
        }

        return null;
    }

    @Override
    public File getDataCipherFile(String fileName) {
        ApplicationHome applicationHome = new ApplicationHome(getClass());
        String rootPath = applicationHome.getSource().getParentFile().getAbsolutePath() + "/uploadFile/dataSetCipher";
        File rootFile = new File(rootPath);

        File[] files = rootFile.listFiles();
        for (File file : files) {

            if (prefixOfFile(file.getName()).equals(fileName)) return file;
        }
        return null;
    }

    // ?????????????????????????????????,???????????????????????????
    @Override
    public Boolean uploadSampleData(@NotNull MultipartFile file, String fileName) throws Exception {
        if (file.isEmpty()) {
            return false;
        }

        System.out.println(file.getOriginalFilename());
        // 1.??????????????????????????????,?????????????????????
        String sampleFileName = fileName + suffixOfFile(file.getOriginalFilename());

        //2.??????????????????????????????????????????,??????????????????
        //??????jar???????????????????????????????????????.jar????????????
        ApplicationHome applicationHome = new ApplicationHome(getClass());
        File source = applicationHome.getSource();
        String parentFilePath = source.getParentFile().getAbsolutePath() + "/uploadFile/sampleDataSet";

        //window?????????
        //String parentFilePath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "uploadFile/sampleDataSet";

        File parentFile = new File(parentFilePath);
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        //3.????????????
        File sampleDataFile = new File(parentFile, sampleFileName);

        //4.??????????????????????????????
        file.transferTo(sampleDataFile);
        return true;
    }

    @Override
    public Boolean uploadDataSetCipher(@NotNull MultipartFile file, String fileName) throws Exception {
        if (file.isEmpty()) {
            return false;
        }

        // 1.??????????????????????????????,?????????????????????
        String sampleFileName = fileName + suffixOfFile(file.getOriginalFilename());

        //2.??????????????????????????????????????????,??????????????????

        ApplicationHome applicationHome = new ApplicationHome(getClass());
        File source = applicationHome.getSource();
        String parentFilePath = source.getParentFile().getAbsolutePath() + "/uploadFile/dataSetCipher";

        //window?????????
        //String parentFilePath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "uploadFile/dataSetCipher";

        File parentFile = new File(parentFilePath);
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        //3.????????????
        File sampleDataFile = new File(parentFile, sampleFileName);

        //4.??????????????????????????????
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
