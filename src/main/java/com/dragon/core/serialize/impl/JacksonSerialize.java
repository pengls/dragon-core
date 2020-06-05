package com.dragon.core.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.dragon.core.lang.JsonUtil;
import com.dragon.core.serialize.ISerializable;
import com.dragon.core.serialize.SerializeType;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName: JacksonSerialize
 * @Description: base on jackson
 * @Author: pengl
 * @Date: 2020/4/5 13:04
 * @Version V1.0
 */
public class JacksonSerialize implements ISerializable {
    @Override
    public <T> byte[] serialize(T obj) {
        return JsonUtil.obj2String(obj).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public <T> T deserialize(byte[] data, Class pvClass) {
        if (data == null || data.length == 0) {
            return null;
        }
        return (T) JsonUtil.string2Obj(new String(data, StandardCharsets.UTF_8), pvClass);
    }

    @Override
    public SerializeType serializeType() {
        return SerializeType.JACKSON;
    }
}
