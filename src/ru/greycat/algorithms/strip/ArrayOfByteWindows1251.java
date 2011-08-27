package ru.greycat.algorithms.strip;

import java.nio.charset.Charset;

/**
 * Probably the slowest possible algorithm - essentially converts an internal
 * Java text representation to a foreign 1-byte encoding.
 *
 * Doesn't work with full range of unicode.
 */
public class ArrayOfByteWindows1251 implements StripAlgorithm {
    public static Charset WINDOWS1251 = Charset.forName("windows-1251");

    @Override
    public String strip(String s) throws Exception {
        byte[] oldBytes = s.getBytes(WINDOWS1251);
        byte[] newBytes = new byte[oldBytes.length];
        int newlen = 0;
        for (int j = 0; j < oldBytes.length; j++) {
            byte ch = oldBytes[j];
            if (!(ch >= 0 && ch < 32)) {
                newBytes[newlen] = ch;
                newlen++;
            }
        }
        return new String(newBytes, 0, newlen, WINDOWS1251);
    }
}
