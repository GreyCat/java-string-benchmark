package ru.greycat.algorithms.strip;

public class ArrayOfCharFromStringCharAt implements StripAlgorithm {
    @Override
    public String strip(String s) {
        char[] res = new char[s.length()];
        int newlen = 0;
        for (int j = 0; j < s.length(); j++) {
            char ch = s.charAt(j);
            if (ch >= ' ') {
                res[newlen] = ch;
                newlen++;
            }
        }
        return new String(res, 0, newlen);
    }
}
