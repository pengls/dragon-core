package com.dragon.core.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.dragon.core.serialize.Serializable;

/**
 * @ClassName: FastJsonSerialize
 * @Description: alibaba fastjson
 * @Author: pengl
 * @Date: 2020/4/5 13:04
 * @Version V1.0
 */
public class FastJsonSerialize implements Serializable {
    @Override
    public byte[] serialize(Object obj) {
        return JSON.toJSONBytes(obj);
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> pvClass) {
        return JSON.parseObject(data, pvClass);
    }
}
