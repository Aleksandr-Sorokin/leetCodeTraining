package ru.sorokin.leetcodetraining.arrays;

public class SolutionArrays {

    /**
     * 1. Two Sum
     * Given an array of integers nums and an integer target,
     * return indices of the two numbers such that they add up to target.
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     *
     * You can return the answer in any order.
     *
     * Example 1:
     * Input: nums = [2,7,11,15], target = 9
     * Output: [0,1]
     * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
     *
     * Example 2:
     * Input: nums = [3,2,4], target = 6
     * Output: [1,2]
     *
     * Example 3:
     * Input: nums = [3,3], target = 6
     * Output: [0,1]
     *
     * Constraints:
     * 2 <= nums.length <= 10^4
     * -10^9 <= nums[i] <= 10^9
     * -10^9 <= target <= 10^9
     */
    public int[] twoSum(int[] nums, int target) {
        int[] out = new int[2];
        boolean trigger = false;
        for (int i = 0; i < nums.length; i++) {
            if (trigger == true) break;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    out[0] = i;
                    out[1] = j;
                    trigger = true;
                    break;
                }
            }
        }
        return out;
    }


    /**
     * Write a function to find the longest common prefix string amongst an array of strings.
     * If there is no common prefix, return an empty string "".
     *
     *  Input: strs = ["flower","flow","flight"]
     *  Output: "fl"
     *
     *  Input: strs = ["dog","racecar","car"]
     *  Output: ""
     *  Explanation: There is no common prefix among the input strings.
     *
     *  1 <= strs.length <= 200
     *  0 <= strs[i].length <= 200
     *  strs[i] consists of only lowercase English letters.
     *
     * @param strs
     * @return
     */
    //Мое решение
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) return strs[0];
        StringBuilder stringBuilder = new StringBuilder();
        Character character = null;
        if (strs[0].length() > 0) character = strs[0].charAt(0);

        int minLength = strs[0].length();
        int count = 0;
        int countTwo = 0;

        while (true) {
            if (count == strs.length) {
                stringBuilder.append(character);
                countTwo++;
                if (countTwo == minLength) return stringBuilder.toString();
                count = 0;
                character = strs[count].charAt(countTwo);
                continue;
            }
            String temp = strs[count++];
            if (temp == null || temp.isEmpty()) return "";
            if (temp.length() < minLength) minLength = temp.length();
            if (temp.charAt(countTwo) != character) return stringBuilder.toString();
        }
    }
    //Чужое крутое решение
    public String longestCommonPrefixBestVersion(String[] strs) {
        String prefix = "";
        for (int i = 0;; i++) {
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].length() <= i || strs[j].charAt(i) != strs[0].charAt(i))
                    return prefix;
            }
            prefix += strs[0].charAt(i);
        }
    }

    /**
     * You are given an integer array height of length n.
     * There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
     * Find two lines that together with the x-axis form a container, such that the container contains the most water.
     * Return the maximum amount of water a container can store.
     * Notice that you may not slant the container.
     * 8    |______________|________
     * 7    |              |     |
     * 6    |  |           |     |
     * 5    |  |     |     |     |
     * 4    |  |     |  |  |     |
     * 3    |  |     |  |  |  |  |
     * 2    |  |  |  |  |  |  |  |
     * 1    |  |  |  |  |  |  |  |
     * 0 |__|__|__|__|__|__|__|__|
     *
     * Input: height = [1,8,6,2,5,4,8,3,7]
     * Output: 49
     * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
     * In this case, the max area of water (blue section) the container can contain is 49.
     *
     * Constraints:
     *
     * n == height.length
     * 2 <= n <= 105
     * 0 <= height[i] <= 104
     * @param height
     * @return
     */

    public int maxArea(int[] height) {
        int maximumArea = Integer.MIN_VALUE;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int shorterLine = Math.min(height[left], height[right]);
            maximumArea = Math.max(maximumArea, shorterLine * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maximumArea;
    }
}
