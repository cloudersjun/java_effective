package com.cloudersjun;

/**
 * @Author yujun
 * @Date 2019/8/4
 */
public class ABCPath {

    private static char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    /**
     * @param grid
     * @return
     */
    public static int length(String[] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int len = grid.length;
        char[][] data = new char[len][];
        int i;
        int rowLen = 0;
        for (i = 0; i < len; i++) {
            char[] chars = grid[i].toCharArray();
            data[i] = chars;
            rowLen = chars.length;
        }
        int j;
        int max = 0;
        int[] ret = new int[len * rowLen];
        for (i = 0; i < len; i++) {
            for (j = 0; j < rowLen; j++) {
                if (data[i][j] == 'A') {
                    max = 1;
                    ret[i * rowLen + j] = 1;
                }
            }
        }
        if (max == 0) {
            return max;
        }
        boolean add = false;
        for (int k = 1; k < chars.length; k++) {
            for (i = 0; i < len; i++) {
                for (j = 0; j < rowLen; j++) {
                    if (data[i][j] == chars[k]) {
                        if ((i > 0 && j > 0 && data[i - 1][j - 1] == chars[k - 1] && ret[(i - 1) * rowLen + j - 1] == k)
                                || (i > 0 && data[i - 1][j] == chars[k - 1] && ret[(i - 1) * rowLen+j] == k)
                                || (i > 0 && j < rowLen - 1 && data[i - 1][j + 1] == chars[k - 1] && ret[(i - 1) * rowLen + j + 1] == k)
                                || (j > 0 && data[i][j - 1] == chars[k - 1] && ret[i * rowLen + j - 1] == k)
                                || (j < rowLen - 1 && data[i][j + 1] == chars[k - 1] && ret[i * rowLen + j + 1] == k)
                                || (i < len - 1 && j > 0 && data[i + 1][j - 1] == chars[k - 1] && ret[(i + 1) * rowLen + j - 1] == k)
                                || (i < len - 1 && data[i + 1][j] == chars[k - 1] && ret[(i + 1) * rowLen + j] == k)
                                || (i < len - 1 && j < rowLen - 1 && data[i + 1][j + 1] == chars[k - 1] && ret[(i + 1) * rowLen + j + 1] == k)
                                ) {
                            ret[i * rowLen + j] = k + 1;
                            max = k + 1;
                            add = true;
                        }
                    }
                }
            }
            if (!add) {
                return max;
            }
            add = false;
        }
        return max;
    }
}
