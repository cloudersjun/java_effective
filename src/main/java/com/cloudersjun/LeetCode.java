package com.cloudersjun;

/**
 * @author yujun06
 * @since 2020/9/8
 */
public class LeetCode {

    public static void main(String[] args) {
        System.out.println(reverse(-2147483648));
    }

    /**
     * https://leetcode-cn.com/problems/container-with-most-water/ 自己解决方案
     */
    private static int maxAreaMine(int[] height) {
        int max = 0;
        for (int i = 1, length = height.length; i < length; i++) {
            int bound = height[i];
            for (int j = i - 1; j >= 0; j--) {
                int h = Math.min(bound, height[j]);
                max = Math.max(max, h * (i - j));
            }
        }
        return max;
    }

    /**
     * https://leetcode-cn.com/problems/container-with-most-water/ 最优解决方案
     */
    private static int maxArea(int[] height) {
        int max = 0;
        int i = 0, j = height.length - 1;
        while (i != j) {
            if (height[i] < height[j]) {
                max = Math.max(max, (j - i) * height[i]);
                i++;
            }
            else {
                max = Math.max(max, (j - i) * height[j]);
                j--;
            }
        }
        return max;
    }

    /**
     * https://leetcode-cn.com/problems/reverse-integer/ 自己解决方案
     */
    private static int reverseMine(int x) {
        long result = 0;
        while (x != 0) {
            int s = x % 10;
            if (s != 0 || result != 0) {
                result = result * 10 + s;
            }
            x = x / 10;
        }
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) result;
    }

    /**
     * https://leetcode-cn.com/problems/reverse-integer/ 更优解决方案
     */
    private static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
