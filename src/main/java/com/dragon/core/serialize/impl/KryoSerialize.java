package com.dragon.core.serialize.impl;

import com.dragon.core.serialize.ISerializable;
import com.dragon.core.serialize.SerializeException;
import com.dragon.core.serialize.SerializeType;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.Pool;

/**
 * @ClassName: KryoSerialize
 * @Description: Kryo
 * @Author: pengl
 * @Date: 2020/4/4 20:06
 * @Version V1.0
 */
public class KryoSerialize implements ISerializable {
    private static Pool<Kryo> mKryoPool = new Pool<Kryo>(true, false, 8) {
        @Override
        protected Kryo create() {
            Kryo kryo = new Kryo();
            kryo.setRegistrationRequired(false);
            kryo.setReferences(false);
            // Configure the Kryo instance.
            return kryo;
        }
    };
    private static Pool<Output> mOutputPool = new Pool<Output>(true, false, 16) {
        @Override
        protected Output create() {
            return new Output(1024, -1);
        }
    };
    private static Pool<Input> mInputPool = new Pool<Input>(true, false, 16) {
        @Override
        protected Input create() {
            return new Input(1024);
        }
    };

    @Override
    public <T> byte[] serialize(T obj) {
        Kryo kryo = mKryoPool.obtain();
        Output output = mOutputPool.obtain();
        try {
            output.reset();
            kryo.writeObject(output, obj);
            return output.getBuffer();
        } finally {
            mKryoPool.free(kryo);
            mOutputPool.free(output);
        }
    }

    @Override
    public <T> T deserialize(byte[] data, Class pvClass) {
        if (data == null || data.length == 0) {
            return null;
        }
        Kryo kryo = mKryoPool.obtain();
        Input input = mInputPool.obtain();
        try {
            input.setBuffer(data);
            return (T) kryo.readObject(input, pvClass);
        } catch (Exception e) {
            throw new SerializeException(e.getMessage(), e);
        } finally {
            mKryoPool.free(kryo);
            mInputPool.free(input);
        }
    }

    @Override
    public SerializeType serializeType() {
        return SerializeType.KRYO;
    }
}
