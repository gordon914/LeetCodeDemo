package org.example.com.gordon.leetcode.top100

/**
 * 第77题
 * 121. 买卖股票的最佳时机
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 *
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 *
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 */
fun maxProfit(prices: IntArray): Int {
    if (prices.isEmpty()) {
        return 0
    }
    var profit = 0
    var lowestPrice = prices[0]
    for (i in 1 until prices.size) {
        profit = maxOf(profit,prices[i]-lowestPrice)
        lowestPrice = minOf(lowestPrice,prices[i])
    }
    return profit
}

/**
 * 第78题
 * 55. 跳跃游戏
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 * 示例 1：
 *
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 */
fun canJump(nums: IntArray): Boolean {
    var maxReach = 0
    for (i in nums.indices) {
        // 如果最大的可达距离不能到达这个索引i,那么就不能完成
        if (i > maxReach) {
            return false
        }
        maxReach = maxOf(i+nums[i],maxReach)
        //如果提前得知能达到最后一个位置,就返回,避免冗余的迭代
        if (maxReach >= nums.lastIndex) {
            return true
        }
    }
    return true
}