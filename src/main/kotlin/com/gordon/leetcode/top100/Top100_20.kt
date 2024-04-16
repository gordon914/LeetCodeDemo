package org.example.com.gordon.leetcode.top100

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
