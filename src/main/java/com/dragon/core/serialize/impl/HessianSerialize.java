package com.dragon.core.serialize.impl;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.SerializerFactory;
import com.dragon.core.serialize.ISerializable;
import com.dragon.core.serialize.SerializeException;
import com.dragon.core.serialize.SerializeType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @ClassName: KryoSerialize
 * @Description: Hessian
 * @Author: pengl
 * @Date: 2020/4/4 20:06
 * @Version V1.0
 */
public class HessianSerialize implements ISerializable {
    private SerializerFactory serializerFactory = new SerializerFactory();

    @Override
    public <T> byte[] serialize(T obj) {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        Hessian2Output output = new Hessian2Output(byteArray);
        output.setSerializerFactory(serializerFactory);
        try {
            output.writeObject(obj);
            output.close();
        } catch (IOException e) {
            throw new SerializeException(e.getMessage(), e);
        }

        return byteArray.toByteArray();
    }

    @Override
    public <T> T deserialize(byte[] data, Class pvClass) {
        if (data == null || data.length == 0) {
            return null;
        }
        Hessian2Input input = new Hessian2Input(new ByteArrayInputStream(data));
        input.setSerializerFactory(serializerFactory);
        Object resultObject;
        try {
            resultObject = input.readObject();
            input.close();
        } catch (IOException e) {
            throw new SerializeException(e.getMessage(), e);
        }
        return (T) resultObject;
    }

    @Override
    public SerializeType serializeType() {
        return SerializeType.HESSIAN;
    }
}
