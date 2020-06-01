package com.dragon.core.serialize;

import com.dragon.core.serialize.impl.FastJsonSerialize;
import com.dragon.core.serialize.impl.HessianSerialize;
import com.dragon.core.serialize.impl.JdkSerialize;
import com.dragon.core.serialize.impl.KryoSerialize;

/**
 * @ClassName: SerializeFactory
 * @Description: SerializeFactory
 * @Author: pengl
 * @Date: 2020/4/1 21:33
 * @Version V1.0
 */
public abstract class SerializeFactory {
    private static ISerializable kryoSerialize = new KryoSerialize();
    private static ISerializable jdkSerialize = new JdkSerialize();
    private static ISerializable hessianSerialize = new HessianSerialize();
    private static ISerializable fastJsonSerialize = new FastJsonSerialize();

    public static final ISerializable getSerializable(SerializeType serializeType) {
        switch (serializeType) {
            case KRYO:
                return kryoSerialize;
            case FAST_JSON:
                return fastJsonSerialize;
            case HESSIAN:
                return hessianSerialize;
            default:
                return jdkSerialize;
        }
    }
}
