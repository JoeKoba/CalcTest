package MyCalculator;

import java.util.Arrays;
import java.util.Scanner;

public class c3 {

    static Scanner in = new Scanner(System.in);
    static String userString = in.nextLine();

    static String[] signs = {"+", "*", "/", "-"};
    static String[] separateValues = userString.split(Arrays.toString(signs));

    static String[] arabicNumbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    static String[] romanNumbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

    public static void main(String[] args) {
        System.out.println(calc());
    }

    public static String calc() {
        int arabOrRoman = -1;
        if (arabMetric()) {
            arabOrRoman = 0;
        }
        int roman = -1;
        if (romanMetric()) {
            arabOrRoman = 1;
        }
        for (int i = 0; i < separateValues.length; i++) {
            for (int j = 0; j < romanNumbers.length; j++) {
                if (separateValues[i].equals(romanNumbers[j])) {
                    separateValues[i] = arabicNumbers[j];
                }
            }
        }
        int a = Integer.parseInt(separateValues[0]);
        int b = Integer.parseInt(separateValues[1]);

        int res = 0;
        if (userString.contains("+")) {
            res = a + b;
        } else if (userString.contains("-")) {
            res = a - b;
        } else if (userString.contains("*")) {
            res = a * b;
        } else if (userString.contains("/")) {
            res = a / b;
        }

        String result = null;

        if (arabOrRoman == 1) {
            result = arabicToRoman(res);
        } else {
            result = "" + res;
        }
        if (!(arabMetric()) && !(romanMetric())) {
            result = "Enter correct numbers!";
            return result;
        }
        return result;
    }

    public static String arabicToRoman(int num) {
        String[] romanNumbers = new String[]{"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] arabicNumbers = new int[]{100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder result = new StringBuilder();
        int count = 0;
        while (count < romanNumbers.length) {
            while (num >= arabicNumbers[count]) {
                int count2 = num / arabicNumbers[count];
                num = num % arabicNumbers[count];
                for (int i = 0; i < count2; i++) {
                    result.append(romanNumbers[count]);
                }
            }
            count++;
        }
        return result.toString();
    }

    static boolean arabMetric() {
        boolean metric = true;
        int count = 0;
        for (String i : separateValues) {
            if (Arrays.toString(arabicNumbers).contains(i)) {
                count++;
            }
        }
        if (count != 2) {
            metric = false;
        }
        return metric;
    }

    static boolean romanMetric() {
        boolean metric = true;
        int count = 0;
        for (String i : separateValues) {
            if (Arrays.toString(romanNumbers).contains(i)) {
                count++;
            }
        }
        if (count != 2) {
            metric = false;
        }
        return metric;
    }
}