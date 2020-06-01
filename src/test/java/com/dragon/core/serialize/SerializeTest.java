package com.dragon.core.serialize;

import com.dragon.core.Stu;
import com.dragon.core.Stu2;
import com.dragon.core.lang.StrUtils;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: SerializeTest
 * @Description: SerializeTest
 * @Author: pengl
 * @Date: 2020/6/1 14:03
 * @Version V1.0
 */
public class SerializeTest {
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
    @Fork(2)
    public void jdkSerializeTest(){
        Stu stu = new Stu("UID-23", "Jordan", 50);
        Stu stu1 = new Stu("UID-03", "Iverson", 42);
        stu.setStu2(stu1);

        ISerializable iSerializable = SerializeFactory.getSerializable(SerializeType.JDK);
        byte[] datas = iSerializable.serialize(stu);
        Stu outStu = iSerializable.deserialize(datas, Stu.class);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
    @Fork(2)
    public void hessianSerializeTest(){
        Stu stu = new Stu("UID-23", "Jordan", 50);
        Stu stu1 = new Stu("UID-03", "Iverson", 42);
        stu.setStu2(stu1);

        ISerializable iSerializable = SerializeFactory.getSerializable(SerializeType.HESSIAN);
        byte[] datas = iSerializable.serialize(stu);

        Stu outStu = iSerializable.deserialize(datas, Stu.class);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
    @Fork(2)
    public void kryoSerializeTest(){
        Stu stu = new Stu("UID-23", "Jordan", 50);
        Stu stu1 = new Stu("UID-03", "Iverson", 42);
        stu.setStu2(stu1);

        ISerializable iSerializable = SerializeFactory.getSerializable(SerializeType.KRYO);
        byte[] datas = iSerializable.serialize(stu);

        Stu outStu = iSerializable.deserialize(datas, Stu.class);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
    @Fork(2)
    public void fastJsonSerializeTest(){
        Stu stu = new Stu("UID-23", "Jordan", 50);
        Stu stu1 = new Stu("UID-03", "Iverson", 42);
        stu.setStu2(stu1);

        ISerializable iSerializable = SerializeFactory.getSerializable(SerializeType.FAST_JSON);
        byte[] datas = iSerializable.serialize(stu);

        Stu outStu = iSerializable.deserialize(datas, Stu.class);
    }


    @Test
    public void jmhTest() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SerializeTest.class.getSimpleName())
                //.output("D:\\jmh.log")
                .build();
        new Runner(opt).run();
    }


    @Test
    public void tt(){
        Stu2 stu = new Stu2("UID-23", "Jordan", 50);
        Stu2 stu1 = new Stu2("UID-03", "Iverson", 42);
        stu.setStu2(stu1);

        ISerializable iSerializable = SerializeFactory.getSerializable(SerializeType.HESSIAN);
        byte[] datas = iSerializable.serialize(stu);
        System.out.println(StrUtils.newStringUtf8(datas));
        System.out.println(datas.length);

        Stu outStu = iSerializable.deserialize(datas, Stu.class);
        System.out.println(outStu);
    }

}
