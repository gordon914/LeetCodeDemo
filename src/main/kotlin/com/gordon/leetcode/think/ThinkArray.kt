package org.example.com.gordon.leetcode.think

fun search(nums: IntArray, target: Int): Int {
    var l = 0
    var r = nums.lastIndex
    while (l <= r) {
        val mid = l + (r - l) / 2
        if (nums[mid] == target) {
            return mid
        }
        if (nums[mid] > target) {
            r = mid - 1 //如果是左闭右开,这里就不需要-1
        } else {
            l = mid + 1
        }
    }
    return -1
}

fun searchInsert(nums: IntArray, target: Int): Int {
    var l = 0
    var r = nums.lastIndex
    while (l <= r) {
        val mid = l + (r - l) / 2
        if (nums[mid] == target) {
            return mid
        }
        if (nums[mid] > target) {
            r = mid - 1
        } else {
            l = mid + 1
        }
    }
    return r + 1
}