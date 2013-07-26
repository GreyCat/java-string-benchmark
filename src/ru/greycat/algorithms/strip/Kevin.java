package ru.greycat.algorithms.strip;

public class Kevin implements StripAlgorithm {
  private char[] chars = new char[256];

  public Kevin() {
  }
  
  @Override
  public String strip(String s) throws Exception {
      final int length = s.length();
      if (length > chars.length) {
        chars = new char[length];
      }
      int newLen = 0;
      for (int j = 0; j < length; j++) {
          final char ch = s.charAt(j);
          if (ch >= ' ') {
              chars[newLen] = ch;
              newLen++;
          }
      }
      if (newLen != length) {
        return new String(chars, 0, newLen);
      }
      return s;
  }
}
