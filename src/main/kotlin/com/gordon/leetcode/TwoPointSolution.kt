package org.example.com.gordon.leetcode

/**
 * 用右边的值,不断的覆盖当前遍历到的左边指针对应的值
 */
private fun removeElement(nums: IntArray, `val`: Int): Int {
    var l = 0
    var r = nums.size
    while (l < r) {
        if (nums[l] == `val`) {
            nums[l] = nums[--r]
        } else {
            l++
        }
    }
    return l
}

private fun reverseString2(s: CharArray): Unit {
    var l = 0
    var r = s.lastIndex
    while (l < r) {
        val tmp = s[l]
        s[l] = s[r]
        s[r] = tmp
        l++
        r--
    }
}