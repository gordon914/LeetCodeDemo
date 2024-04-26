package org.example.com.gordon.leetcode.top100


/**
 * 第4题 移动零
 * 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
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
 * 第6题 三数之和
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
 * 第7题 接雨水
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