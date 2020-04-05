package com.dragon.core.serial;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.dragon.core.Stu;
import com.dragon.core.jwt.JwtToken;
import org.junit.Test;

import java.util.Date;

/**
 * @ClassName: SerialTest
 * @Description: TODO
 * @Author: pengl
 * @Date: 2020/4/5 17:47
 * @Version V1.0
 */
public class SerialTest {
    @Test
    public void tt(){
        Stu stu = new Stu("S-01020012012", "Zhang San", 23);
        JwtToken jwtToken = JwtToken.builder().key("123456").expire(3000).createDate(new Date()).data(stu).build();

        String json = JSON.toJSONString(jwtToken);
        System.out.println(json);

        JwtToken<Stu> toKen = JSON.parseObject(json, new TypeReference<JwtToken<Stu>>(){});
        System.out.println(toKen.getData().getClass().getSimpleName());
    }
}
