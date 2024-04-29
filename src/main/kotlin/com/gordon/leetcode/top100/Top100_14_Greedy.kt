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
        profit = maxOf(profit, prices[i] - lowestPrice)
        lowestPrice = minOf(lowestPrice, prices[i])
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
        //注意这一步不能少,提前判断是否可达
        // 如果最大的可达距离不能到达这个索引i,那么就不能完成
        if (i > maxReach) {
            return false
        }
        maxReach = maxOf(i + nums[i], maxReach)
        //如果提前得知能达到最后一个位置,就返回,避免冗余的迭代
        if (maxReach >= nums.lastIndex) {
            return true
        }
    }
    return true
}

/**
 * 第79题 跳跃游戏II
 * 跳跃游戏I,计算游戏结束需要跳跃的最小步数
 */
fun jump(nums: IntArray): Int {
    var currDistance = 0
    var nextReach = 0
    var ans = 0
    //只需遍历到倒数第二个位置即可,因为,假设当前刚好到达了倒数第二个位置,那么次数再加1就是到达最后一个位置了.
    for (i in 0 until nums.size - 1) {
        //记录历史可以跳跃的最大距离
        nextReach = maxOf(nextReach, i + nums[i])
        //如果当前所在的位置已经到达了当前所能跳跃的距离,就累加次数,同时更新当前所能跳跃的最大距离
        if (i == currDistance) {
            ans++
            currDistance = nextReach
        }
    }
    return ans
}

/**
 * 第80题 划分字母区间
 * 763. 划分字母区间
 * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
 *
 * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
 *
 * 返回一个表示每个字符串片段的长度的列表。
 *
 * 示例 1：
 * 输入：s = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。
 */
fun partitionLabels(s: String): List<Int> {
    val arr = IntArray(26)
    s.forEachIndexed { index, c ->
        arr[c - 'a'] = index
    }
    var start = 0
    var end = 0
    val ans = mutableListOf<Int>()
    for (i in s.indices) {
        end = maxOf(end, arr[s[i] - 'a'])
        if (i == end) {
            ans.add(end - start + 1)
            start = end + 1
        }
    }
    return ans
}