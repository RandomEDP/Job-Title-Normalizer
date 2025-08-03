package com.ethan.normalizer.service;

import com.ethan.normalizer.MatchResult;
import com.ethan.normalizer.config.NormalizerProperties;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class SimpleTokenMatcherTest {
    private final SimpleTokenMatcher matcher;

    SimpleTokenMatcherTest() {
        NormalizerProperties props = new NormalizerProperties();
        props.setThreshold(0.5);
        matcher = new SimpleTokenMatcher(props);
    }

    @ParameterizedTest
    @CsvSource({
        "Java engineer,Software engineer",
        "Accountant,Accountant",
        "Chief Accountant,Accountant"
    })
    void testValid(String input, String expected) {
        List<String> titles = List.of("Architect", "Software engineer", "Quantity surveyor", "Accountant");
        MatchResult res = matcher.findBestMatch(input.toLowerCase(), titles);
        assertNotNull(res);
        assertEquals(expected, res.getTitle());
    }

    @Test
    void testUnknown() {
        List<String> titles = List.of("Architect", "Quantity surveyor");
        assertNull(matcher.findBestMatch("Banana manager", titles));
    }
}