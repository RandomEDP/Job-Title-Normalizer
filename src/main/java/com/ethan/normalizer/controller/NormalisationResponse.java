package com.ethan.normalizer.controller;

public class NormalisationResponse {
    private String normalizedTitle;
    public NormalisationResponse() {}
    public NormalisationResponse(String normalizedTitle) { this.normalizedTitle = normalizedTitle; }
    public String getNormalizedTitle() { return normalizedTitle; }
    public void setNormalizedTitle(String normalizedTitle) { this.normalizedTitle = normalizedTitle; }
}