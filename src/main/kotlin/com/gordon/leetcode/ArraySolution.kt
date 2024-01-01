package org.example.com.gordon.leetcode

/**
 * 移除指定元素
 */
fun removeElement(nums: IntArray, `val`: Int): Int {
    var position = 0
    var i = 0
    val size = nums.size
    while (i < size) {
        if (nums[i] != `val`) {
            nums[position++] = nums[i]
        }
        i++
    }
    return position
}

/**
 * 代码优化
 * 给数组赋值的操作变少了
 */
fun removeElement2(nums: IntArray, `val`: Int): Int {
    var left = 0
    var right = nums.size
    while (left < right) {
        if (nums[left] == `val`) {
            nums[left] = nums[--right]
        } else {
            left++
        }
    }
    return left
}

fun sortedSquares(nums: IntArray): IntArray {
    val newArray = IntArray(nums.size)
    var l = 0
    var r = nums.size - 1
    var index = nums.lastIndex
    while (l <= r) {
        if (nums[l] * nums[l] > nums[r] * nums[r]) {
            newArray[index--] = nums[l] * nums[l]
            l++
        } else {
            newArray[index--] = nums[r] * nums[r]
            r--
        }
    }
    return newArray
}

fun minSubArrayLen(target: Int, nums: IntArray): Int {
    var sum = 0
    var i = 0
    var j = 0
    val r = nums.size
    var result = Int.MAX_VALUE
    while (j < r) {
        println("sum = $sum")
        sum += nums[j]
        j++
        while (sum >= target && j >= i) {
            println("sum=$sum,i=$i,j=$j")
            result = minOf(result, j - i)
            sum -= nums[i]
            i++
        }
    }
    return if (result == Int.MAX_VALUE) 0 else result
}

fun generateMatrix(n: Int): Array<IntArray> {
    val arr = Array(n) { IntArray(n) } //二维数组的定义
    var i: Int
    var j: Int
    var count = 1
    var loop = 0
    var start = 0
    while (loop++ < n / 2) {
        j = start //临时变量的定义
        while (j < n - loop) {
            arr[start][j] = count++
            j++
        }
        i = start
        while (i < n - loop) {
            arr[i][j] = count++
            i++
        }
        while (j >= loop) {
            arr[i][j] = count++
            j--
        }
        while (i >= loop) {
            arr[i][j] = count++
            i--

        }
        start++
    }
    if (n % 2 != 0) {
        arr[start][start] = count
    }
    return arr
}

fun main() {
    val nums = intArrayOf(2, 3, 1, 2, 4, 3)
    minSubArrayLen(7, nums)
}