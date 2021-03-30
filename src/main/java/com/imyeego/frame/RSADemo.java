package com.imyeego.frame;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;

/**
 * @authur : liuzhao
 * @date : 2/22/21:8:29 PM
 * @des :
 */
public class RSADemo {

    /**RSA算法*/
    public static final String RSA = "RSA";
    /**加密方式，标准jdk的*/
    public static final String TRANSFORMATION = "RSA/ECB/PKCS1Padding";

    public static void main(String[] args) {

        int length = 32;
        int num = 100;

        System.out.println(num%length);
        System.out.println(num&(length - 1));


//        String data = "hello liuzhao";
//
//        try {
//            KeyPair keyPair = generateRSAKeyPair(1024);
//
//            byte[] publicKey = getPublicKey(keyPair);
//            System.out.println("加密的公钥 " + encryptBASE64(publicKey));
//
//            byte[] privateKey = getPrivateKey(keyPair);
//
//            byte[] encrypted = encryptByPublicKey(data.getBytes(), publicKey);
//
//            System.out.println("加密后的数据 " + encryptBASE64(encrypted));
//
//            byte[] originalBytes = decryptByPrivateKey(encrypted, privateKey);
//            System.out.println("解密后的数据 " + new String(originalBytes));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    public static byte[] encryptByPublicKey(byte[] data, byte[] publicKey) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PublicKey key = keyFactory.generatePublic(keySpec);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    public static byte[] decryptByPrivateKey(byte[] encrypted, byte[] privateKey) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PrivateKey key = keyFactory.generatePrivate(keySpec);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(encrypted);

    }

    public static KeyPair generateRSAKeyPair(final int length) throws NoSuchAlgorithmException{
        KeyPairGenerator generator = KeyPairGenerator.getInstance(RSA);
        generator.initialize(length);
        return generator.genKeyPair();
    }

    public static byte[] getPublicKey(KeyPair keyPair) {
        RSAPublicKey key = (RSAPublicKey) keyPair.getPublic();
        return key.getEncoded();
    }

    public static byte[] getPrivateKey(KeyPair keyPair) {
        RSAPrivateKey key = (RSAPrivateKey) keyPair.getPrivate();
        return key.getEncoded();
    }

    //解码返回byte
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    //编码返回字符串
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

 }
