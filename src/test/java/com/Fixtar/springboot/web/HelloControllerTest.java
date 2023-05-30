package com.Fixtar.springboot.web;

import com.fixtar.springboot.config.auth.SecurityConfig;
import com.fixtar.springboot.web.HelloController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(controllers = HelloController.class,
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
    }
)
@AutoConfigureMockMvc
public class HelloControllerTest{

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(roles = "USER")
    public void returnHello() throws Exception{
        String hello = "hello";

        ResultActions result =  mvc.perform(MockMvcRequestBuilders.get("/hello"));

        result.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.content().string(hello)
        );
    }

    @Test
    @WithMockUser(roles = "USER")
    public void returnHelloDto() throws Exception {
        String name = "hello";
        int amount = 10;

        ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/hello/dto").param("name",name).param("amount", String.valueOf(amount)));

        result.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.jsonPath("$.name").value(name),
                MockMvcResultMatchers.jsonPath("$.amount").value(amount)
        );
    }

}
