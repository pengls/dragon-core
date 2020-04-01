package com.dragon.core;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: Stu
 * @Description: TODO
 * @Author: pengl
 * @Date: 2020/4/1 21:46
 * @Version V1.0
 */
@Data
@Builder
public class Stu implements Serializable {

    private String uid;
    private String name;
    private int age;

    private Stu stu2;
}
