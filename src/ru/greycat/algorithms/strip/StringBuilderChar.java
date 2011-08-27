package ru.greycat.algorithms.strip;

public class StringBuilderChar implements StripAlgorithm {
    @Override
    public String strip(String s) {
        StringBuilder sb = new StringBuilder(s.length());
        for (int j = 0; j < s.length(); j++) {
            char ch = s.charAt(j);
            if (ch >= ' ')
                sb.append(ch);
        }
        return sb.toString();
    }
}
