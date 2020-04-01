package com.dragon.core.compression;

/**
 * @ClassName: CompressionCodec
 * @Description: CompressionCodec
 * @Author: pengl
 * @Date: 2020/4/1 20:18
 * @Version V1.0
 */
public interface CompressionCodec {
    /**
     * @MethodName: compress
     * @Description: compress
     * @Author: pengl
     * @Date: 2020/4/1 20:18
     * @Version V1.0
     */
    byte[] compress(byte[] data) throws CompressionException;
    /**
     * @MethodName: decompress
     * @Description: decompress
     * @Author: pengl
     * @Date: 2020/4/1 20:18
     * @Version V1.0
     */
    byte[] decompress(byte[] compressed) throws CompressionException;
}
