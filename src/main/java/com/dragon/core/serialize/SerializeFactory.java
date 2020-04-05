package com.dragon.core.serialize;

import com.dragon.core.serialize.impl.FastJsonSerialize;
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

    public static final Serializable getSerializable(Serialize serialize){
        switch (serialize){
            case JDK:
                return new JdkSerialize();
            case KRYO:
                return new KryoSerialize();
            case FAST_JSON:
                return new FastJsonSerialize();
            default:
                return new JdkSerialize();
        }
    }
}
