package com.dragon.core.jmx;

import java.lang.management.PlatformManagedObject;

public interface JmxMBean extends PlatformManagedObject {
    void registerMBean();
}
