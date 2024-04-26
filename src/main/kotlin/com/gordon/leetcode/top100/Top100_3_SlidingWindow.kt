package org.example.com.gordon.leetcode.top100

/**
 * 第8题 无重复字符的最长子串
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长
 * 子串
 *  的长度。
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 思路: 双指针解法
 * 用hashset记录当前窗口中包含的元素,用于判断是否有重复的元素
 * 左指针指向当前遍历到的字符,右指针指向滑动窗口向右边扩张的位置,默认是-1
 */
fun lengthOfLongestSubstring(s: String): Int {
    if (s.isEmpty()) {
        return 0
    }
    val set = hashSetOf<Char>()
    val len = s.length
    var ans = 0
    var j = -1
    for (i in s.indices) {
        if (i > 0) {
            set.remove(s[i - 1])
        }
        while (j + 1 < len && !set.contains(s[j + 1])) {
            set.add(s[j + 1])
            j++
        }
        ans = ans.coerceAtLeast(j - i + 1)
    }
    return ans
}

/**
 * 第9题 找到字符串中所有字母异位词
 * 438. 找到字符串中所有字母异位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *  示例 2:
 *
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 */
fun findAnagrams(s: String, p: String): List<Int> {
    if (s.length < p.length) {
        return emptyList()
    }
    val result = mutableListOf<Int>()
    val sArr = IntArray(26)
    val pArr = IntArray(26)
    for (i in p.indices) {
        sArr[s[i] - 'a']++
        pArr[p[i] - 'a']++
    }
    if (sArr.contentEquals(pArr)) {
        result.add(0)
    }
    var left = 0
    var right = p.length - 1
    while (true) {
        sArr[s[left] - 'a']--
        left++
        right++
        if (right >= s.length) {
            break
        }
        sArr[s[right] - 'a']++
        if (sArr.contentEquals(pArr)) {
            result.add(left)
        }
    }
    return result
}