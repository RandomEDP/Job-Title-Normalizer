package com.ethan.normalizer.controller;

import com.ethan.normalizer.service.NormalisationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NormalisationController.class)
class ControllerExtraTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    NormalisationService service;
    @Test
    void missingParam() throws Exception {
        mvc.perform(get("/api/normalize"))
           .andExpect(status().isBadRequest())
           .andExpect(content().string(containsString("title parameter is missing")));
    }
    @Test
    void runtimeException500() throws Exception {
        when(service.normalise(anyString())).thenThrow(new RuntimeException("boom"));
        mvc.perform(get("/api/normalize").param("title","X"))
           .andExpect(status().isInternalServerError())
           .andExpect(content().string("Unexpected server error"));
    }
}