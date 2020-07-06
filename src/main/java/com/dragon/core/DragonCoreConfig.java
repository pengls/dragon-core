package com.dragon.core;

import cn.hutool.setting.dialect.Props;

/**
 * @ClassName: DragonCoreConfig
 * @Description: DragonCoreConfig
 * @Author: pengl
 * @Date: 2020/7/6 22:23
 * @Version V1.0
 */
public final class DragonCoreConfig {
    private static final String DRAGON_CORE_CONFIG_NAME = "dragon-core.properties";
    private static final Props props = Props.getProp(DRAGON_CORE_CONFIG_NAME);

    public static Props getProps() {
        return props;
    }
}
