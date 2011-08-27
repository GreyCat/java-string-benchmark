package ru.greycat.algorithms.strip;

/**
 * Derivative of {@link ArrayOfCharFromArrayOfChar}, but uses same array of
 * char to hold both old string and new string, thus saving on 2nd array init.
 */
public class RatchetFreak1 implements StripAlgorithm {
    @Override
    public String strip(String s) {
        int length = s.length();
        char[] oldChars = new char[length];
        s.getChars(0, length, oldChars, 0);
        int newLen = 0;
        for (int j = 0; j < length; j++) {
            char ch = oldChars[j];
            if (ch >= ' ') {
                oldChars[newLen] = ch;
                newLen++;
            }
        }
        return new String(oldChars, 0, newLen);
    }
}
