package com.qingyan.demo.util.crypto;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

public class MultipartFileSHA256 {

    /*
    判断输入文件的哈希值与输入的哈希值是否一致
    @param file MultipartFile文件对象
    @param hash
    @return boolean
    */
    public static boolean isHashLegal(MultipartFile file, String hash) throws Exception {
        return hashMultipartFile(file).equals(hash);
    }

    /*
    获取MultipartFile文件的hash
    @param file MultipartFile文件对象
    @return String hash字符串
     */
    public static String hashMultipartFile(MultipartFile file) throws Exception {
        InputStream fis = null;
        String sha256 = null;
        try {
            fis = file.getInputStream();
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            byte[] digest = md.digest();
            sha256 = byte2hexLower(digest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("计算文件hash值错误");
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sha256;
    }

    /*
    2进制转小写16进制
     */
    private static String byte2hexLower(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int i = 0; i < b.length; i++) {
            stmp = Integer.toHexString(b[i] & 0XFF);
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs;
    }
}
