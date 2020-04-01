package com.dragon.core.jwt;

import com.dragon.core.compression.Compression;
import com.dragon.core.compression.CompressionException;
import com.dragon.core.compression.CompressionFactory;
import com.dragon.core.crypto.*;
import com.dragon.core.lang.Assert;
import com.dragon.core.serialize.Serialize;
import com.dragon.core.serialize.SerializeException;
import com.dragon.core.serialize.SerializeFactory;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: JwtToken
 * @Description: JwtToken core class
 * @Author: pengl
 * @Date: 2020/4/1 20:57
 * @Version V1.0
 */
@Slf4j
@Data
@Builder
public class JwtToken implements Token, Serializable {
    private static final Crypto base64 =  CryptoFactory.getCrypto(Algorithm.Base64);
    /**
     * crypto algorithm
     */
    private JwtAlgorithm algorithm;
    /**
     * compression algorithm
     */
    private Compression compression;
    /**
     * Serialize type
     */
    private Serialize serialize;
    /**
     * the crypto key
     */
    private String key;
    /**
     * create date
     */
    private Date createDate;
    /**
     * expire date
     */
    private Date expireDate;
    /**
     * expire offset (ms)
     */
    private long expire;
    /**
     * biz data
     */
    private Object data;


    @Override
    public String create() {
        Assert.notBlank(key, "the crypto key is required");
        setDefault();

        //serialize
        byte[] bytes = SerializeFactory.getSerializable(serialize).serialize(this);

        //compression
        bytes = CompressionFactory.getCompression(compression).compress(bytes);

        //encrypt
        String encrypt = CryptoFactory.getCrypto(Algorithm.valueOf(algorithm.toString())).encrypt(CryptoParam.builder().key(key).bytes(bytes).build());

        return encrypt;
    }

    @Override
    public boolean verify() {
        return false;
    }

    @Override
    public Object parse(String jwt) {
        Assert.notBlank(key, "the crypto key is required");
        Assert.notBlank(jwt, "the jwt token is blank");
        setDefault();
        try {
            //base64 decode
            //String decrypt = base64.decrypt(jwt);

            //decrypt
            String decrypt = CryptoFactory.getCrypto(Algorithm.valueOf(algorithm.toString())).encrypt(CryptoParam.builder().key(key).data(jwt).build());

            //decompression
            byte[] decryptBytes = CompressionFactory.getCompression(compression).decompress(decrypt.getBytes());

            //deserialize
            JwtToken jwtToken = (JwtToken)SerializeFactory.getSerializable(serialize).deserialize(decryptBytes);

            //check expired
            checkExpire(jwtToken);

            return jwtToken.getData();

        }catch (CryptoException e){
            e.printStackTrace();
            throw new JwtInvalidException("invalid token string");
        }catch (CompressionException e){
            e.printStackTrace();
            throw new JwtInvalidException("invalid token string");
        }catch (SerializeException e){
            e.printStackTrace();
            throw new JwtInvalidException("invalid token string");
        }
    }

    @Override
    public boolean isExpire() {
        return false;
    }

    private void setDefault(){
        algorithm = algorithm == null ? JwtAlgorithm.AES : algorithm;
        compression = compression == null ? Compression.DEFAULT : compression;
        serialize = serialize == null ? Serialize.JDK : serialize;
        this.createDate = new Date();
    }

    private void checkExpire(JwtToken jwtToken){
        Date expireDate = jwtToken.getExpireDate();
        if(expireDate != null){
            if(System.currentTimeMillis() > expireDate.getTime()){
                throw new JwtExpireException("token is expired");
            }
        }

        long expire = jwtToken.getExpire();
        Date createDate = jwtToken.getCreateDate();
        if(System.currentTimeMillis() > (createDate.getTime() + expire)){
            throw new JwtExpireException("token is expired");
        }
    }

}
