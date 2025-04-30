package com.mycom.myapp.basic;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_Basic {

    @Test
    @Order(2)
    void test1() {
        System.out.println("test1()");
    }

    @Test
    @Order(3)
    @DisplayName("test2 메소드를 테스트 합니다.")
    void test2() {
        System.out.println("test2()");
    }

    @Test
    @Order(1)
    void test3() {
        System.out.println("test3()");
    }
}
