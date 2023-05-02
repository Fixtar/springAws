package com.Fixtar.springboot.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest{

    @Autowired
    private MockMvc mvc;

    @Test
    public void returnHello() throws Exception{
        String hello = "hello";

        ResultActions result =  mvc.perform(MockMvcRequestBuilders.get("/hello"));

        result.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.content().string(hello)
        );
    }

    @Test
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
