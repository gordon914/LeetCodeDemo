package org.example.com.gordon.leetcode.top100

/**
 * 第81题 爬楼梯
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */
fun climbStairs(n: Int): Int {
    if (n < 3) {
        return n
    }
    var first = 1
    var second = 2
    for (i in 3..n) {
        val curr = first + second
        first = second
        second = curr
    }
    return second
}

/**
 * 第82题 杨辉三角
 * 118. 杨辉三角
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 *
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 */
fun generate(numRows: Int): List<List<Int>> {
    val ans = mutableListOf<List<Int>>()
    for (i in 0 until numRows) {
        val row = mutableListOf<Int>()
        for (j in 0..i) {
            if (j == 0 || j == i) {
                row.add(1)
            } else {
                row.add(ans[i - 1][j - 1] + ans[i - 1][j])
            }
        }
        ans.add(row)
    }
    return ans
}

/**
 * 第83题 打家劫舍
 * 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * 示例 1：
 *
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 思路:
 * 假设dp[i]是能偷到的最大金额
 * 那么dp[i] = (dp[i-1],dp[i-2]+nums[i])
 */
fun rob(nums: IntArray): Int {
    if (nums.isEmpty()) {
        return 0
    }
    if (nums.size == 1) {
        return nums[0]
    }
    var first = nums[0]
    var second = maxOf(nums[0], nums[1])
    for (i in 2 until nums.size) {
        val curr = maxOf(second, first + nums[i])
        first = second
        second = curr
    }
    return second
}

/**
 * 第84题 完全平方数
 * 279. 完全平方数
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 *
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * 示例 1：
 *
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 *
 * 思路: 这是一个完全背包问题,从m个物品中,任意选取几个,使得它们的容量大小为n
 */
fun numSquares(n: Int): Int {
    val dp = IntArray(n + 1) { Int.MAX_VALUE }
    dp[0] = 0
    var i = 1
    while (i * i <= n) {
        var j = i * i
        while (j <= n) {
            dp[j] = minOf(dp[j], dp[j - i * i] + 1)
            j++
        }
        i++
    }
    return dp[n]
}

/**
 * 第85题 零钱兑换
 *
 */
fun coinChange(coins: IntArray, amount: Int): Int {
    val dp = IntArray(amount + 1) { Int.MAX_VALUE }
    dp[0] = 0
    for (i in coins.indices) { //先遍历物品
        for (j in coins[i]..amount) { //再遍历背包,背包的大小至少为coins[i]
            if (dp[j - coins[i]] != Int.MAX_VALUE) {
                //dp[j]表示兑换j,需要的最少的coin数,当选择当前从coin[i]时,它的值为dp[j-coins[i]]+1
                dp[j] = minOf(dp[j], dp[j - coins[i]] + 1)
            }
        }
    }
    return if (dp[amount] != Int.MAX_VALUE) {
        dp[amount]
    } else {
        -1
    }
}

/**
 * 第86题 单词拆分
 * 139. 单词拆分
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
 *
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 *
 * 思路:
 * 字典列表-->物品 可以选择多个
 * 字符串-->背包
 * 从物品中选取一个或者多个,拼凑成背包的容量,所以是个完全背包问题
 * 因为拼凑的结果是有顺序要求的,所以要先遍历背包,再遍历物品.
 */
fun wordBreak(s: String, wordDict: List<String>): Boolean {
    //定义dp[i] 表示以i结尾的子串是否可以由字典拼凑成
    //状态转移方程 如果[j,i)截取的子串在字典中,判断它是物品.并且dp[j]也为true.那么就有dp[i]=true
    val dp = BooleanArray(s.length + 1)
    dp[0] = true
    val set = wordDict.toHashSet()
    for (i in 1..s.length) {
        var j = 0
        while (j < i && !dp[i]) {
            if (dp[j] && set.contains(s.substring(j, i))) {
                dp[i] = true
            }
            j++
        }
    }
    return dp[s.length]
}

/**
 * 第87题 最长递增子序列
 * 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的
 * 子序列
 * 。
 *
 * 思路:
 * 动态规划
 * 1.定义dp[i]为以nums[i]结尾的子序列的最大长度
 * 2.初始值,每个dp的数组长度为1
 * 3.逻辑--> 如果nums[i]>nums[j] 则有dp[i] = max(dp[i],dp[j]+1)
 * 4.遍历所有的dp,求最大值.(再步骤3遍历时,用最大值来记录)
 *
 * tips: 如果求的是连续递增子序列,只需要一层遍历,
 * 逻辑-->比较的是与上一个元素进行比较,然后更新dp
 */
fun lengthOfLIS(nums: IntArray): Int {
    val dp = IntArray(nums.size) { 1 }
    var ans = 1 //这里的值是1,只有一个数时,不会执行下面的for循环
    for (i in 1 until nums.size) {
        for (j in 0 until i) {
            if (nums[i] > nums[j]) {
                dp[i] = maxOf(dp[j] + 1, dp[i])
            }
            ans = maxOf(ans, dp[i])
        }
    }
    return ans
}

/**
 * 第88题 乘积最大子数组
 * 152. 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续
 * 子数组
 * （该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 测试用例的答案是一个 32-位 整数。
 *
 * 示例 1:
 *
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 *
 * 思路:
 * 本题可以使用动态规划区解决.
 * 由于nums中存在负数,所以它与最长
 * 定义两个变量一个是imax,一个是imin分别表示以nums[i]结尾的乘积的最大值和最小值
 * 如果nums[i]<0 -->就交换imax和imin
 * 同时更新imax = max(imax*nums[i],nums[i]) //负数*最小值 才能使其乘积变得更大.
 * imin = min(imin*nums[i],nums[i]) //负数*最大值 会使得它的乘积变得更小
 *
 * 用变量ans记录历史最大值.
 *
 * 1*任意数为当前数本身.  所以初始imin和imax为最大值
 */
fun maxProduct(nums: IntArray): Int {
    var ans = Int.MIN_VALUE
    var imax = 1
    var imin = 1
    for (i in nums.indices) {
        if (nums[i] < 0) {
            val tmp = imax
            imax = imin
            imin = tmp
        }
        imax = maxOf(imax * nums[i], nums[i])
        imin = minOf(imin * nums[i], nums[i])
        ans = maxOf(ans, imax)
    }
    return ans
}