package ru.greycat.stringsources;

import java.util.Random;

public class MultipleStrings implements StringSource {
    private String str[];
    private int last;
    private Random r;

    public MultipleStrings(int num, double probabilityOfControl) {
        r = new Random(42);
        str = new String[num];
        for (int i = 0; i < num; i++)
            str[i] = generateString(r.nextDouble() < probabilityOfControl);
        last = -1;
    }

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
