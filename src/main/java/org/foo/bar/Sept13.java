package org.foo.bar;

import java.util.ArrayList;
import java.util.List;

/**
1) Write a program to encrypt a message using the ROT13 technique.
2) Write a program to convert a decimal number to its octal equivalent.
 */

public class Sept13 {
  public static String ROT13(String s) {
    StringBuilder sb = new StringBuilder();
    for (char c : s.toCharArray()) {
      char a = 'a' <= c && c < 'a' + 26 ? 'a' : 'A' <= c && c < 'A' + 26 ? 'A' : 0;
      if (a != 0) {
        int b = (c - a + 13) % 26;
        sb.append((char) (b + a));
      } else {
        sb.append(c);
      }
    }
    return sb.toString();
  }

  public static String doubleToOctal(double d) {
    boolean isNeg = false;
    if (d < 0) {
      isNeg = true;
      d = -d;
    }
    String s = String.valueOf(d);
    int whole = Integer.valueOf(s.substring(0, s.indexOf(".")));
    double deci = Float.valueOf(s.substring(s.indexOf("."), s.length()));
    StringBuilder wholeRes = new StringBuilder();
    StringBuilder deciRes = new StringBuilder();
    while (whole != 0) {
      wholeRes.append(whole % 8);
      whole /= 8;
    }
    if (isNeg) {
      wholeRes.append('-');
    }
    while (deci >= 10e-10) {
      deci = deci * 8;
      int t = (int) (deci % 10);
      deciRes.append(t);
      deci -= t;
    }
    wholeRes.reverse();
    if (deciRes.length() != 0) {
      wholeRes.append('.').append(deciRes);
    }
    return wholeRes.toString();
  }

  public static void main(String[] args) {
    List<String[]> ROT13Cases = new ArrayList<String[]>() {
      {
        add(new String[] { "O", "B" });
        add(new String[] { "How can you tell an extrovert from an introvert at NSA?",
            "Ubj pna lbh gryy na rkgebireg sebz na vagebireg ng AFN?" });
      }
    };
    for (String[] c : ROT13Cases) {
      System.out.println(ROT13(c[0]).equals(c[1]));
    }

    List<Object[]> doubleToOctalCases = new ArrayList<Object[]>() {
      {
        add(new Object[] { -8.125D, "-10.1" });
        add(new Object[] { 2.5D, "2.4" });
      }
    };
    for (Object[] c : doubleToOctalCases) {
      System.out.println(doubleToOctal((Double) (c[0])).equals((String) c[1]));
    }
  }
}
