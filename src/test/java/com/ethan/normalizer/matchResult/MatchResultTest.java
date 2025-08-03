package com.ethan.normalizer.matchResult;

import com.ethan.normalizer.MatchResult;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MatchResultTest {
    @Test
    void storesTitleAndScoreCorrectly() {
        MatchResult result = new MatchResult("Software engineer", 0.8);
        assertEquals("Software engineer", result.getTitle());
        assertEquals(0.8, result.getScore(), 0.0001);
    }
}