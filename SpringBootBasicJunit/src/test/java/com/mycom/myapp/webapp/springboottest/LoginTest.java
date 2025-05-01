package com.mycom.myapp.webapp.springboottest;


import com.mycom.myapp.auth.controller.LoginController;
import com.mycom.myapp.user.dto.UserResultDto;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {

//    @Autowired
//    private MockMvc mockMvc;

    // Controller
    @Autowired
    private LoginController loginController;

    @Autowired
    private HttpSession httpSession;

    @Test
    public void testLogin() {
        UserResultDto userResultDto =
                loginController.login("user1@mycom.com", "password1", httpSession);
        assertEquals("success", userResultDto.getResult());

    }
}
