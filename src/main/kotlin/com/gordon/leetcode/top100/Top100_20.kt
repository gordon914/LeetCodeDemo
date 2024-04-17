package org.example.com.gordon.leetcode.top100

import java.util.*

/**
 * 第一题
 * 1.两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 */
fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = mutableMapOf<Int, Int>()
    for (i in nums.indices) {
        if (map.containsKey(nums[i])) {
            return intArrayOf(map[nums[i]]!!, i)
        } else {
            map[target - nums[i]] = i
        }
    }
    return IntArray(0)
}

/**
 * 第2题
 * 49. 字母异位词分组
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 *
 *
 *
 * 示例 1:
 *
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 示例 2:
 *
 * 输入: strs = [""]
 * 输出: [[""]]
 * 示例 3:
 *
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 */
fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val map = mutableMapOf<String, MutableList<String>>()
    strs.forEach { str ->
        val key = str.toCharArray().apply {
            sort()
        }.contentToString()
        map.getOrPut(key) { mutableListOf() }.add(str)
    }
    return map.values.toList()
}

/**
 * 第3题
 * 128. 最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * 示例 1：
 *
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 *
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 */
fun longestConsecutive(nums: IntArray): Int {
    val hashSet = nums.toHashSet()
    var history = 0
    var count = 0
    hashSet.forEach { num ->
        if (!hashSet.contains(num - 1)) {
            var current = num
            count = 1
            while (hashSet.contains(current)) {
                current += 1
                count++
            }
            history = history.coerceAtLeast(count)
        }
    }
    return history
}

/**
 * 第4题
 * 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 示例 2:
 *
 * 输入: nums = [0]
 * 输出: [0]
 *
 * 思路:用快慢指针,快指针i遍历数组,慢指针j指向开始的位置
 * 当快指针指向的数据不是0,就交换i,j所在的值
 * 同时往后移动指针j
 * 遍历完之后,数组也就把0移动到了后面
 */
fun moveZeroes(nums: IntArray): Unit {
    var j = 0
    for (i in nums.indices) {
        if (nums[i] != 0) {
            val tmp = nums[i]
            nums[i] = nums[j]
            nums[j] = tmp
            j++
        }
    }
}

/**
 * 第5题 盛最多水的容器
 * 11. 盛最多水的容器
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 *
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 返回容器可以储存的最大水量。
 *
 * 说明：你不能倾斜容器。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 示例 2：
 *
 * 输入：height = [1,1]
 * 输出：1
 */
fun maxArea(height: IntArray): Int {
    var l = 0
    var r = height.lastIndex
    var ans = Int.MIN_VALUE
    while (l < r) {
        val area = minOf(height[l], height[r]) * (r - l)
        ans = ans.coerceAtLeast(area)
        if (height[l] < height[r]) {
            l++
        } else {
            r--
        }
    }
    return ans
}

/**
 * 第6题
 * 15. 三数之和
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 *
 * 你返回所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 * 示例 2：
 *
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 * 示例 3：
 *
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 */
fun threeSum(nums: IntArray): List<List<Int>> {
    val ans = mutableListOf<List<Int>>()
    nums.sort()
    for (i in nums.indices) {
        if (nums[i] > 0) {
            return ans
        }
        if (i > 0 && nums[i] == nums[i - 1]) {
            continue
        }
        var l = i + 1
        var r = nums.lastIndex
        while (l < r) {
            val sum = nums[i] + nums[l] + nums[r]
            if (sum == 0) {
                ans.add(listOf(nums[i], nums[l], nums[r]))
                while (l < r && nums[r] == nums[r - 1]) {
                    r--
                }
                while (l < r && nums[l] == nums[l + 1]) {
                    l++
                }
                l++
                r--
            } else if (sum > 0) {
                r--
            } else {
                l++
            }
        }
    }
    return ans
}

/**
 * 第7题
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 示例 1：
 *
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 *
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 */
fun trap(height: IntArray): Int {
    var leftMax = 0
    var rightMax = 0
    var l = 0
    var r = height.lastIndex
    var ans = 0
    while (l < r) {
        leftMax = leftMax.coerceAtLeast(height[l])
        rightMax = rightMax.coerceAtLeast(height[r])
        if (height[l] < height[r]) {
            ans += leftMax - height[l]
            l++
        } else {
            ans += rightMax - height[r]
            r--
        }
    }
    return ans
}

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


/**
 * 第13题 最大子数组和
 * 53. 最大子数组和
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 子数组
 * 是数组中的一个连续部分。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 *
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 *
 * 思路:
 * 假设以i为结尾的子数组最大和为 dp[i]
 * 则有dp[i] = max(dp[i-1]+nums[i],nums[i])
 * 遍历数组,取最大值即可.
 * 空间优化:  dp[i]依赖于上一项,用一个变量来记录上次的值即可.
 */
private fun maxSubArray2(nums: IntArray): Int {
    var maxHistory = Int.MIN_VALUE
    var pre = 0
    nums.forEach {
        pre = maxOf(pre + it, it)
        maxHistory = maxHistory.coerceAtLeast(pre)
    }
    return maxHistory
}

/**
 * 第14题 合并区间
 * 56. 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 *
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
fun merge(intervals: Array<IntArray>): Array<IntArray> {
    intervals.sortBy { it[0] }
    val list = mutableListOf<IntArray>()
    intervals.forEachIndexed { _, values ->
        if (list.isEmpty() || list.last()[1] < values[0]) {
            list.add(values)
        } else {
            list.last()[1] = maxOf(list.last()[1], values[1])
        }
    }
    return list.toTypedArray()
}

/**
 * 第15题 轮转数组
 * 189. 轮转数组
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 *
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右轮转 1 步: [99,-1,-100,3]
 * 向右轮转 2 步: [3,99,-1,-100]
 */
