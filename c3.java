package MyCalculator;

import java.io.IOException;
import java.util.Scanner;

public class ncalc {
    static int a;
    static String sign;
    static int b;
    static String[] romanNumbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    static String[] arabicNumbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    static String[] signs = {"+", "-", "*", "/"};
    static Scanner in = new Scanner(System.in);
    static String userString = in.nextLine();

    public static void main(String[] args) throws IOException {

        System.out.println(calc(userString));
    }

    public static String calc(String input) throws IOException {
        int temp = -1;
        int indexOfSign = -1;
        for (int i = 0; i < signs.length; i++) {
            temp = input.indexOf(signs[i]);
            if (temp >= 0) {
                indexOfSign = i;
                i = signs.length;
            }
        }

        if (indexOfSign == -1) {
            throw new IndexOutOfBoundsException("Вы ввели недопустимый знак. Допустимые знаки: +, -, *, /");
        }


        sign = signs[indexOfSign];
        String[] operands = input.split(String.valueOf("\\" + sign));
        operands[0] = operands[0].trim();
        operands[1] = operands[1].trim();


        int areBothArabicOrRoman = 0;
        for (int i = 0; i < operands.length; i++) {
            for (int j = 0; j < romanNumbers.length; j++) {
                if (operands[i].equals(romanNumbers[j])) {
                    operands[i] = arabicNumbers[j];
                    areBothArabicOrRoman++;
                }
            }
        }

        if (areBothArabicOrRoman == 1) {
            throw new IOException("Вы ввели числа из разных систем счисления. Введите только арабские или только римские числа.");
        }


        a = Integer.parseInt(operands[0]);
        b = Integer.parseInt(operands[1]);

        if (a < 1 || b < 1 || a > 10 || b > 10) {
            throw new IOException("Введенные значения не входят в допустимый диапазон от 1 до 10.");
        }

        int res = 0;
        if (sign.equals("+")) {
            res = a + b;
        } else if (sign.equals("-")) {
            res = a - b;
        } else if (sign.equals("*")) {
            res = a * b;
        } else if (sign.equals("/")) {
            res = a / b;
        }

        String result = null;
        if (areBothArabicOrRoman == 2) {
            result = arabicToRoman(res);

        } else {
            result = "" + res;
        }


        return result;
    }


    public static String arabicToRoman(int num) throws IOException {
        if (num <= 0) {
            throw new IOException("В римской системе нет отрицательных чисел и 0");
        }
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


}
