package com.qingyan.demo.service.otherservice.serviceImp;

import com.qingyan.demo.service.otherservice.ConvertService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.io.*;
import java.security.Key;
import java.util.Base64;

@Service
public class ConvertServiceImp implements ConvertService {
    // 密钥--->字符串
    @Override
    public String convertKeyToString(Key key) throws IOException {
        byte[] bytes = convertObjectToBytes(key);
        return Base64.getEncoder().encodeToString(bytes);
    }

    @Override
    public String convertKeyToString(SecretKey key) throws IOException {
        byte[] bytes = convertObjectToBytes(key);
        return Base64.getEncoder().encodeToString(bytes);
    }

    // 字符串--->密钥
    @Override
    public Key convertStringToKey(String key) throws IOException, ClassNotFoundException {
        byte[] decode = Base64.getDecoder().decode(key);
        Object o = convertBytesToObject(decode);
        return (Key) o;
    }

    @Override
    public SecretKey convertStringToSecretKey(String key) throws IOException, ClassNotFoundException {
        byte[] decode = Base64.getDecoder().decode(key);
        Object o = convertBytesToObject(decode);
        return (SecretKey) o;
    }

    // 字节数--->对象
    @Override
    public Object convertBytesToObject(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(in);

        Object o = ois.readObject();

        ois.close();
        in.close();

        return o;
    }

    // 对象--->字节数组
    @Override
    public byte[] convertObjectToBytes(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);

        oos.writeObject(obj);
        oos.flush();

        byte[] bytes = out.toByteArray();

        oos.close();
        out.close();
        return bytes;
    }
}
