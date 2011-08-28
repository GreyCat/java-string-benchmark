package ru.greycat.algorithms.strip;

/**
 * An algorithm implementation that performs a single operation:
 * stripping all the control characters (i.e. characters with code
 * less than 32) from a given string.
 */
public interface StripAlgorithm {
    public String strip(String s) throws Exception;
}
