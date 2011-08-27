package ru.greycat.algorithms.strip;

public class StringReplaceAll implements StripAlgorithm {
    @Override
    public String strip(String s) {
        return s.replaceAll("[\\x00-\\x1F]", "");
    }
}
