package org.example.com.gordon.leetcode.top100

import java.util.*

/**
 * 第10题 和为 K 的子数组
 * 560. 和为 K 的子数组
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 *
 * 子数组是数组中元素的连续非空序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */
fun subarraySum(nums: IntArray, k: Int): Int {
    var count = 0
    val map = hashMapOf(0 to 1)
    var pre = 0
    nums.forEach { num ->
        pre += num
        if (map.containsKey(pre - k)) {
            count += map.getOrDefault(pre - k, 0)
        }
        map[pre] = map.getOrDefault(pre, 0) + 1

    }
    return count
}

/**
 * 第11题 滑动窗口最大值
 * 239. 滑动窗口最大值
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回 滑动窗口中的最大值 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * 示例 2：
 *
 * 输入：nums = [1], k = 1
 * 输出：[1]
 */
fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
    val queue = MyQueue()
    val ans = mutableListOf<Int>()
    for (i in 0 until k) {
        queue.add(nums[i])
    }
    ans.add(queue.peek())
    for (i in k until nums.size) {
        queue.poll(nums[i - k])
        queue.add(nums[i])
        ans.add(queue.peek())
    }
    return ans.toIntArray()
}

class MyQueue {
    val queue = LinkedList<Int>()
    fun add(num: Int) {
        while (queue.isNotEmpty() && num > queue.last()) {
            queue.removeLast()
        }
        queue.add(num)
    }

    fun peek() = queue.peek()
    fun poll(num: Int) {
        if (queue.isNotEmpty() && num == queue.peek()) {
            queue.poll()
        }
    }
}

/**
 * 第12题 最小覆盖子串
 * 76. 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 *
 *
 * 注意：
 *
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 *
 * 示例 1：
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 * 示例 2：
 *
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 解释：整个字符串 s 是最小覆盖子串。
 * 示例 3:
 *
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 */
fun minWindow(s: String, t: String): String {
    var lp = 0
    var rp = -1
    val oriMap = hashMapOf<Char, Int>()
    val cntMap = hashMapOf<Char, Int>()
    var minLen = Int.MAX_VALUE
    var ansL = -1
    var ansR = -1
    t.forEach {
        oriMap[it] = oriMap.getOrDefault(it, 0) + 1
    }
    while (rp < s.length) {
        rp++
        if (rp < s.length && oriMap.containsKey(s[rp])) {
            cntMap[s[rp]] = cntMap.getOrDefault(s[rp], 0) + 1
        }
        while (lp <= rp && check(cntMap, oriMap)) {
            if (rp - lp + 1 < minLen) {
                minLen = rp - lp + 1
                ansL = lp
                ansR = lp + minLen
            }
            cntMap[s[lp]] = cntMap.getOrDefault(s[lp], 0) - 1
            lp++
        }
    }
    return if (ansL == -1) "" else s.substring(ansL, ansR)
}

private fun check(cnt: Map<Char, Int>, ori: Map<Char, Int>): Boolean {
    ori.forEach { (key, value) ->
        if (cnt.getOrDefault(key, 0) < value) {
            return false
        }
    }
    cnt.forEach { key, u ->

    }
    return true
}