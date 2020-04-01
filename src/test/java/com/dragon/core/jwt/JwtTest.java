package com.dragon.core.jwt;

import com.dragon.core.Stu;
import org.junit.Test;

/**
 * @ClassName: JwtTest
 * @Description: TODO
 * @Author: pengl
 * @Date: 2020/4/1 21:45
 * @Version V1.0
 */
public class JwtTest {

    @Test
    public void tt1(){
        String token = JwtToken.builder().data(Stu.builder().age(11).name("hh").uid("Z-01202323").build()).key("abc12123112313213456778843213431").build().create();
        System.out.println(token);
        Stu stu = (Stu)JwtToken.builder().key("abc12123112313213456778843213431").build().parse(token);
        System.out.println(stu);
    }
}
