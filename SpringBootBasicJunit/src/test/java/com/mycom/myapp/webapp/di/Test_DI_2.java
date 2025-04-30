package com.mycom.myapp.webapp.di;

import com.mycom.myapp.auth.service.LoginService;
import com.mycom.myapp.user.controller.UserController;
import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.repository.UserRepository;
import com.mycom.myapp.user.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// Spring Context 전체를 이용한 Test
//  @SpringBootTest
//  무겁다
//  전체 cover
// Spring Web 을 이용한 Test
//  @WebMvcTest (JPA Context 등 사용X)
//  가볍다
//  Web cover

// DI 테스트
// @WebMvcTest() 은 특정 Controller based Test 를 진행함
// @WebMvcTest() 에 Controller based Test 를 지정하지 않으면 Service 등 다른 Di 포함되어야 한다.
// 아래 LoginService 가 예시

@WebMvcTest(UserController.class)
//@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class Test_DI_2 {

    private static final Logger log = LoggerFactory.getLogger(Test_DI_2.class);

//    @Autowired
//    MockMvc mockMvc;


    //since spring boot 3.4.x @MockBean -> @MockitoBean
//    @Autowired
    @MockitoBean
    UserController userController;

    @MockitoBean
    UserService userService;

    @MockitoBean
    UserRepository userRepository;

//    @MockitoBean
//    LoginService loginService;


    @Test
    @Order(0)
    void TestDi() {
        log.debug("testDI() 시작");
        assertNotNull(userController);
        assertNotNull(userService);
        assertNotNull(userRepository);
        log.debug("testDI() 종료");
    }
//
//    @Test
//    @Order(1)
//    void TestDi_All() {
//        log.debug("testDI() 시작");
//
//        assertAll(
//                "DI 묶음 테스트",
//                () -> assertNotNull(userController),
//                () -> assertNotNull(userService),
//                () -> assertNotNull(userRepository)
//        );
//
//        log.debug("testDI() 종료");
//    }

    @MockitoBean
    HttpSession session;

    @MockitoBean
    HttpServletRequest request;

    @Test
    @Order(3)
    void TestDi_SessionRequest() {
        log.debug("TestDi_SessionRequest() 시작");
        assertNotNull(session);
        assertNotNull(request);
        log.debug("TestDi_SessionRequest() 종료");
    }

//    // jpa 영역 DI 실패
//    @MockitoBean
//    EntityManager entityManager;
//
//    @Test
//    @Order(4)
//    void TestDi_EntityManager() {
//        log.debug("TestDi_EntityManager() 시작");
//        assertNotNull(entityManager);
//        User user = entityManager.find(User.class, 1);
//        assertNotNull(user);
//        log.debug("TestDi_EntityManager() 종료");
//    }
}
