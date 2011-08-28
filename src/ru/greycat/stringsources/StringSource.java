package ru.greycat.stringsources;

/**
 * String source is a provider of strings for testing. It is required
 * to implement just one method - nextString() - and it's supposed to
 * be as fast as possible to minimally impact the results of
 * testing. Various implementations provide different testing strings
 * which may ultimately greatly impact algorithms' performance
 * statistics.
 */
public interface StringSource {
    public String nextString();
}
