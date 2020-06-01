package com.dragon.core.serialize;

/**
 * @ClassName: Serializable
 * @Description: Serializable intface
 * @Author: pengl
 * @Date: 2020/4/1 21:13
 * @Version V1.0
 */
public interface ISerializable {

    /**
     * @MethodName: serialize
     * @Description: serialize
     * @Author: pengl
     * @Date: 2020/4/1 21:14
     * @Version V1.0
     */
    <T> byte[] serialize(T obj);

    /**
     * @MethodName: deserialize
     * @Description: deserialize
     * @Author: pengl
     * @Date: 2020/4/1 21:15
     * @Version V1.0
     */
    <T> T deserialize(byte[] data, Class pvClass);

    /**
     * @MethodName: serializeType
     * @Description: the impl type
     * @Author: pengl
     * @Date: 2020/6/1 13:48
     * @Version V1.0
     */
    SerializeType serializeType();

}
