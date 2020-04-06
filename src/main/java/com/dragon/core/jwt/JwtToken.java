package com.dragon.core.jwt;

import com.dragon.core.compression.Compression;
import com.dragon.core.compression.CompressionException;
import com.dragon.core.compression.CompressionFactory;
import com.dragon.core.crypto.*;
import com.dragon.core.lang.Assert;
import com.dragon.core.lang.StrUtils;
import com.dragon.core.serialize.Serialize;
import com.dragon.core.serialize.SerializeException;
import com.dragon.core.serialize.SerializeFactory;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

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
public class JwtToken<T> implements Token<T>, Serializable {
    private static final Crypto base64 = CryptoFactory.getCrypto(Algorithm.Base64);
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
    private T data;

    @Tolerate
    public JwtToken(){}

    @Override
    public String create() {
        Assert.notBlank(key, "the crypto key is required");
        setDefault();

        //serialize
        byte[] bytes = SerializeFactory.getSerializable(serialize).serialize(this);

        //compression
        if(compression != null){
            bytes = CompressionFactory.getCompression(compression).compress(bytes);
        }

        //encrypt
        byte[] encrypt = CryptoFactory.getCrypto(Algorithm.valueOf(algorithm.toString())).encrypt(CryptoParam.builder().key(key).data(bytes).build());

        return StrUtils.newStringUtf8(base64.encrypt(encrypt));
    }

    @Override
    public boolean verify(String jwt) {
        try {
            parse(jwt, true);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public T parse(String jwt, boolean checkExpire) {
        Assert.notBlank(key, "the crypto key is required");
        Assert.notBlank(jwt, "the jwt token is blank");
        setDefault();
        try {
            //base64 decode
            byte[] data = base64.decrypt(jwt.getBytes());

            //decrypt
            data = CryptoFactory.getCrypto(Algorithm.valueOf(algorithm.toString())).decrypt(CryptoParam.builder().key(key).data(data).build());

            //decompression
            if(compression != null){
                data = CompressionFactory.getCompression(compression).decompress(data);
            }

            //deserialize
            JwtToken<T> jwtToken = SerializeFactory.getSerializable(serialize).deserialize(data, JwtToken.class);

            //check expired
            if (checkExpire && checkIsExpire(jwtToken)) {
                log.warn("jwt token {} is expired !", jwt);
                throw new JwtExpireException("token is expire");
            }

            return jwtToken.getData();

        } catch (CryptoException e) {
            log.warn("jwt token parse exception, CryptoException : {}", e.getMessage(), e);
            throw new JwtInvalidException("invalid token string");
        } catch (CompressionException e) {
            log.warn("jwt token parse exception, CompressionException : {}", e.getMessage(), e);
            throw new JwtInvalidException("invalid token string");
        } catch (SerializeException e) {
            log.warn("jwt token parse exception, SerializeException : {}", e.getMessage(), e);
            throw new JwtInvalidException("invalid token string");
        } catch (JwtExpireException e){
            log.warn("jwt token {} is expired !", jwt);
            throw new JwtExpireException("token is expire");
        }catch (Exception e) {
            log.warn("jwt token parse exception, Exception : {}", e.getMessage(), e);
            throw new JwtInvalidException("invalid token string");
        }
    }

    @Override
    public boolean isExpire() {
        return false;
    }

    private void setDefault() {
        algorithm = algorithm == null ? JwtAlgorithm.AES : algorithm;
        //compression = compression == null ? Compression.DEFAULT : compression;
        serialize = serialize == null ? Serialize.JDK : serialize;
        this.createDate = new Date();
    }

    private boolean checkIsExpire(JwtToken jwtToken) {
        Date expireDate = jwtToken.getExpireDate();
        if (expireDate != null) {
            if (System.currentTimeMillis() > expireDate.getTime()) {
                return true;
            }
        }

        long expire = jwtToken.getExpire();
        if (expire != 0) {
            Date createDate = jwtToken.getCreateDate();
            if (System.currentTimeMillis() > (createDate.getTime() + expire)) {
                return true;
            }
        }
        return false;
    }

}
