package ru.sorokin.leetcodetraining.digitals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SolutionDigit {

    /**
     * Given an integer x, return true if x is a
     * palindrome
     * , and false otherwise.
     *
     *
     *
     * Example 1:
     *
     * Input: x = 121
     * Output: true
     * Explanation: 121 reads as 121 from left to right and from right to left.
     * Example 2:
     *
     * Input: x = -121
     * Output: false
     * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
     * Example 3:
     *
     * Input: x = 10
     * Output: false
     * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
     *
     *
     * Constraints:
     *
     * -2^31 <= x <= 2^31 - 1
     */
    public boolean isPalindrome(int x) {
        String convertInt = String.valueOf(x);
        int count = 0;
        int stopIndex = convertInt.length() / 2;
        while (count < stopIndex) {
            if (convertInt.charAt(count) == convertInt.charAt(convertInt.length() - 1 - count)) {
                count++;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 7. Reverse Integer
     * Given a signed 32-bit integer x, return x with its digits reversed.
     * If reversing x causes the value to go outside the signed 32-bit integer range [-2^31, 2^31 - 1], then return 0.
     * <p>
     * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
     * <p>
     * Example 1:
     * Input: x = 123
     * Output: 321
     * <p>
     * Example 2:
     * Input: x = -123
     * Output: -321
     * <p>
     * Example 3:
     * Input: x = 120
     * Output: 21
     * <p>
     * Constraints:
     * <p>
     * -2^31 <= x <= 2^31 - 1
     */
    public int reverse(int input) {
        int response = 0;
        while (input != 0) {
            int temp = input % 10;
            response = response * 10 + temp;
            input = (input - temp) / 10;
            if (response % 10 != temp) return 0;
        }
        return response;
    }

    /**
     * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
     * <p>
     * Symbol       Value
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * For example, 2 is written as II in Roman numeral, just two one's added together.
     * 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
     * <p>
     * Roman numerals are usually written largest to smallest from left to right.
     * However, the numeral for four is not IIII. Instead, the number four is written as IV.
     * Because the one is before the five we subtract it making four.
     * The same principle applies to the number nine, which is written as IX.
     * There are six instances where subtraction is used:
     * <p>
     * I can be placed before V (5) and X (10) to make 4 and 9.
     * X can be placed before L (50) and C (100) to make 40 and 90.
     * C can be placed before D (500) and M (1000) to make 400 and 900.
     * Given an integer, convert it to a roman numeral.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: num = 3
     * Output: "III"
     * Explanation: 3 is represented as 3 ones.
     * Example 2:
     * <p>
     * Input: num = 58
     * Output: "LVIII"
     * Explanation: L = 50, V = 5, III = 3.
     * Example 3:
     * <p>
     * Input: num = 1994
     * Output: "MCMXCIV"
     * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= num <= 3999
     *
     * @param num
     * @return
     */

    //Мое решение
    public String intToRoman(int num) {
        StringBuilder answer;
        List<String> arrayBuildAnswer = new ArrayList<>();
        Map<Integer, String> numberRoman = Map.of(
                1, "I",
                5, "V",
                10, "X",
                50, "L",
                100, "C",
                500, "D",
                1000, "M"
        );
        String s = String.valueOf(num);
        int count = 1;
        int countSign = s.length();
        for (int i = countSign - 1; i >= 0; i--, count *= 10) {
            int charFromNum = Character.getNumericValue(s.charAt(i));
            if (numberRoman.containsKey(charFromNum * countSign)) {
                arrayBuildAnswer.add(numberRoman.get(charFromNum * count));
            } else if (s.charAt(i) == '4' || s.charAt(i) == '9') {
                answer = new StringBuilder();
                answer.append(numberRoman.get(count)).append(numberRoman.get((charFromNum + 1) * count));
                arrayBuildAnswer.add(answer.toString());
            } else if (s.charAt(i) < '4') {
                answer = new StringBuilder();
                for (int j = 0; j < charFromNum; j++) {
                    answer.append(numberRoman.get(count));
                }
                arrayBuildAnswer.add(answer.toString());
            } else {
                answer = new StringBuilder();
                answer.append(numberRoman.get(5 * count));
                for (int j = 0; j < charFromNum - 5; j++) {
                    answer.append(numberRoman.get(count));
                }
                arrayBuildAnswer.add(answer.toString());
            }
        }
        answer = new StringBuilder();
        for (int i = arrayBuildAnswer.size() - 1; i >= 0; i--) {
            answer.append(arrayBuildAnswer.get(i));
        }
        return answer.toString();
    }
}
