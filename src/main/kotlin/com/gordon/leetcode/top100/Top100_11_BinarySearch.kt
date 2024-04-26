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

