package com.bootdo.system.shiro;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Tip: 使用AES对数据进行加密解密
 * 算法模式：ECB 密钥
 * 长度：128bits 16位长
 * 偏移量： 默认
 * 补码方式：PKCS5Padding
 * 解密串编码方式：base64
 * Created by Im.Yan on 2017/11/1.
 */

public class ASEUtil {

    protected final static String Key = "cfd9874561230ABC";

    /**
     * 加密
     * @param sSrc 明文数据
     * @param sKey 密匙
     * @return 密文
     */
    public static String Encrypt(String sSrc, String sKey) {
        try{
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

            return new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密
     * @param sSrc 要加密的数据
     * @param sKey 密匙
     * @return 明文数据
     */
    public static String Decrypt(String sSrc, String sKey) {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = new Base64().decode(sSrc); //先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public static void main(String[] args) {

        String content1 = "60";
        Integer score = 120;
        System.out.println("加密前：" + score);
        String content2 = Encrypt(String.valueOf(score), Key);
        System.out.println("加密后：" + content2);
        String content3 = Decrypt(content2, Key);
        System.out.println("解密后：" + content3);
    }
}
