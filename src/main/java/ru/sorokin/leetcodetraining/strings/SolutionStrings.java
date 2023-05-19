package ru.sorokin.leetcodetraining.strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SolutionStrings {

    /**
     *  Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
     *  Symbol       Value
     *  I             1
     *  V             5
     *  X             10
     *  L             50
     *  C             100
     *  D             500
     *  M             1000
     *
     * For example, 2 is written as II in Roman numeral, just two ones added together.
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
     * Given a roman numeral, convert it to an integer.
     *
     * Input: s = "III"
     * Output: 3
     * Explanation: III = 3.
     *
     * Input: s = "LVIII"
     * Output: 58
     * Explanation: L = 50, V= 5, III = 3.
     *
     * Input: s = "MCMXCIV"
     * Output: 1994
     * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
     *
     * Constraints:
     *
     * 1 <= s.length <= 15
     * s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
     * It is guaranteed that s is a valid roman numeral in the range [1, 3999].
     * @param s
     * @return
     */

    //Мое решение
    public int romanToInt(String s) {
        Map<String, Integer> numberRoman = new HashMap<>(20);
        numberRoman.put("I", 1);
        numberRoman.put("V", 5);
        numberRoman.put("X", 10);
        numberRoman.put("L", 50);
        numberRoman.put("C", 100);
        numberRoman.put("D", 500);
        numberRoman.put("M", 1000);
        numberRoman.put("IV", 4);
        numberRoman.put("IX", 9);
        numberRoman.put("XL", 40);
        numberRoman.put("XC", 90);
        numberRoman.put("CD", 400);
        numberRoman.put("CM", 900);
        Set<String> numberRomanForMinus = Set.of("I", "X", "C");
        int count = 0;
        int resultTemp = 0;
        while (count < s.length()) {
            if (numberRomanForMinus.contains(String.valueOf(s.charAt(count)))) {
                if (s.length() - count > 1 &&
                        numberRoman.containsKey(String.valueOf(new char[]{s.charAt(count), s.charAt(count + 1)}))) {
                    resultTemp += numberRoman.get(String.valueOf(new char[]{s.charAt(count), s.charAt(count + 1)}));
                    count += 2;
                    continue;
                }
            }
            resultTemp += numberRoman.get(String.valueOf(s.charAt(count)));
            count++;
        }
        return resultTemp;
    }
    //Чужое крутое решение
    public int romanToIntBestVersion(String s) {
        int ans = 0;
        int num = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            switch (s.charAt(i)) {
                case 'I': num = 1; break;
                case 'V': num = 5; break;
                case 'X': num = 10; break;
                case 'L': num = 50; break;
                case 'C': num = 100; break;
                case 'D': num = 500; break;
                case 'M': num = 1000; break;
            }
            if (num * 4 > ans) ans += num;
            else ans -= num;
        }
        return ans;
    }
}
