package ru.greycat.algorithms.strip;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherReplace implements StripAlgorithm {
    public static final Pattern STRIP_PATTERN = Pattern.compile("[\\x00-\\x1F]");

    @Override
    public String strip(String s) {
        Matcher m = STRIP_PATTERN.matcher(s);
        return m.replaceAll("");
    }
}
