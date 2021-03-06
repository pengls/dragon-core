package com.dragon.core.serialize.impl;

import com.dragon.core.serialize.ISerializable;
import com.dragon.core.serialize.SerializeException;
import com.dragon.core.serialize.SerializeType;
import java.io.*;

/**
 * @ClassName: JdkSerialize
 * @Description: JdkSerialize use object stream
 * @Author: pengl
 * @Date: 2020/4/1 21:15
 * @Version V1.0
 */
public class JdkSerialize implements ISerializable {

    @Override
    public <T> byte[] serialize(T obj) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(obj);
            oos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new SerializeException(e.getMessage(), e);
        }
    }

    @Override
    public <T> T deserialize(byte[] data, Class pvClass) {
        if (data == null || data.length == 0) {
            return null;
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        try (ObjectInputStream oos = new ObjectInputStream(bais)) {
            return (T) oos.readObject();
        } catch (IOException e) {
            throw new SerializeException(e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            throw new SerializeException(e.getMessage(), e);
        }
    }

    @Override
    public SerializeType serializeType() {
        return SerializeType.JDK;
    }
}
