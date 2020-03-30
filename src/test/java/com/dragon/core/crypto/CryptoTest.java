package com.dragon.core.crypto;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @ClassName: CryptoTest
 * @Description: TODO
 * @Author: pengl
 * @Date: 2020/3/28 10:31
 * @Version V1.0
 */
public class CryptoTest {
    String str = "!@#$%^&*()_+~（）abc 中文";
    String str2_8 = "qweroiuy12345678";
    private String key = "1asssdsdc212";

    long bg;
    long end;

    @Before
    public void befor(){
        bg = System.currentTimeMillis();
    }

    @After
    public void after(){
        end = System.currentTimeMillis();
        System.out.println("----------all times:>>>" + (end - bg) + " ms");
    }

    @Test
    public void base64Test(){
        String encryt = CryptoFactory.getCrypto(Algorithm.Base64).encrypt(str);
        System.out.println(encryt);
        System.out.println(CryptoFactory.getCrypto(Algorithm.Base64).decrypt(encryt));
    }

    @Test
    public void md5Test(){
        String encryt = CryptoFactory.getCrypto(Algorithm.MD5).encrypt(str);
        System.out.println(encryt);
    }

    @Test
    public void shaTest(){
        System.out.println(CryptoFactory.getCrypto(Algorithm.SHA1).encrypt(str));
        System.out.println(CryptoFactory.getCrypto(Algorithm.SHA256).encrypt(str));
        System.out.println(CryptoFactory.getCrypto(Algorithm.SHA384).encrypt(str));
        System.out.println(CryptoFactory.getCrypto(Algorithm.SHA512).encrypt(str));
    }

    @Test
    public void hmacTest(){
        System.out.println(CryptoFactory.getCrypto(Algorithm.HmacMD5).encrypt(str));
        System.out.println(CryptoFactory.getCrypto(Algorithm.HmacSHA1).encrypt(str));
        System.out.println(CryptoFactory.getCrypto(Algorithm.HmacSHA256).encrypt(str));
        System.out.println(CryptoFactory.getCrypto(Algorithm.HmacSHA384).encrypt(str));
        System.out.println(CryptoFactory.getCrypto(Algorithm.HmacSHA512).encrypt(str));
    }

    @Test
    public void aesTest(){
        String key = "1234567890-=plmkoijnbhuygvcftrdx";
        String iv = "0okmnji98uyhgbvf";
        Crypto aes = CryptoFactory.getCrypto(Algorithm.AES);
        //采用默认key， iv， 工作模式 ，填充方式
        /*String defEncry = aes.encrypt(CryptoParam.builder().data(str).build());
        System.out.println("默认KEY,IV,工作模式,填充方式加密：" + defEncry);
        System.out.println("默认KEY,IV,工作模式,填充方式解密：" + aes.decrypt(defEncry));*/
/*
        defEncry = aes.encrypt(CryptoParam.builder().data(str2_8).key(key).iv(iv).workModel(CryptoParam.WorkModel.CBC).padding(CryptoParam.Padding.NoPadding).build());
        System.out.println("指定KEY,IV,工作模式,填充方式加密：" + defEncry);
        System.out.println("指定KEY,IV,工作模式,填充方式解密：" + aes.decrypt(CryptoParam.builder().data(defEncry).key(key).iv(iv).workModel(CryptoParam.WorkModel.CBC).padding(CryptoParam.Padding.NoPadding).build()));*/
        /*defEncry = aes.encrypt(CryptoParam.builder().data(str).key(key).iv(iv).workModel(CryptoParam.WorkModel.CBC).build());
        System.out.println("指定KEY,IV,工作模式,填充方式加密：" + defEncry);
        System.out.println("指定KEY,IV,工作模式,填充方式解密：" + aes.decrypt(CryptoParam.builder().data(defEncry).key(key).iv(iv).workModel(CryptoParam.WorkModel.CBC).build()));*/


        String defEncry = aes.encrypt(CryptoParam.builder().data(str).workModel(CryptoParam.WorkModel.CFB).build());
        System.out.println("指定工作模式加密：" + defEncry);
        System.out.println("指定工作模式解密：" + aes.decrypt(CryptoParam.builder().data(defEncry).workModel(CryptoParam.WorkModel.CFB).build()));
    }

    /*@Test
    public void des3Test(){
        String encrypt = CryptoFactory.getCrypto(Algorithm.DES3_ECB).encrypt(str);
        System.out.println(encrypt);
        encrypt = CryptoFactory.getCrypto(Algorithm.DES3_ECB).encrypt(str);
        System.out.println(encrypt);
        System.out.println(CryptoFactory.getCrypto(Algorithm.DES3_ECB).decrypt(encrypt));
        System.out.println(CryptoFactory.getCrypto(Algorithm.DES3_ECB).decrypt(encrypt));
    }

    @Test
    public void des3Test2(){
        String encrypt = CryptoFactory.getCrypto(Algorithm.DES3_CBC).encrypt(str);
        System.out.println(encrypt);
        System.out.println(CryptoFactory.getCrypto(Algorithm.DES3_CBC).decrypt(encrypt));
    }

    @Test
    public void aesTest(){
        String encrypt = CryptoFactory.getCrypto(Algorithm.AES_ECB).encrypt(str);
        System.out.println(encrypt);
        System.out.println(CryptoFactory.getCrypto(Algorithm.AES_ECB).decrypt(encrypt));
    }

    @Test
    public void aesTest2(){
        String encrypt = CryptoFactory.getCrypto(Algorithm.AES_CBC).encrypt(str);
        System.out.println(encrypt);
        System.out.println(CryptoFactory.getCrypto(Algorithm.AES_CBC).decrypt(encrypt));
    }*/

    @Test
    public void pbeTest(){
        Crypto pbe = CryptoFactory.getCrypto(Algorithm.PBEWithMd5AndDes);
        String encrypt = pbe.encrypt(str);
        System.out.println(encrypt);
        System.out.println(pbe.decrypt(encrypt));

        encrypt = pbe.encrypt(CryptoParam.builder().data(str).key(key).salt("12345678").build());
        System.out.println(encrypt);
        System.out.println(pbe.decrypt(CryptoParam.builder().data(encrypt).key(key).salt("12345678").build()));

    }

    @Test
    public void rsaTest(){
        KeyPairs keyPairs = RSACoder.keyPairs();
        System.out.println("===========生成密钥对===========\n->PublickKey \n" + keyPairs.getPublicKey() + "\n->PrivateKey \n" + keyPairs.getPrivateKey());
        Crypto rsa = CryptoFactory.getCrypto(Algorithm.RSA);
        //公钥加密，私钥解密
        String encry = rsa.encrypt(CryptoParam.builder().data(str).publicKey(keyPairs.getPublicKey()).build());
        System.out.println("公钥加密：" + encry);

        String decry = rsa.decrypt(CryptoParam.builder().data(encry).privateKey(keyPairs.getPrivateKey()).build());
        System.out.println("私钥解密：" + decry);

        //私钥加密，公钥解密
        encry = rsa.encrypt(CryptoParam.builder().data(str).privateKey(keyPairs.getPrivateKey()).build());
        System.out.println("私钥加密：" + encry);
        decry = rsa.decrypt(CryptoParam.builder().data(encry).publicKey(keyPairs.getPublicKey()).build());
        System.out.println("公钥解密：" + decry);
    }
}
