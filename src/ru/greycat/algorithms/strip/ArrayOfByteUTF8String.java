package ru.greycat.algorithms.strip;

public class ArrayOfByteUTF8String implements StripAlgorithm {
    @Override
    public String strip(String s) throws Exception {
        byte[] oldBytes = s.getBytes("utf-8");
        byte[] newBytes = new byte[oldBytes.length];
        int newlen = 0;
        for (int j = 0; j < oldBytes.length; j++) {
            byte ch = oldBytes[j];
            if (!(ch >= 0 && ch < 32)) {
                newBytes[newlen] = ch;
                newlen++;
            }
        }
        return new String(newBytes, 0, newlen, "utf-8");
    }
}
