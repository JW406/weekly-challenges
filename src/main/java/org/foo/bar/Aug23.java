package org.foo.bar;

import java.util.ArrayList;
import java.util.Arrays;

/**
Java:
Create a function that takes a number as an argument and returns true if the
number is a valid credit card number, false otherwise.

Credit card numbers must be between 14-19 digits in length, and pass the Luhn
test, described below:

Remove the last digit (this is the "check digit").
Reverse the number.
Double the value of each digit in odd-numbered positions. If the doubled value
has more than 1 digit, add the digits together (e.g. 8 x 2 = 16 ➞ 1 + 6 = 7).
Add all digits.
Subtract the last digit of the sum (from step 4) from 10. The result should be
equal to the check digit from step 1.
Examples
validateCard(1234567890123456) --> false

// Step 1: check digit = 6, num = 123456789012345
// Step 2: num reversed = 543210987654321
// Step 3: digit array after selective doubling: [1, 4, 6, 2, 2, 0, 9, 8, 5, 6, 1, 4, 6, 2, 2]
// Step 4: sum = 58
// Step 5: 10 - 8 = 2 (not equal to 6) --> false

validateCard(1234567890123452) --> true

 */

public class Aug23 {
  public static void main(String[] args) {
    ArrayList<ArrayList<Object>> arr = new ArrayList<ArrayList<Object>>() {
      {
        add(new ArrayList<>(Arrays.asList("1234567890123456", false)));
        add(new ArrayList<>(Arrays.asList("1234567890123452", true)));
        add(new ArrayList<>(Arrays.asList("378282246310005", true)));
        add(new ArrayList<>(Arrays.asList("371449635398431", true)));
        add(new ArrayList<>(Arrays.asList("378734493671000", true)));
        add(new ArrayList<>(Arrays.asList("5610591081018250", true)));
        add(new ArrayList<>(Arrays.asList("30569309025904", true)));
        add(new ArrayList<>(Arrays.asList("38520000023237", true)));
        add(new ArrayList<>(Arrays.asList("6011111111111117", true)));
        add(new ArrayList<>(Arrays.asList("6011000990139424", true)));
        add(new ArrayList<>(Arrays.asList("3530111333300000", true)));
        add(new ArrayList<>(Arrays.asList("3566002020360505", true)));
      }
    };
    for (ArrayList<Object> item : arr) {
      String p = (String) item.get(0);
      Boolean res = (Boolean) item.get(1);
      assert validateCard(p) == res;
      System.out.println(p + " is a validCard: " + res);
    }
  }

  public static boolean validateCard(String cardNum) {
    int lastDigit = cardNum.charAt(cardNum.length() - 1) - '0';
    cardNum = cardNum.substring(0, cardNum.length() - 1);
    int[] arr = numToIntArr(cardNum);
    reverseArray(arr);
    for (int i = 0; i < arr.length; i += 2) {
      arr[i] *= 2;
      int sum = 0;
      while (arr[i] > 0) {
        sum += arr[i] % 10;
        arr[i] /= 10;
      }
      arr[i] = sum;
    }
    int total = sum(arr);
    return 10 - total % 10 == lastDigit;
  }

  public static int sum(int[] arr) {
    int res = 0;
    for (int n : arr) {
      res += n;
    }
    return res;
  }

  public static int[] numToIntArr(String num) {
    int[] arr = new int[num.length()];
    int i = 0;
    for (char c : num.toCharArray()) {
      arr[i] = c - '0';
      ++i;
    }
    return arr;
  }

  public static void reverseArray(int[] arr) {
    int i = 0, j = arr.length - 1;
    while (i < j) {
      int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
      ++i;
      --j;
    }
  }
}
