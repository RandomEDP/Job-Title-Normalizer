package com.ethan.normalizer.service;

import com.ethan.normalizer.MatchResult;
import com.ethan.normalizer.config.NormalizerProperties;
import org.springframework.stereotype.Service;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.Objects;

/**
 * Service that normalizes job titles.
 */
@Service
public class JobTitleNormaliserService implements NormalisationService {
    private static final Logger log = LoggerFactory.getLogger(JobTitleNormaliserService.class);

    private final List<String> titles;
    private final SimpleTokenMatcher matcher;
    private final MeterRegistry registry;

    public JobTitleNormaliserService(NormalizerProperties props,
                                     SimpleTokenMatcher matcher,
                                     MeterRegistry registry) {
        this.titles = Objects.requireNonNull(props.getTitles(), "Titles must not be null");
        this.matcher = matcher;
        this.registry = registry;
    }

    @PostConstruct
    public void init() {
        log.info("Loaded {} normalized titles", titles.size());
    }

    @Timed("jobtitle.normalise")
    public String normalise(String rawTitle) {
        log.info("Normalizing title=\"{}\"", rawTitle);
        registry.counter("jobtitle.requested").increment();

        // 1) trim & lowercase
        // 2) remove punctuation
        // 3) collapse all whitespace to single spaces
        String cleaned = rawTitle.trim().toLowerCase()
                .replaceAll("[^\\p{L}\\p{N} ]+", " ")
                .replaceAll("\\s+", " ");
        if (cleaned.isEmpty()) {
            throw new IllegalArgumentException("Input title must not be blank");
        }

        MatchResult result = matcher.findBestMatch(cleaned, titles);
        String normalized = (result != null) ? result.getTitle() : "Unknown";

        registry.counter("jobtitle.matched", "matched", String.valueOf(!"Unknown".equals(normalized))).increment();
        log.info("Result=\"{}\"", normalized);

        return normalized;
    }
}
