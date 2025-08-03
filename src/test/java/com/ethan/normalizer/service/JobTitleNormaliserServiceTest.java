package com.ethan.normalizer.service;

import com.ethan.normalizer.config.NormalizerProperties;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class JobTitleNormaliserServiceTest {
    private JobTitleNormaliserService service;
    @BeforeEach
    void setUp() {
        NormalizerProperties props = new NormalizerProperties();
        props.setTitles(List.of("Architect", "Software engineer", "Quantity surveyor", "Accountant"));
        props.setThreshold(0.5);
        service = new JobTitleNormaliserService(props, new SimpleTokenMatcher(props), new SimpleMeterRegistry());
    }
    @ParameterizedTest
    @CsvSource({
        "Java engineer,Software engineer",
        "C# engineer,Software engineer",
        "Accountant,Accountant",
        "Chief Accountant,Accountant",
        "ARCHITECT,Architect",
        "  Quantity surveyor ,Quantity surveyor"
    })
    void testValidMatches(String input, String expected) {
        assertEquals(expected, service.normalise(input));
    }
    @Test
    void testUnknown() {
        assertEquals("Unknown", service.normalise("Dinosaur trainer"));
    }
    @Test
    void testBlankThrows() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> service.normalise(" "));
        assertEquals("Input title must not be blank", ex.getMessage());
    }
}