package com.springtour.example.chapter07.controller;

import com.springtour.example.chapter07.service.HotelDisplayService;
import com.springtour.example.chapter07.util.JsonUtil;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = HotelController.class)
public class ApiControllerTest02 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelDisplayService hotelDisplayService;

    @BeforeEach
    public void init() {
        given(hotelDisplayService.getHotelsByName(any()))
                .willAnswer(new Answer<List<HotelResponse>>() {
                    @Override
                    public List<HotelResponse> answer(InvocationOnMock invocation) throws Throwable {
                        HotelRequest hotelRequest = invocation.getArgument(0);
                        return List.of(new HotelResponse(1L, hotelRequest.getHotelName(), "unknown", "213-820-3xxx"));
                    }
                });
    }

    @Test
    public void testGetHotelById() throws Exception {
        HotelRequest hotelRequest = new HotelRequest("Line Hotel");
        String jsonRequest = JsonUtil.objectMapper.writeValueAsString(hotelRequest);

        mockMvc.perform(post("/hotels/fetch-by-name")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].hotelId", Matchers.is(1)))
                .andExpect(jsonPath("$[0].hotelName", Matchers.is("Line Hotel")))
                .andDo(MockMvcResultHandlers.print(System.out));
    }
}
