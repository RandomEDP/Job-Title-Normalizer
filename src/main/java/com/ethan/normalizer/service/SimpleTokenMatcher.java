package com.ethan.normalizer.service;

import com.ethan.normalizer.MatchResult;
import com.ethan.normalizer.TitleMatcher;
import com.ethan.normalizer.config.NormalizerProperties;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class SimpleTokenMatcher implements TitleMatcher {
    private final double threshold;
    public SimpleTokenMatcher(NormalizerProperties props) {
        this.threshold = props.getThreshold();
    }
    @Override
    public MatchResult findBestMatch(String input, List<String> titles) {
        double bestScore = 0.0;
        String bestTitle = null;
        for (String title : titles) {
            double score = computeSimilarity(input, title.toLowerCase());
            if (score > bestScore) {
                bestScore = score;
                bestTitle = title;
            }
        }
        return (bestTitle != null && bestScore >= threshold) ? new MatchResult(bestTitle, bestScore) : null;
    }
    private double computeSimilarity(String s1, String s2) {
        Set<String> tokens1 = new HashSet<>(Arrays.asList(s1.split("\s+")));
        Set<String> tokens2 = new HashSet<>(Arrays.asList(s2.split("\s+")));
        Set<String> intersection = new HashSet<>(tokens1);
        intersection.retainAll(tokens2);
        return tokens2.isEmpty() ? 0.0 : (double) intersection.size() / tokens2.size();
    }
}