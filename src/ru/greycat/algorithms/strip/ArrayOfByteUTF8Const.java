package ru.greycat.algorithms.strip;

import java.nio.charset.Charset;

public class ArrayOfByteUTF8Const implements StripAlgorithm {
    public static Charset UTF8 = Charset.forName("utf-8");

    @Override
    public String strip(String s) throws Exception {
        byte[] oldBytes = s.getBytes(UTF8);
        byte[] newBytes = new byte[oldBytes.length];
        int newlen = 0;
        for (int j = 0; j < oldBytes.length; j++) {
            byte ch = oldBytes[j];
            if (!(ch >= 0 && ch < 32)) {
                newBytes[newlen] = ch;
                newlen++;
            }
        }
        return new String(newBytes, 0, newlen, UTF8);
    }
}
