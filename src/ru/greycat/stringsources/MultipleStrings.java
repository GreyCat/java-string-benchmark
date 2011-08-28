package ru.greycat.stringsources;

import java.util.Random;

/**
 * Prepares a list of generated strings with given properties, stores
 * them in in-memory array and returns them in round-robin fashion.
 */
public class MultipleStrings implements StringSource {
    private String str[];
    private int last;
    private Random r;

    /**
     * Initializes a provider of multiple strings, pregenerating
     * <code>num</code> strings in memory. Every string generated
     * might be a string containing control characters (with
     * probability of <code>probabilityOfControl</code>) or a string
     * without control characters (with probability of <code>1 -
     * probabilityOfControl</code>). Supplying 0 as
     * <code>probabilityOfControl</code> thus generates a list of
     * strings without control characters at all and supplying 1
     * ensures that almost all strings would contain at least one
     * control character.
     */
    public MultipleStrings(int num, double probabilityOfControl) {
        r = new Random(42);
        str = new String[num];
        for (int i = 0; i < num; i++)
            str[i] = generateString(r.nextDouble() < probabilityOfControl);
        last = -1;
    }

    /**
     * Returns the next available string in pregenerated array. If
     * we're out of strings, we restart from the beginning, thus
     * providing strings infinitely in round-robin fashion.
     */
    @Override
    public String nextString() {
        last = (last + 1) % str.length;
        return str[last];
    }

    private String generateString(boolean control) {
        int len = 60 + r.nextInt(100);
        char[] l = new char[len];
        for (int i = 0; i < len; i++) {
            l[i] = (char) (control ? r.nextInt(128) : (32 + r.nextInt(128 - 32)));
        }
        return new String(l);
    }
}
