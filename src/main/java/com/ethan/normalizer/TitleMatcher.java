package com.ethan.normalizer;

import java.util.List;

public interface TitleMatcher {
    MatchResult findBestMatch(String input, List<String> titles);
}