package com.ethan.normalizer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "normalizer")
public class NormalizerProperties {
    private List<String> titles;
    private double threshold;
    public List<String> getTitles() { return titles; }
    public void setTitles(List<String> titles) { this.titles = titles; }
    public double getThreshold() { return threshold; }
    public void setThreshold(double threshold) { this.threshold = threshold; }
}