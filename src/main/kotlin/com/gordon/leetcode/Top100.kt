package org.example.com.gordon.leetcode

/**
 * 接雨水,
 * 左右指针,左指针指向最左边,右指针指向最右边. 那么所接的雨水最多是area = min(l,r)* (r-l)
 * 然后移动值较小的那个指针, 每次记录计算的area值,保留最大的值.
 * 当循环完时,返回记录的最大值
 */
fun maxArea(height: IntArray): Int {
    var l = 0
    var r = height.lastIndex
    var res = 0
    while (l < r) {
        val area = height[l].coerceAtMost(height[r]) * (r - l)
        res = res.coerceAtLeast(area)
        if (height[l] <= height[r]) {
            l++
        } else {
            r--
        }
    }
    return res
}