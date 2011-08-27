package ru.greycat.algorithms.strip;

public class StringBuilderCodePoint implements StripAlgorithm {
    @Override
    public String strip(String s) {
        StringBuilder sb = new StringBuilder(s.length());
        for (int j = 0; j < s.length(); j++) {
            int ch = s.codePointAt(j);
            if (ch >= ' ')
                sb.appendCodePoint(ch);
        }
        return sb.toString();
    }
}
