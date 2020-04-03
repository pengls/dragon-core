package com.dragon.core.jwt;

import com.alibaba.fastjson.JSON;
import com.dragon.core.Stu;
import com.dragon.core.compression.Compression;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @ClassName: JwtTest
 * @Description: TODO
 * @Author: pengl
 * @Date: 2020/4/1 21:45
 * @Version V1.0
 */
public class JwtTest {
    long bg;
    long end;

    @Before
    public void befor() {
        bg = System.currentTimeMillis();
    }

    @After
    public void after() {
        end = System.currentTimeMillis();
        System.out.println("----------all times:>>>" + (end - bg) + " ms");
    }

    @Test
    public void tt1() throws InterruptedException {
        Stu stu = new Stu("Z-01202323","hh",11);
        String token = JwtToken.builder()
                .data(stu)
                .compression(Compression.GZIP)
                .algorithm(JwtAlgorithm.PBEWithSHA1AndDESede)
                .key("abc12123112313213456778843213431")
                .expire(3000)
                .build().create();
        System.out.println(token);
        //System.out.println(JwtToken.builder().compression(Compression.GZIP).algorithm(JwtAlgorithm.PBEWithSHA1AndDESede).key("abc12123112313213456778843213431").build().verify(token));
        Stu stut = (Stu)JwtToken.builder().compression(Compression.GZIP).algorithm(JwtAlgorithm.PBEWithSHA1AndDESede).key("abc12123112313213456778843213431").build().parse(token, true);
        System.out.println(JSON.toJSONString(stut));

        Thread.sleep(4000);
        stut = (Stu)JwtToken.builder().compression(Compression.GZIP).algorithm(JwtAlgorithm.PBEWithSHA1AndDESede).key("abc12123112313213456778843213431").build().parse(token, true);

    }
}
