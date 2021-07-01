package com.enlightenment.demo.service.otherservice;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.Key;

/**
 * 转换工厂
 * 最常用的只有两个方法--->配合加密和解密使用
 * convertBytesToObject
 * convertObjectToBytes
 */
public interface ConvertService {

    /**
     * RSA公私钥--->字符串
     *
     * @param key
     * @return
     * @throws IOException
     */
    String convertKeyToString(Key key) throws IOException;

    /**
     * AES对称密钥--->字符串
     *
     * @param key
     * @return
     * @throws IOException
     */
    String convertKeyToString(SecretKey key) throws IOException;

    /**
     * 字符串--->RSA公私钥
     *
     * @param key
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    Key convertStringToKey(String key) throws IOException, ClassNotFoundException;

    /**
     * 字符串--->AES对称密钥
     *
     * @param key
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    SecretKey convertStringToSecretKey(String key) throws IOException, ClassNotFoundException;

    /**
     * 字节数组--->对象
     *
     * @param bytes
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    Object convertBytesToObject(byte[] bytes) throws IOException, ClassNotFoundException;

    /**
     * 对象--->字节数组
     *
     * @param obj
     * @return
     * @throws IOException
     */
    byte[] convertObjectToBytes(Object obj) throws IOException;
}
