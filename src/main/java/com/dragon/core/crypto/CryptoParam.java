package com.dragon.core.crypto;

import lombok.Builder;
import org.apache.commons.lang3.StringUtils;
import java.nio.charset.Charset;

/**
 * @ClassName: CryptoParam
 * @Description: encry/decry params builder
 * @Author: pengl
 * @Date: 2020/3/28 21:16
 * @Version V1.0
 */
@Builder
public class CryptoParam {
    private String data;
    private String key;
    private String iv;
    private Charset charset;

    /**
     * AES/DES3 --> CBC/ECB...
     */
    private WorkModel workModel;
    /**
     * AES/DES3 --> padding:PKCS5padding...
     */
    private Padding padding;
    /**
     * RSA
     */
    private String publicKey;
    private String privateKey;

    /**
     * PBE
     */
    private String salt;

    public String getData() {
        return data;
    }

    public String getKey() {
        return key;
    }

    public String getIv() {
        return iv;
    }

    public Charset getCharset() {
        return charset == null ? Crypto.DEFAULT_CHARSET : charset;
    }

    public WorkModel getWorkModel() {
        return workModel == null ? SymmetricCrypto.DEFAULT_WORK_MODEL : workModel;
    }

    public Padding getPadding() {
        return padding == null ? SymmetricCrypto.DEFAULT_PADDING : padding;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getSalt() {
        return salt;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public void setWorkModel(WorkModel workModel) {
        this.workModel = workModel;
    }

    public void setPadding(Padding padding) {
        this.padding = padding;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * @ClassName: CryptoParam
     * @Description: All must iv, But ECB
     * @Author: pengl
     * @Date: 2020/3/29 12:12
     * @Version V1.0
     */
    public enum WorkModel {
        ECB,
        CBC,
        PCBC,
        CTR,
        CTS,
        CFB,
        CFB128,
        OFB,
        OFB128
    }

    public enum Padding {
        /**
         * this padding model, The original must be a multiple of 8
         */
        NoPadding,
        /**
         * Every encrypted ciphertext is the same
         */
        PKCS5Padding,
        /**
         * Every encrypted ciphertext 50% is the same
         */
        ISO10126Padding
    }

}
