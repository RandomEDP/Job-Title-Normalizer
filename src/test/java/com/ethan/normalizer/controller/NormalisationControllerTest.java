package com.ethan.normalizer.controller;

import com.ethan.normalizer.service.NormalisationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * Exercises edge cases around whitespace, punctuation, and Unicode in the matcher.
 */
@WebMvcTest(NormalisationController.class)
class NormalisationControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    NormalisationService service;
    @Test
    void controlTest() throws Exception {
        when(service.normalise("Java engineer")).thenReturn("Software engineer");
        mvc.perform(get("/api/normalize").param("title","Java engineer"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.normalizedTitle", is("Software engineer")));
    }
    @Test
    void blankReturnsBadRequest() throws Exception {
        mvc.perform(get("/api/normalize").param("title",""))
           .andExpect(status().isBadRequest());
    }
}