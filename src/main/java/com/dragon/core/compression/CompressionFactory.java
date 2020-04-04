package com.dragon.core.compression;

import com.dragon.core.compression.impl.DeflateCompressionCodec;
import com.dragon.core.compression.impl.GzipCompressionCodec;

/**
 * @ClassName: CompressionFactory
 * @Description: get the compression type
 * @Author: pengl
 * @Date: 2020/4/1 20:38
 * @Version V1.0
 */
public abstract class CompressionFactory {
    /**
     * @MethodName: getCompression
     * @Description: getCompression
     * @Author: pengl
     * @Date: 2020/4/1 20:40
     * @Version V1.0
     */
    public static final CompressionCodec getCompression(Compression compression){
        switch (compression){
            case GZIP:
                return new GzipCompressionCodec();
            case DEFLATE:
                return new DeflateCompressionCodec();
            default:
                return new DeflateCompressionCodec();
        }
    }
}
