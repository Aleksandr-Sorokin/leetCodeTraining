package ru.sorokin.leetcodetraining.digitals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SolutionDigit {

    /**
     * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
     *
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
     *
     * Roman numerals are usually written largest to smallest from left to right.
     * However, the numeral for four is not IIII. Instead, the number four is written as IV.
     * Because the one is before the five we subtract it making four.
     * The same principle applies to the number nine, which is written as IX.
     * There are six instances where subtraction is used:
     *
     * I can be placed before V (5) and X (10) to make 4 and 9.
     * X can be placed before L (50) and C (100) to make 40 and 90.
     * C can be placed before D (500) and M (1000) to make 400 and 900.
     * Given an integer, convert it to a roman numeral.
     *
     *
     *
     * Example 1:
     *
     * Input: num = 3
     * Output: "III"
     * Explanation: 3 is represented as 3 ones.
     * Example 2:
     *
     * Input: num = 58
     * Output: "LVIII"
     * Explanation: L = 50, V = 5, III = 3.
     * Example 3:
     *
     * Input: num = 1994
     * Output: "MCMXCIV"
     * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
     *
     *
     * Constraints:
     *
     * 1 <= num <= 3999
     * @param num
     * @return
     */

    //Мое решение
    public String intToRoman(int num) {
        StringBuilder answer;
        List<String> arrayBuildAnswer = new ArrayList<>();
        Map<Integer, String> numberRoman = Map.of(
                1,"I",
                5,"V",
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