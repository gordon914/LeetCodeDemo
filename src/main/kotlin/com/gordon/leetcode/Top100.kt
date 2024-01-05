package org.example.com.gordon.leetcode

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