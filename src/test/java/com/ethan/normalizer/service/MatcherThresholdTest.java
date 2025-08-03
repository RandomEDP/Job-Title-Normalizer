package com.ethan.normalizer.service;

import com.ethan.normalizer.MatchResult;
import com.ethan.normalizer.TitleMatcher;
import com.ethan.normalizer.config.NormalizerProperties;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class MatcherThresholdTest {
    @Test
    void atThreshold() {
        NormalizerProperties p = new NormalizerProperties();
        p.setTitles(List.of("A B", "A C"));
        p.setThreshold(0.5);
        SimpleTokenMatcher m = new SimpleTokenMatcher(p);
        // At threshold, inclusive match
        MatchResult res = m.findBestMatch("a d", p.getTitles());
        assertNotNull(res);
        assertEquals("A B", res.getTitle());
    }

    @Test
    void aboveThreshold() {
        var stub = (TitleMatcher) (inp, t) -> new MatchResult("A B", 0.5001);
        MatchResult res = stub.findBestMatch("", List.of("A B"));
        assertEquals("A B", res.getTitle());
    }
}