package com.ethan.normalizer;

public class MatchResult {
    private final String title;
    private final double score;

    public MatchResult(String title, double score) {
        this.title = title;
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public double getScore() {
        return score;
    }
}