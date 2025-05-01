package com.mycom.myapp.webapp.webmvctest;

import com.mycom.myapp.user.controller.TestController;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TestController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // #1
    @Test
    @Order(1)
    public void testHello() throws Exception {
        this.mockMvc.perform( get("/hello") )
                    .andExpect( status().isOk());
    }

    // #2
    @Test
    @Order(2)
    public void testParam1() throws Exception {
        this.mockMvc.perform(
                post("/param1")
                        .param("id", "111")
                        .param("name", "홍길동")
                )
                .andExpect( status().isOk());
    }
}
