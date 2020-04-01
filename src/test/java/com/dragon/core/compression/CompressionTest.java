package com.dragon.core.compression;

import org.junit.Test;

/**
 * @ClassName: CompressionTest
 * @Description: TODO
 * @Author: pengl
 * @Date: 2020/4/1 20:41
 * @Version V1.0
 */
public class CompressionTest {
    @Test
    public void tt1(){
        String s = "我是一個中國人！sdsdsdsdsd！！！@@#%…………&&*@#￥@#sdldfd聖克魯斯的賈克斯貸記卡時代峰峻卡薩帝";
        //CompressionCodec compressionCodec = CompressionFactory.getCompression(Compression.DEFAULT);
        CompressionCodec compressionCodec = CompressionFactory.getCompression(Compression.GZIP);
        byte[] data = s.getBytes();
        System.out.println(data.length);
        byte[] compressed = compressionCodec.compress(data);
        System.out.println(compressed.length);
        System.out.println(new String(compressed));

        byte[] decompress = compressionCodec.decompress(compressed);
        System.out.println(decompress.length);
        System.out.println(new String(decompress));

    }
}
