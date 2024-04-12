package org.example.com.gordon.leetcode.top100

/**
 * 最大子数组和
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * https://leetcode.cn/problems/maximum-subarray/description/?envType=study-plan-v2&envId=top-100-liked
 * 定义i为以i为结尾的子数组表示的最大和dp[i]
 * dp[i] = max(dp[i-1]+nums[i],nums[i])
 * dp[0] = nums[0]
 *
 * 为什么这道题可以使用动态规划?
 * 如何证明它的正确性?
 *
 * 假设和猜想.
 * [-2,1,-3,4,-1,2,1,-5,4]
 *
 *
 */
fun maxSubArray(nums: IntArray): Int {
    var res = Int.MIN_VALUE
    var pre = 0
    nums.forEach { num ->
        pre = (pre + num).coerceAtLeast(num)
        res = res.coerceAtLeast(pre)
    }
    return res
}

/**
 * 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * https://leetcode.cn/problems/merge-intervals/description/?envType=study-plan-v2&envId=top-100-liked
 */
fun merge(intervals: Array<IntArray>): Array<IntArray> {
    if (intervals.isEmpty()) {
        return emptyArray()
    }
    intervals.sortBy { it[0] }
    val merged = mutableListOf<IntArray>()
    for (interval in intervals) {
        val (L, R) = interval
        if (merged.isEmpty() || merged.last()[1] < L) {
            merged.add(interval)
        } else {
            merged.last()[1] = maxOf(merged.last()[1], R)
        }
    }
    return merged.toTypedArray()
}

/**
 * 189. 轮转数组
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * https://leetcode.cn/problems/rotate-array/description/?envType=study-plan-v2&envId=top-100-liked
 *
 * [1,2,3,4,5,6,7] --->[7,6,5,4,3,2,1] --->k =3
 * [7,6,5,4,3,2,1] --->[5,6,7,4,3,2,1]
 * [5,6,7,4,3,2,1] --->[5,6,7,1,2,3,4]
 *
 */
fun rotate(nums: IntArray, k: Int): Unit {
    val fk = k % nums.size
    reverse(nums, 0, nums.lastIndex)
    reverse(nums, 0, fk - 1)
    reverse(nums, k, nums.lastIndex)
}

private fun reverse(nums: IntArray, left: Int, right: Int) {
    var l = left
    var r = right
    while (l < r) {
        val temp = nums[l]
        nums[l] = nums[r]
        nums[r] = temp
        l++
        r--
    }
}

fun productExceptSelf(nums: IntArray): IntArray {
    val ans = IntArray(nums.size)
    ans[0] = 1
    for (i in 1 until nums.size) {
        ans[i] = ans[i - 1] * nums[i - 1]
    }
    var R = 1
    for (i in nums.lastIndex downTo 0) {
        ans[i] = ans[i] * R
        R *= nums[i]
    }
    return ans
}
