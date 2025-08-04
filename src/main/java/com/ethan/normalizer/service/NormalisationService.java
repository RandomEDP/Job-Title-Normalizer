package com.ethan.normalizer.service;
/**
 * Normalises arbitrary raw strings to one of the configured titles.
 * Implementations should apply any cleanup, matching logic, and scoring.
 */
public interface NormalisationService {
    String normalise(String rawTitle);
}