fun rotate(nums: IntArray, k: Int): Unit {
    val fk = k % nums.size
    if (fk > 0) {
        reverse(nums, 0, nums.lastIndex)
        reverse(nums, 0, fk - 1)
        reverse(nums, fk, nums.lastIndex)
    }
}

private fun reverse(nums: IntArray, start: Int, end: Int) {
    var l = start
    var r = end
    while (l <= r) {
        val tmp = nums[l]
        nums[l] = nums[r]
        nums[r] = tmp
        l++
        r--
    }
}

/**
 * 第16题 除自身以外数组的乘积
 * 238. 除自身以外数组的乘积
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 *
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 *
 * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 * 示例 2:
 *
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0,0]
 */
fun productExceptSelf(nums: IntArray): IntArray {
    val ans = IntArray(nums.size)
    ans[0] = 1
    //累乘当前元素左边的元素
    for (i in 1 until nums.size) {
        ans[i] = ans[i - 1] * nums[i - 1]
    }
    var factorR = 1
    //再累乘当前元素右边的元素
    for (i in nums.lastIndex downTo 0) {
        ans[i] = ans[i] * factorR
        factorR *= nums[i]
    }
    return ans
}

/**
 * 第17题 缺失的第一个正数
 * 41. 缺失的第一个正数
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 *
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,0]
 * 输出：3
 * 解释：范围 [1,2] 中的数字都在数组中。
 * 示例 2：
 *
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 * 解释：1 在数组中，但 2 没有。
 * 示例 3：
 *
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 * 解释：最小的正数 1 没有出现。
 */
fun firstMissingPositive(nums: IntArray): Int {
    val n = nums.size
    //如果所有的x∈[1,n] 那么必然有nums[nums[i]-1] = x
    for (i in nums.indices) {
        while (nums[i] > 0 && nums[i] <= nums.size && nums[nums[i] - 1] != nums[i]) {
            val tmp = nums[nums[i] - 1]
            nums[nums[i] - 1] = nums[i]
            nums[i] = tmp
        }
    }
    //遍历数组,如果当前元素不是连续的,那么i+1就是缺失的元素
    for (i in nums.indices) {
        if (nums[i] != i + 1) {
            return i + 1
        }
    }
    //都满足了,n+1就是缺失的元素
    return n + 1
}

/**
 * 第18题
 * 73. 矩阵置零
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 */
fun setZeroes(matrix: Array<IntArray>): Unit {
    val m = matrix.size
    val n = matrix[0].size
    var firstRowHas0 = false
    var firstColHas0 = false
    //标记第一列中是否有0
    for (i in 0 until m) {
        if (matrix[i][0] == 0) {
            firstColHas0 = true
            break
        }
    }
    //标记第一行中是否有0
    for (j in 0 until n) {
        if (matrix[0][j] == 0) {
            firstRowHas0 = true
            break
        }
    }
    //从第二行第二列开始遍历,如果存在0,就把对应的行和列的第一个元素标记为0
    for (i in 1 until m) {
        for (j in 1 until n) {
            if (matrix[i][j] == 0) {
                matrix[i][0] = 0
                matrix[0][j] = 0
            }
        }
    }
    //再次遍历,如果该元素所在的行或者列第一个值是0,那么就把当前元素置为0
    for (i in 1 until m) {
        for (j in 1 until n) {
            if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                matrix[i][j] = 0
            }
        }
    }
    //把第一列的元素置为0
    if (firstColHas0) {
        for (i in 0 until m) {
            matrix[i][0] = 0
        }
    }
    //把第一行的元素置为0
    if (firstRowHas0) {
        for (j in 0 until n) {
            matrix[0][j] = 0
        }
    }
}

/**
 * 第19题 螺旋矩阵
 * 54. 螺旋矩阵
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 */
fun spiralOrder(matrix: Array<IntArray>): List<Int> {
    val m = matrix.size
    val n = matrix[0].size
    var t = 0
    var b = m - 1
    var l = 0
    var r = n - 1
    val ans = mutableListOf<Int>()
    while (l <= r && t <= b) {
        for (i in l until r) {
            ans.add(matrix[t][i])
        }
        for (j in t..b) {
            ans.add(matrix[j][r])
        }
        if (l < r && t < b) {
            for (i in r - 1 downTo l + 1) {
                ans.add(matrix[b][i])
            }
            for (j in b downTo t + 1) {
                ans.add(matrix[j][l])
            }
        }
        t++
        b--
        l++
        r--
    }
    return ans
}

/**
 * 第20题 旋转图像
 * 48. 旋转图像
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 *
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 * 示例 2：
 *
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 *
 * 思路:
 * 1. 先沿着中心的x轴上下翻转
 * 2. 再沿着对角线45度交换
 * 最后的结果就是旋转了90度
 */
fun rotate(matrix: Array<IntArray>): Unit {
    val m = matrix.size
    val n = matrix[0].size
    for (i in 0 until m / 2) {
        for (j in 0 until n) {
            val tmp = matrix[i][j]
            matrix[i][j] = matrix[n - i - 1][j]
            matrix[n - i - 1][j] = tmp
        }
    }
    for (i in 0 until m) {
        for (j in 0 until i) {
            val tmp = matrix[i][j]
            matrix[i][j] = matrix[j][i]
            matrix[j][i] = tmp
        }
    }
}

/**
 * 第21题 搜索二维矩阵 II
 * 240. 搜索二维矩阵 II
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 *
 */
fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
    val m = matrix.size
    val n = matrix[0].size
    var i = 0
    var j = n - 1
    while (i < m && j >= 0) {
        if (matrix[i][j] == target) {
            return true
        } else if (matrix[i][j] > target) {
            j--
        } else {
            i++
        }
    }
    return false
}