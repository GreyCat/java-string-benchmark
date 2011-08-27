package ru.greycat.algorithms.strip;

import java.lang.reflect.Field;

/**
 * Uses reflection's black magic to access {@link String} internal structures.
 */
public class Voo1 implements StripAlgorithm {

    // Has to be done only once - so cache those! Prohibitively expensive otherwise
    private Field value;
    private Field offset;
    private Field count;
    private Field hash;

    public Voo1() {
        try {
            value = String.class.getDeclaredField("value");
            value.setAccessible(true);
            offset = String.class.getDeclaredField("offset");
            offset.setAccessible(true);
            count = String.class.getDeclaredField("count");
            count.setAccessible(true);
            hash = String.class.getDeclaredField("hash");
            hash.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String strip(String s) throws Exception {
        final int length = s.length();
        char[] chars = null;
        int off = 0;
        try {
            chars = (char[]) value.get(s);
            off = offset.getInt(s);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        int newLen = off;
        for (int j = off; j < off + length; j++) {
            final char ch = chars[j];
            if (ch >= ' ') {
                chars[newLen] = ch;
                newLen++;
            }
        }
        if (newLen - off != length) {
            // We changed the internal state of the string, so at least
            // be friendly enough to correct it.
            try {
                count.setInt(s, newLen - off);
                // Have to recompute hash later on
                hash.setInt(s, 0);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        // Well we have to return something
        return s;
    }
}
