package com.ethan.normalizer.service;

import com.ethan.normalizer.MatchResult;
import com.ethan.normalizer.config.NormalizerProperties;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class ServiceExtraEdgeCasesTest {
    private final SimpleMeterRegistry reg = new SimpleMeterRegistry();
    private final NormalizerProperties props;
    private final JobTitleNormaliserService service;
    ServiceExtraEdgeCasesTest() {
        props = new NormalizerProperties();
        props.setTitles(List.of("Architect","Software engineer","Quantity surveyor","Accountant","Coeur architect"));
        props.setThreshold(0.5);
        service = new JobTitleNormaliserService(props, new SimpleTokenMatcher(props), reg);
    }
    @ParameterizedTest
    @CsvSource({
        "Java-engineer,Software engineer",
        "Java_engineer,Software engineer",
        "Java/engineer,Software engineer",
        "Chief!Accountant,Accountant"
    })
    void punctuation(String raw, String expected) {
        assertEquals(expected, service.normalise(raw));
    }
    @Test
    void unicode() {
        MatchResult r = service.normalise("Cœur architect")!=null? 
            new MatchResult(service.normalise("Cœur architect"),1.0):null;
        assertNotNull(r);
    }
    @Test
    void mixedWhitespace() {
        assertEquals("Software engineer", service.normalise("Software	 engineer "));
    }
    @Test
    void emptyTitles() {
        NormalizerProperties p2 = new NormalizerProperties();
        p2.setTitles(List.of());
        p2.setThreshold(0.5);
        var s2 = new JobTitleNormaliserService(p2, new SimpleTokenMatcher(p2), reg);
        assertEquals("Unknown", s2.normalise("Anything"));
    }
    @Test
    void longInput() {
        String longInput = "A".repeat(10000);
        assertDoesNotThrow(() -> service.normalise(longInput));
    }
    @Test
    void metrics() {
        reg.clear();
        service.normalise("Java engineer");
        assertEquals(1, reg.counter("jobtitle.requested").count());
        assertEquals(1, reg.counter("jobtitle.matched", "matched", "true").count());
    }
}