package com.dragon.core.serialize;

import com.dragon.core.serialize.impl.*;

/**
 * @ClassName: SerializeFactory
 * @Description: SerializeFactory
 * @Author: pengl
 * @Date: 2020/4/1 21:33
 * @Version V1.0
 */
public final class SerializeFactory {
    private static final ISerializable KRYO_SERIALIZE = new KryoSerialize();
    private static final ISerializable JDK_SERIALIZE = new JdkSerialize();
    private static final ISerializable HESSIAN_SERIALIZE = new HessianSerialize();
    private static final ISerializable FASTJSON_SERIALIZE = new FastJsonSerialize();
    private static final ISerializable JACKSON_SERIALIZE = new JacksonSerialize();

    public static ISerializable getSerializable(SerializeType serializeType) {
        switch (serializeType) {
            case KRYO:
                return KRYO_SERIALIZE;
            case FAST_JSON:
                return FASTJSON_SERIALIZE;
            case JACKSON:
                return JACKSON_SERIALIZE;
            case HESSIAN:
                return HESSIAN_SERIALIZE;
            default:
                return JDK_SERIALIZE;
        }
    }
}
