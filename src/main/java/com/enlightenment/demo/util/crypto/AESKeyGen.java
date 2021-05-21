package com.enlightenment.demo.util.crypto;

import javax.crypto.KeyGenerator;

/*
@Note：

@User：NineSun
@Time:2021/5/20   15:11
*/
public class AESKeyGen {
    private static final String aesAlgorithm = "AES";// AES算法
    private static final int AES_KEY_SIZE = 256;    // AES最大加密明文大小

    public static byte[] keyGen() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(aesAlgorithm);
        keyGenerator.init(AES_KEY_SIZE);
        return keyGenerator.generateKey().getEncoded();
    }
}
