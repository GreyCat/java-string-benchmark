package ru.greycat.algorithms.strip;

public class ArrayOfCharFromArrayOfChar implements StripAlgorithm {
    @Override
    public String strip(String s) {
        char[] oldChars = new char[s.length()];
        s.getChars(0, s.length(), oldChars, 0);
        char[] newChars = new char[s.length()];
        int newLen = 0;
        for (int j = 0; j < s.length(); j++) {
            char ch = oldChars[j];
            if (ch >= ' ') {
                newChars[newLen] = ch;
                newLen++;
            }
        }
        return new String(newChars, 0, newLen);
    }
}
