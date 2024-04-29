package org.example.com.gordon.leetcode.top100

fun main() {
    val nums = intArrayOf(1, 3, 5, 6)
    val insert = searchInsert(nums, 4)
    println("insert = $insert")
}

/**
 * 第 63题 搜索插入位置
 * 35. 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * 示例 1:
 *
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 *
 * 思路: 利用循环不等式
 * 虽然是简单题这里有几个易错点:
 * 1.l<=r 等于号不能少
 * 2.mid是索引,不要把它的值求出来了
 * 3.移动l和r时,不要写成了++,而是赋值为mid的加减
 * 4.返回值是l
 */
fun searchInsert(nums: IntArray, target: Int): Int {
    var l = 0
    var r = nums.lastIndex
    var printHead = false
    while (l <= r) {
        val mid = l + (r - l) / 2
        if (!printHead) {
            println("round\tmid\tl\tr")
            printHead = true
        }
        println("before\t$mid\t$l\t$r")
        if (nums[mid] == target) {
            return mid
        } else if (nums[mid] > target) {
            r = mid - 1
        } else {
            l = mid + 1
        }
        println("after\t$mid\t$l\t$r")
    }
    return l
}

/**
 * 第64题 搜索二维矩阵
 * 74. 搜索二维矩阵
 * 给你一个满足下述两条属性的 m x n 整数矩阵：
 *
 * 每行中的整数从左到右按非严格递增顺序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 * 示例 1：
 *
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 * 思路: 整体是有序的,对二维数组进行一维的二分查找
 * 中间位置计算当前的行列坐标是 mid/n = 行  mid%n = 列
 * 循环不等式 low <=high 因为high=lastIndex,可以取右边的坐标 如果少了=,就通过不了了.下面的写法
 */
private fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
    val m = matrix.size
    val n = matrix[0].size
    var low = 0
    var high = m * n - 1
    while (low <= high) {
        val mid = low + (high - low) / 2
        val x = matrix[mid / n][mid % n]
        if (x == target) {
            return true
        } else if (x > target) {
            high = mid - 1
        } else {
            low = mid + 1
        }
    }
    return false
}

/**
 * 第65题 在排序数组中查找元素的第一个和最后一个位置
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 *
 * 示例 1：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 *
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 */
fun searchRange(nums: IntArray, target: Int): IntArray {
    fun find(nums: IntArray, target: Int, isLeft: Boolean = true): Int {
        var l = 0
        var r = nums.lastIndex
        var ans = nums.size
        while (l <= r) {
            val mid = l + (r - l) / 2
            if (nums[mid] > target || (isLeft && nums[mid] >= target)) {
                ans = mid
                r = mid - 1
            } else {
                l = mid + 1
            }
        }
        return ans
    }

    val first = find(nums, target)
    val last = find(nums, target, false)
    if (first <= last && last < nums.size && nums[first] == target && nums[last] == target) {
        return intArrayOf(first, last)
    }
    return intArrayOf(-1, -1)
}

/**
 * 第66题 搜索旋转排序数组
 * 33. 搜索旋转排序数组
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 *
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k],
 * nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如，
 * [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 *
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 *
 * 示例 1：
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 示例 2：
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：nums = [1], target = 0
 * 输出：-1
 */
fun search(nums: IntArray, target: Int): Int {
    var l = 0
    var r = nums.lastIndex
    while (l <= r) {
        val mid = (l + r) / 2
        if (nums[mid] == target) {
            return mid
        }
        //判断用的是第一个元素,而不是下标l
        if (nums[0] <= nums[mid]) {
            if (nums[0] <= target && target <= nums[mid]) {
                r = mid - 1
            } else {
                l = mid + 1
            }
        } else {
            if (nums[mid] < target && target <= nums.last()) {
                l = mid + 1
            } else {
                r = mid - 1
            }
        }
    }
    return -1
}

/**
 * 第67题 寻找旋转排序数组中的最小值
 * 153. 寻找旋转排序数组中的最小值
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 *
 * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 *
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,4,5,1,2]
 * 输出：1
 * 解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
 * 示例 2：
 *
 * 输入：nums = [4,5,6,7,0,1,2]
 * 输出：0
 * 解释：原数组为 [0,1,2,4,5,6,7] ，旋转 3 次得到输入数组。
 * 示例 3：
 *
 * 输入：nums = [11,13,15,17]
 * 输出：11
 * 解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
 *
 */
fun findMin(nums: IntArray): Int {
    var l = 0
    var r = nums.lastIndex
    while (l < r) {
        val mid = (l + r) / 2
        if (nums[mid] < nums[r]) {
            r = mid
        } else {
            l = mid + 1
        }
    }
    return nums[l]
}