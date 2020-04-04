package com.dragon.core.compression.impl;

import com.dragon.core.compression.Compression;
import com.dragon.core.compression.CompressionCodec;
import com.dragon.core.compression.CompressionException;
import com.dragon.core.lang.Objects;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: CompressionProxy
 * @Description: 代理类：统计压缩率
 * @Author: pengl
 * @Date: 2020/4/4 20:40
 * @Version V1.0
 */
@Slf4j
public class CompressionProxy implements CompressionCodec {

    private CompressionCodec compressionCodec;

    public CompressionProxy(CompressionCodec compressionCodec) {
        this.compressionCodec = compressionCodec;
    }

    @Override
    public byte[] compress(byte[] data) throws CompressionException {
        long c = data.length;
        long b = System.currentTimeMillis();
        byte[] res = compressionCodec.compress(data);
        long e = System.currentTimeMillis();
        log.info("Compression Type : {}, before length: {}, after length: {}, ratio: {}%, times: {}ms",
                compressionCodec.current(), c, res.length, Objects.percentage(res.length, c, 2), (e - b));
        return res;
    }

    @Override
    public byte[] decompress(byte[] compressed) throws CompressionException {
        long b = System.currentTimeMillis();
        byte[] res = compressionCodec.decompress(compressed);
        long e = System.currentTimeMillis();
        log.info("Compression Type : {}, decompress times: {}ms", compressionCodec.current(), (e - b));
        return res;
    }

    @Override
    public Compression current() {
        return null;
    }
}
