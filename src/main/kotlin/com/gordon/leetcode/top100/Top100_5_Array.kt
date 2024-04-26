package org.example.com.gordon.leetcode.top100


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
