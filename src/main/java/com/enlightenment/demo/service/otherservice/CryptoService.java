package com.enlightenment.demo.service.otherservice;


import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 密码套件
 */
public interface CryptoService {
    /**
     * 生成RSA公私钥对
     *
     * @return
     * @throws Exception
     */
    KeyPair genRSAKeyPair() throws Exception;

    /**
     * 生成对称密钥
     *
     * @return
     * @throws Exception
     */
    SecretKey genAESSecretKey() throws Exception;

    /**
     * AES加密
     *
     * @param secretKey
     * @param bytes
     * @return
     * @throws Exception
     */
    byte[] encryptByAES(SecretKey secretKey, byte[] bytes) throws Exception;

    /**
     * AES解密
     *
     * @param secretKey
     * @param bytes
     * @return
     * @throws Exception
     */
    byte[] decryptByAES(SecretKey secretKey, byte[] bytes) throws Exception;

    /**
     * 获取数字摘要
     *
     * @param object
     * @return
     */
    String getDigestString(Object object) throws Exception;

    byte[] getDigestBytes(Object object) throws Exception;

    String convertBytesToHexString(byte[] bytes) throws Exception;

    byte[] encryptByRSA(PublicKey publicKey, byte[] bytes) throws Exception;

    byte[] decryptByRSA(PrivateKey privateKey, byte[] bytes) throws Exception;

    byte[] getSignatureByRSA(PrivateKey privateKey, byte[] bytes) throws Exception;

    byte[] verifySignatureByRSA(PublicKey publicKey, byte[] bytes) throws Exception;

}


