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
    
    // @beforeAll, @afterAll 는 static
    // 테스트 전 static 으로 호출 되고, TestBasic 객체 생성 후 호출되는 UI 에는 보이지 않는다
    // 전체 테스트 전 리소스 확인, 테스트 데이터 생성...
    @BeforeAll
    @DisplayName("전체 테스트 메소드 수행전 한번 실행")
    static void beforeAll() {
        System.out.println("before()");
    }

    // 전체 테스트 전 리소스 종료, 테스트 데이터 정리...
    @AfterAll
    @DisplayName("전체 테스트 메소드 수행 후 실행")
    static void afterAll() {
        System.out.println("after()");
    }

    @BeforeEach
    @DisplayName("개별 테스트 메소드 수행전 매번 실행")
    void beforeEach() {
        System.out.println("beforeEach()");
    }

    // 전체 테스트 전 리소스 종료, 테스트 데이터 정리...
    @AfterEach
    @DisplayName("전체 테스트 메소드 수행 후 실행")
    void afterEach() {
        System.out.println("afterEach()");
    }
}
