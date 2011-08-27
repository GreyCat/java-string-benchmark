package ru.greycat.algorithms.strip;

/**
 * Derived from {@link RatchetFreak2EdStaub1GreyCat1}, combines ideas from
 * {@link RatchetFreak2} and {@link EdStaub1} algorithms, plus avoid creation
 * of new String object if possible, returning the old one.
 */
public class RatchetFreak2EdStaub1GreyCat2 implements StripAlgorithm {
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

        // no control characters found? early bail out with existing string
        if (newLen == length)
            return s;

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
