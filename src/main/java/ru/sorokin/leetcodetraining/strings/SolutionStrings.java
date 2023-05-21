package ru.sorokin.leetcodetraining.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SolutionStrings {

    /**
     * 10. Regular Expression Matching
     * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
     *
     * '.' Matches any single character.​​​​
     * '*' Matches zero or more of the preceding element.
     * The matching should cover the entire input string (not partial).
     *
     *
     *
     * Example 1:
     *
     * Input: s = "aa", p = "a"
     * Output: false
     * Explanation: "a" does not match the entire string "aa".
     * Example 2:
     *
     * Input: s = "aa", p = "a*"
     * Output: true
     * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
     * Example 3:
     *
     * Input: s = "ab", p = ".*"
     * Output: true
     * Explanation: ".*" means "zero or more (*) of any character (.)".
     *
     *
     * Constraints:
     *
     * 1 <= s.length <= 20
     * 1 <= p.length <= 20
     * s contains only lowercase English letters.
     * p contains only lowercase English letters, '.', and '*'.
     * It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
     */
    public boolean isMatch(String s, String p) {
        return java.util.regex.Pattern.matches(p, s);
    }

    /**
     * 8. String to Integer (atoi)
     * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).
     *
     * The algorithm for myAtoi(string s) is as follows:
     *
     * Read in and ignore any leading whitespace.
     * Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
     * Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.
     * Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
     * If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
     * Return the integer as the final result.
     * Note:
     *
     * Only the space character ' ' is considered a whitespace character.
     * Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.
     *
     *
     * Example 1:
     *
     * Input: s = "42"
     * Output: 42
     * Explanation: The underlined characters are what is read in, the caret is the current reader position.
     * Step 1: "42" (no characters read because there is no leading whitespace)
     *          ^
     * Step 2: "42" (no characters read because there is neither a '-' nor '+')
     *          ^
     * Step 3: "42" ("42" is read in)
     *            ^
     * The parsed integer is 42.
     * Since 42 is in the range [-231, 231 - 1], the final result is 42.
     * Example 2:
     *
     * Input: s = "   -42"
     * Output: -42
     * Explanation:
     * Step 1: "   -42" (leading whitespace is read and ignored)
     *             ^
     * Step 2: "   -42" ('-' is read, so the result should be negative)
     *              ^
     * Step 3: "   -42" ("42" is read in)
     *                ^
     * The parsed integer is -42.
     * Since -42 is in the range [-231, 231 - 1], the final result is -42.
     * Example 3:
     *
     * Input: s = "4193 with words"
     * Output: 4193
     * Explanation:
     * Step 1: "4193 with words" (no characters read because there is no leading whitespace)
     *          ^
     * Step 2: "4193 with words" (no characters read because there is neither a '-' nor '+')
     *          ^
     * Step 3: "4193 with words" ("4193" is read in; reading stops because the next character is a non-digit)
     *              ^
     * The parsed integer is 4193.
     * Since 4193 is in the range [-231, 231 - 1], the final result is 4193.
     *
     *
     * Constraints:
     *
     * 0 <= s.length <= 200
     * s consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.
     */
    //Мое решение
    public int myAtoi(String s) {
        String copyAndTrimInputS = s.trim();
        StringBuilder stringBuilder = new StringBuilder();
        int result = 0;
        int resultPositiveOrNegativeSign = 0;
        if (copyAndTrimInputS.length() == 0 || (copyAndTrimInputS.length() == 1 &&
                !Character.isDigit(copyAndTrimInputS.charAt(0)))) {
            return result;
        } else {
            for (int i = 0; i < copyAndTrimInputS.length(); i++) {
                Character temp = copyAndTrimInputS.charAt(i);
                if (!Character.isDigit(temp)) {
                    if (stringBuilder.length() > 0 || resultPositiveOrNegativeSign != 0 ||
                            (temp != Character.valueOf('-') && temp != Character.valueOf('+'))) {
                        break;
                    } else if (temp == Character.valueOf('-') || temp == Character.valueOf('+')){
                        resultPositiveOrNegativeSign = Integer.parseInt(temp + "1");
                    } else {

                    }
                } else {
                    stringBuilder.append(temp);
                }
            }
            if (resultPositiveOrNegativeSign == 0) resultPositiveOrNegativeSign = 1;
            if (stringBuilder.length() != 0) {
                Double tempResultParse = Double.valueOf(stringBuilder.toString()) * resultPositiveOrNegativeSign;
                if (tempResultParse > Integer.MAX_VALUE) {
                    result = Integer.MAX_VALUE * resultPositiveOrNegativeSign;
                } else if (tempResultParse < Integer.MIN_VALUE) {
                    result = Integer.MIN_VALUE;
                } else {
                    result = tempResultParse.intValue();
                }
            }
            return result;
        }
    }
    //Чужое крутое решение
    public int myAtoiBestVersion(String s) {
        s = s.trim();
        Pattern p = Pattern.compile("^[ ]*[+\\-]?[0]*([0-9]\\d*)");
        Matcher m = p.matcher(s);
        if (m.find()){
            try{
                return Integer.parseInt(s.substring(m.start(),m.end()));
            }catch (NumberFormatException e){
                return s.charAt(0) == '-' ? -2147483648 : 2147483647;
            }
        }
        return 0;
    }

    /**
     * 3. Longest Substring Without Repeating Characters
     * Given a string s, find the length of the longest
     * substring
     *  without repeating characters.
     *
     * Example 1:
     * Input: s = "abcabcbb"
     * Output: 3
     * Explanation: The answer is "abc", with the length of 3.
     *
     * Example 2:
     * Input: s = "bbbbb"
     * Output: 1
     * Explanation: The answer is "b", with the length of 1.
     *
     * Example 3:
     * Input: s = "pwwkew"
     * Output: 3
     * Explanation: The answer is "wke", with the length of 3.
     * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
     *
     * Constraints:     *
     * 0 <= s.length <= 5 * 104
     * s consists of English letters, digits, symbols and spaces.
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 1) return 1;
        int max = 0;
        Set<Character> characterSet = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            characterSet.add(ch);
            for (int j = i + 1; j < s.length(); j++) {
                Character chj = s.charAt(j);
                if (!characterSet.contains(chj)) {
                    characterSet.add(chj);
                } else {
                    if (max < characterSet.size()) {
                        max = characterSet.size();
                    }
                    characterSet.removeAll(characterSet);
                    break;
                }
            }
        }
        if (max < characterSet.size()) max = characterSet.size();
        return max;
    }

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
