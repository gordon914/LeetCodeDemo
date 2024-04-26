package org.example.com.gordon.leetcode.top100

/**
 * 第一题 两数之和
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
 * 第2题 字母异位词分组
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
 * 第3题 最长连续序列
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