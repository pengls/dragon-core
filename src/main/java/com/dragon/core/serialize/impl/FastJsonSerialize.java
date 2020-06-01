package com.dragon.core.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.dragon.core.serialize.ISerializable;
import com.dragon.core.serialize.SerializeType;

/**
 * @ClassName: FastJsonSerialize
 * @Description: alibaba fastjson
 * @Author: pengl
 * @Date: 2020/4/5 13:04
 * @Version V1.0
 */
public class FastJsonSerialize implements ISerializable {
    @Override
    public <T> byte[] serialize(T obj) {
        return JSON.toJSONBytes(obj);
    }

    @Override
    public <T> T deserialize(byte[] data, Class pvClass) {
        if (data == null || data.length == 0) {
            return null;
        }
        return (T) JSON.parseObject(data, pvClass);
    }

    @Override
    public SerializeType serializeType() {
        return SerializeType.FAST_JSON;
    }
}
