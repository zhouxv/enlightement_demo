package com.enlightenment.demo.service.otherservice.serviceImp;

import com.enlightenment.demo.service.otherservice.ConvertService;
import com.enlightenment.demo.service.otherservice.CryptoService;
import org.apache.commons.lang3.ArrayUtils;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.*;

@Service
public class CryptoServiceImp implements CryptoService {
    private static final String rsaAlgorithm = "RSA";// RSA算法
    private static final int RSA_KEY_SIZE = 1024;// 128对应1024,256对应2048
    private static final int MAX_ENCRYPT_BLOCK = 117;// RSA最大加密明文大小
    private static final int MAX_DECRYPT_BLOCK = RSA_KEY_SIZE / 8;// RSA最大解密明文大小
    private static final String aesAlgorithm = "AES";// AES算法
    private static final int AES_KEY_SIZE = 128;// AES最大加密明文大小
    private static final String sha256Algorithm = "SHA-256";// SHA-256算法
    private static KeyPairGenerator keyPairGenerator;// RSA公私钥对生成器
    private static KeyGenerator keyGenerator;// AES对称密钥生成器
    private static Cipher cipher;// 加密组件
    private final ConvertService convertService;

    public CryptoServiceImp(ConvertService convertService) {
        this.convertService = convertService;
    }

    @Override
    public KeyPair genRSAKeyPair() throws Exception {
        keyPairGenerator = KeyPairGenerator.getInstance(rsaAlgorithm);
        keyPairGenerator.initialize(RSA_KEY_SIZE);
        return keyPairGenerator.generateKeyPair();
    }

    @Override
    public SecretKey genAESSecretKey() throws Exception {
        keyGenerator = KeyGenerator.getInstance(aesAlgorithm);
        keyGenerator.init(AES_KEY_SIZE);
        return keyGenerator.generateKey();
    }

    @Override
    public byte[] encryptByAES(SecretKey secretKey, byte[] bytes) throws Exception {
        cipher = Cipher.getInstance(aesAlgorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // 加密时超过117字节就报错,分段加密
        byte[] finalBytes = null;
        for (int i = 0; i < bytes.length; i += MAX_ENCRYPT_BLOCK) {
            // 注意要使用2的倍数，否则会出现加密后的内容再解密时为乱码
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(bytes, i, i + MAX_ENCRYPT_BLOCK));
            finalBytes = ArrayUtils.addAll(finalBytes, doFinal);
        }

        return finalBytes;
    }

    @Override
    public byte[] decryptByAES(SecretKey secretKey, byte[] bytes) throws Exception {
        cipher = Cipher.getInstance(aesAlgorithm);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // 分段解密,64位解码加密后的字符串
        byte[] finalBytes = null;
        // 解密时超过字节报错。为此采用分段解密的办法来解密
        for (int i = 0; i < bytes.length; i += MAX_DECRYPT_BLOCK) {
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(bytes, i, i + MAX_DECRYPT_BLOCK));
            finalBytes = ArrayUtils.addAll(finalBytes, doFinal);
        }

        return finalBytes;
    }

    @Override
    public String getDigestString(Object object) throws Exception {
        ConvertServiceImp imp = new ConvertServiceImp();
        byte[] bytes = MessageDigest.getInstance(sha256Algorithm).digest(convertService.convertObjectToBytes(object));
        return Hex.toHexString(bytes);// import org.bouncycastle.util.encoders.Hex;
    }

    @Override
    public byte[] getDigestBytes(Object object) throws Exception {
        ConvertServiceImp imp = new ConvertServiceImp();
        byte[] bytes = MessageDigest.getInstance(sha256Algorithm).digest(convertService.convertObjectToBytes(object));
        return bytes;
    }

    @Override
    public String convertBytesToHexString(byte[] bytes) throws Exception {
        return Hex.toHexString(bytes);
    }

    @Override
    public byte[] encryptByRSA(PublicKey publicKey, byte[] bytes) throws Exception {
        cipher = Cipher.getInstance(rsaAlgorithm);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        // 加密时超过117字节就报错,分段加密
        byte[] finalBytes = null;
        for (int i = 0; i < bytes.length; i += MAX_ENCRYPT_BLOCK) {
            // 注意要使用2的倍数，否则会出现加密后的内容再解密时为乱码
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(bytes, i, i + MAX_ENCRYPT_BLOCK));
            finalBytes = ArrayUtils.addAll(finalBytes, doFinal);
        }

        return finalBytes;
    }

    @Override
    public byte[] decryptByRSA(PrivateKey privateKey, byte[] bytes) throws Exception {
        cipher = Cipher.getInstance(rsaAlgorithm);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        // 分段解密,64位解码加密后的字符串
        byte[] finalBytes = null;
        // 解密时超过字节报错。为此采用分段解密的办法来解密
        for (int i = 0; i < bytes.length; i += MAX_DECRYPT_BLOCK) {
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(bytes, i, i + MAX_DECRYPT_BLOCK));
            finalBytes = ArrayUtils.addAll(finalBytes, doFinal);
        }

        return finalBytes;
    }

    @Override
    public byte[] getSignatureByRSA(PrivateKey privateKey, byte[] bytes) throws Exception {
        cipher = Cipher.getInstance(rsaAlgorithm);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        // 加密时超过117字节就报错,分段加密
        byte[] finalBytes = null;
        for (int i = 0; i < bytes.length; i += MAX_ENCRYPT_BLOCK) {
            // 注意要使用2的倍数，否则会出现加密后的内容再解密时为乱码
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(bytes, i, i + MAX_ENCRYPT_BLOCK));
            finalBytes = ArrayUtils.addAll(finalBytes, doFinal);
        }

        return finalBytes;
    }

    @Override
    public byte[] verifySignatureByRSA(PublicKey publicKey, byte[] bytes) throws Exception {
        cipher = Cipher.getInstance(rsaAlgorithm);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        // 分段解密,64位解码加密后的字符串
        byte[] finalBytes = null;
        // 解密时超过字节报错。为此采用分段解密的办法来解密
        for (int i = 0; i < bytes.length; i += MAX_DECRYPT_BLOCK) {
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(bytes, i, i + MAX_DECRYPT_BLOCK));
            finalBytes = ArrayUtils.addAll(finalBytes, doFinal);
        }

        return finalBytes;
    }

}
