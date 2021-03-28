package com.daichen.study.jvm;

/**
 * @author daichen
 * @version v1.0
 * @create 2021/3/20 11:16 上午
 * @description
 */
public class Hello {

    static {
        System.out.println("Hello class static code");
    }

    public void hello() {
        System.out.println("Hello, classLoader!");
    }
}
