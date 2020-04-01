package com.dragon.core.crypto;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

/**
 * @ClassName: SymmetricCrypto
 * @Description: 对称加密
 * @Author: pengl
 * @Date: 2020/3/28 15:44
 * @Version V1.0
 */
public abstract class SymmetricCrypto implements Crypto {
    /**
     * default key, size:24 (DES)
     */
    protected static final String DEFAULT_KEY_24 = "~!@#$%^&*()_+QWERTabcGBN";
    /**
     * default key, size:32 (AES)
     */
    protected static final String DEFAULT_KEY_32 = "1ph~!@#$%^&*(+QBNb*&^)$%#sp75gqp";
    /**
     * default iv, size:8 (DES-CBC)
     */
    protected static final String DEFAULT_IV_8 = ")(*&*^%@";
    /**
     * default iv, size:16 (AES-CBC)
     */
    protected static final String DEFAULT_IV_16 = "ij018395)(*&*^%@";
    /**
     * default padding
     */
    protected static final CryptoParam.Padding DEFAULT_PADDING = CryptoParam.Padding.PKCS5Padding;
    /**
     * default work model
     */
    protected static final CryptoParam.WorkModel DEFAULT_WORK_MODEL = CryptoParam.WorkModel.ECB;
    /**
     * Separator
     */
    protected static final String CIPHER_SEPARATOR = "/";

    protected static final int MIN_KEY_SIZE_32 = 32;
    protected static final int MIN_KEY_SIZE_24 = 24;
    protected static final int IV_SIZE_16 = 16;
    protected static final int IV_SIZE_8 = 8;

    @Override
    public String encryptString(String data) {
        warn();
        return encryptString(CryptoParam.builder().data(data).build());
    }

    @Override
    public String decryptString(String data) {
        warn();
        return decryptString(CryptoParam.builder().data(data).build());
    }

    @Override
    public byte[] decryptBytes(CryptoParam param) {
        return cryptBts(param, 2);
    }


    @Override
    public byte[] encryptBytes(CryptoParam param) {
        return cryptBts(param, 1);
    }


    @Override
    public String decryptString(CryptoParam param) {
        return crypt(param, 2);
    }


    @Override
    public String encryptString(CryptoParam param) {
        return crypt(param, 1);
    }

    private byte[] cryptBts(CryptoParam param, int type) {
        param.checkData();
        String data = param.getData();
        if (StringUtils.isBlank(data)) {
            return null;
        }
        String key = StringUtils.isBlank(param.getKey()) ? (Algorithm.AES == current() ? DEFAULT_KEY_32 : DEFAULT_KEY_24) : param.getKey();
        checkKeySize(key);
        param.setKey(key);

        String iv = StringUtils.isBlank(param.getIv()) ? (Algorithm.AES == current() ? DEFAULT_IV_16 : DEFAULT_IV_8) : param.getIv();
        checkIvSize(iv);
        param.setIv(iv);

        return type == 1 ? encryBts(param) : decryBts(param);
    }

    private String crypt(CryptoParam param, int type) {
        param.checkData();
        String data = param.getData();
        if (StringUtils.isBlank(data)) {
            return null;
        }
        String key = StringUtils.isBlank(param.getKey()) ? (Algorithm.AES == current() ? DEFAULT_KEY_32 : DEFAULT_KEY_24) : param.getKey();
        checkKeySize(key);
        param.setKey(key);

        String iv = StringUtils.isBlank(param.getIv()) ? (Algorithm.AES == current() ? DEFAULT_IV_16 : DEFAULT_IV_8) : param.getIv();
        checkIvSize(iv);
        param.setIv(iv);

        return type == 1 ? encry(param) : decry(param);
    }

    private void checkKeySize(String key) {
        if (Algorithm.AES == current()) {
            if (key.length() < MIN_KEY_SIZE_32) {
                throw new CryptoException("the key size must be greater than or equal to " + MIN_KEY_SIZE_32);
            }
        } else if (Algorithm.DES3 == current()) {
            if (key.length() < MIN_KEY_SIZE_24) {
                throw new CryptoException("the key size must be greater than or equal to " + MIN_KEY_SIZE_24);
            }
        }
    }

    private void checkIvSize(String iv) {
        if (Algorithm.AES == current()) {
            if (iv.length() < IV_SIZE_16) {
                throw new CryptoException("the iv size must be " + IV_SIZE_16);
            }
        } else if (Algorithm.DES3 == current()) {
            if (iv.length() < IV_SIZE_8) {
                throw new CryptoException("the iv size must be " + IV_SIZE_8);
            }
        }
    }

    private String encry(CryptoParam param) {
        try {
            Cipher cipher = Cipher.getInstance(current().getCode() + CIPHER_SEPARATOR + param.getWorkModel() + CIPHER_SEPARATOR + param.getPadding());
            initCipher(cipher, Cipher.ENCRYPT_MODE, param);
            return Hex.encodeHexString(cipher.doFinal(param.getData().getBytes(param.getCharset())));
        } catch (Exception e) {
            e.printStackTrace();
            throw new CryptoException(e.getMessage());
        }
    }

    private String decry(CryptoParam param) {
        try {
            byte[] mingwen = Hex.decodeHex(param.getData());
            Cipher cipher = Cipher.getInstance(current().getCode() + CIPHER_SEPARATOR + param.getWorkModel() + CIPHER_SEPARATOR + param.getPadding());
            initCipher(cipher, Cipher.DECRYPT_MODE, param);
            return new String(cipher.doFinal(mingwen), param.getCharset());
        } catch (Exception e) {
            throw new CryptoException(e.getMessage());
        }
    }

    private byte[] encryBts(CryptoParam param) {
        try {
            Cipher cipher = Cipher.getInstance(current().getCode() + CIPHER_SEPARATOR + param.getWorkModel() + CIPHER_SEPARATOR + param.getPadding());
            initCipher(cipher, Cipher.ENCRYPT_MODE, param);
            return cipher.doFinal(param.getData().getBytes(param.getCharset()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new CryptoException(e.getMessage());
        }
    }

    private byte[] decryBts(CryptoParam param) {
        try {
            byte[] mingwen = Hex.decodeHex(param.getData());
            Cipher cipher = Cipher.getInstance(current().getCode() + CIPHER_SEPARATOR + param.getWorkModel() + CIPHER_SEPARATOR + param.getPadding());
            initCipher(cipher, Cipher.DECRYPT_MODE, param);
            return cipher.doFinal(mingwen);
        } catch (Exception e) {
            throw new CryptoException(e.getMessage());
        }
    }

    private void initCipher(Cipher cipher, int model, CryptoParam param) throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (CryptoParam.WorkModel.ECB != param.getWorkModel() && StringUtils.isBlank(param.getIv())) {
            throw new CryptoException("when not ECB work model, iv param can not be null!");
        }

        if (CryptoParam.WorkModel.ECB == param.getWorkModel()) {
            cipher.init(model, toKey(param.getKey()));
        } else {
            IvParameterSpec ips = new IvParameterSpec(param.getIv().getBytes());
            cipher.init(model, toKey(param.getKey()), ips);
        }
    }

}
