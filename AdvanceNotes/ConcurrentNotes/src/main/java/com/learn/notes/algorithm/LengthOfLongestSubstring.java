package com.learn.notes.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * 以(a)bcabcbb 开始的最长字符串为(abc)abcbb}
 * 以a(b)cabcbb开始的最长字符串为a(bca)bcbb
 * 以ab(c)abcbb 开始的最长字符串为ab(cab)cbb
 * 以abc(a)bcbb 开始的最长字符串为abc(abc)bb
 * 以abca(b)cbb 开始的最长字符串为abca(bc)bb
 * 以abcab(c)bb 开始的最长字符串为abcab(cb)b
 * 以abcabc(b)b 开始的最长字符串为abcabc(b)b
 * 以abcabcb(b) 开始的最长字符串为abcabcb(b)
 *
 * 如果我们依次递增地枚举子串的起始位置，那么子串的结束位置也是递增的！这里的原因在于，假设我们选择字符串中的第 k 个字符作为起始位置，并且得到了不包含重复字符的最长子串的结束位置为 rk。
 * 那么当我们选择第 k+1 个字符作为起始位置时，首先从 k+1到 rk的字符显然是不重复的，并且由于少了原本的第 k 个字符，我们可以尝试继续增大 rk直到右侧出现了重复字符为止。
 * 这样一来，我们就可以使用「滑动窗口」来解决这个问题了：
 *
 * 1、我们使用两个指针表示字符串中的某个子串（或窗口）的左右边界，其中左指针代表着上文中「枚举子串的起始位置」，
 *    而右指针即为上文中的 rk
 * 2、在每一步的操作中，我们会将左指针向右移动一格，表示 我们开始枚举下一个字符作为起始位置，
 *    然后我们可以不断地向右移动右指针，但需要保证这两个指针对应的子串中没有重复的字符。
 *    在移动结束后，这个子串就对应着 以左指针开始的，不包含重复字符的最长子串。我们记录下这个子串的长度；
 * 3、在枚举结束后，我们找到的最长的子串的长度即为答案。
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        //int result = LengthOfLongestSubstring.lengthHandler("abcabcbb");
        int result = LengthOfLongestSubstring.lengthHandler("abcacba");
        System.out.println(result);
    }

    public static int lengthHandler(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, result = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            result = Math.max(result, occ.size());
        }
        return result;
    }
}
