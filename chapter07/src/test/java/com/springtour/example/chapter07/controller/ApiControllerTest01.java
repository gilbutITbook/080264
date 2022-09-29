package com.springtour.example.chapter07.controller;

import com.springtour.example.chapter07.util.JsonUtil;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ApiControllerTest01 {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetHotelById() throws Exception {

        HotelRequest hotelRequest = new HotelRequest("Ragged Point Inn");
        String jsonRequest = JsonUtil.objectMapper.writeValueAsString(hotelRequest);

        mockMvc.perform(post("/hotels/fetch-by-name")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("[{\"hotelId\":1000,\"hotelName\":\"Ragged Point Inn\",\"address\":\"18091 CA-1, San Simeon, CA 93452\",\"phoneNumber\":\"+18885846374\"}]"))
                .andExpect(content().json("[{\"hotelId\":1000,\"hotelName\":\"Ragged Point Inn\",\"address\":\"18091 CA-1, San Simeon, CA 93452\",\"phoneNumber\":\"+18885846374\"}]"))
                .andExpect(jsonPath("$[0].hotelId", Matchers.is(1000)))
                .andExpect(jsonPath("$[0].hotelName", Matchers.is("Ragged Point Inn")))
                .andDo(MockMvcResultHandlers.print(System.out));
    }

}
