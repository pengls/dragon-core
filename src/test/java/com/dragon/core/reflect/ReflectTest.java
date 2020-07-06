package com.dragon.core.reflect;

import com.google.common.eventbus.Subscribe;
import org.reflections.Reflections;
import org.reflections.scanners.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

/**
 * @ClassName: ReflectTest
 * @Description: TODO
 * @Author: pengl
 * @Date: 2020/7/3 15:05
 * @Version V1.0
 */
public class ReflectTest {
    public static void main(String[] args) {
        Reflections reflections = new Reflections("com.dragon.core",Arrays.asList(new MethodAnnotationsScanner()));
        Set<Method> methods = reflections.getMethodsAnnotatedWith(Subscribe.class);
        methods.forEach(m -> {
            System.out.println(m.getName() + "--" + m.getDeclaringClass().getName());
        });
    }
}
