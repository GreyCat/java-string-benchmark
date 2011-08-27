package ru.greycat.algorithms.strip;

/**
 * Combines ideas from {@link RatchetFreak2} and {@link EdStaub1} algorithms.
 */
public class RatchetFreak2EdStaub1GreyCat1 implements StripAlgorithm {
    char[] oldChars = new char[5];

    @Override
    public String strip(String s) throws Exception {
        int length = s.length();
        if (oldChars.length < length + 1) {
            oldChars = new char[length + 1];
        }
        s.getChars(0, length, oldChars, 0);
        oldChars[length] = '\0'; // avoiding explicit bound check in while

        // find first non-printable,
        // if there are none it ends on the null char I appended
        int newLen = -1;
        while (oldChars[++newLen] >= ' ');

        for (int j = newLen; j < length; j++) {
            char ch = oldChars[j];
            if (ch >= ' ') {
                oldChars[newLen] = ch; // the while avoids repeated overwriting here when newLen==j
                newLen++;
            }
        }
        return new String(oldChars, 0, newLen);
    }
}
