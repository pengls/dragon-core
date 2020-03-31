package com.dragon.core.crypto;

import com.dragon.core.lang.Assert;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName: CryptoFactory
 * @Description: 加密工具类
 * @Author: pengl
 * @Date: 2020/3/26 20:03
 * @Version V1.0
 */
@Slf4j
public final class CryptoFactory {
    private static final String PACKAGE_NAME = "com.dragon.core.crypto";

    private CryptoFactory() {
    }

    private static Map<Algorithm, Crypto> cryptoMap = null;

    static {
        Reflections reflections = new Reflections(PACKAGE_NAME);
        Set<Class<? extends Crypto>> subClasses = reflections.getSubTypesOf(Crypto.class);
        if (null != subClasses && subClasses.size() > 0) {
            //filter the abstract class
            subClasses = subClasses.stream().filter(c -> !Modifier.isAbstract(c.getModifiers())).collect(Collectors.toSet());
            cryptoMap = Maps.newHashMapWithExpectedSize(subClasses.size());
            for (Class<? extends Crypto> cryptoClass : subClasses) {
                try {
                    Crypto crypto = cryptoClass.newInstance();
                    cryptoMap.put(crypto.current(), crypto);
                } catch (Exception e) {
                    log.error("Crypto instanced error: {}", e);
                }
            }
        }
    }

    /**
     * @MethodName: getCrypto
     * @Description: getCrypto by algorithm
     * @Author: pengl
     * @Date: 2020/3/28 10:30
     * @Version V1.0
     */
    public static Crypto getCrypto(Algorithm algorithm) {
        if (null == cryptoMap) {
            throw new CryptoException("Crypto Impl Not Found !");
        }
        Crypto inst = cryptoMap.get(algorithm);
        Assert.notNull(inst, "Algorithm Impl Not Found !");
        return inst;
    }

}
