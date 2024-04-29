package org.example.com.gordon.leetcode.top100

import java.util.*

fun main() {
    val nums = intArrayOf(1, 1, 1, 2, 2, 3)
    topKFrequent(nums, 2)
}

/**
 * 第74题
 * 215. 数组中的第K个最大元素
 */
fun findKthLargest(nums: IntArray, k: Int): Int {
    //第k大元素,所在的位置应该是n-k,从小往大的数组中
    return quickSelect(nums, 0, nums.size - 1, nums.size - k)
}

/**
 * 这里的k是数组第k大元素所在的下标
 */
private fun quickSelect(nums: IntArray, l: Int, r: Int, k: Int): Int {
    //注意终止条件时l==r也就是分割后的数组中只剩下一个元素了,就是要查找的元素
    if (l == r) {
        return nums[l]
    }
    //使用快排的分割思想,找到分割元素所在的位置为j
    var i = l - 1
    var j = r + 1
    val pivot = nums[l]
    while (i < j) {
        do {
            i++
        } while (nums[i] < pivot)
        do {
            j--
        } while (nums[j] > pivot)
        if (i < j) {
            val tmp = nums[i]
            nums[i] = nums[j]
            nums[j] = tmp
        }
    }
    //如果查找的分割点所在的位置j比k大,说明目标元素在左边的数组中,所以在左边的数组中继续查找元素
    return if (k <= j) {
        quickSelect(nums, l, j, k)
    } else {
        //否则,在右边继续查找,这里的下标位置l为j+1
        quickSelect(nums, j + 1, r, k)
    }
}

fun findKthLargest2(nums: IntArray, k: Int): Int {
    val pq = PriorityQueue<Int>(k)
    for (element in nums) {
        pq.add(element)
        if (pq.size > k) {
            pq.poll()
        }
    }
    return pq.peek()
}

/**
 * 第75题 前 K 个高频元素
 * 347. 前 K 个高频元素
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 */
fun topKFrequent(nums: IntArray, k: Int): IntArray {
    val map = hashMapOf<Int, Int>()
    for (num in nums) {
        map[num] = map.getOrDefault(num, 0) + 1
    }
    val pq = PriorityQueue<Pair<Int, Int>> { a, b -> a.second - b.second }
    map.forEach { (key, value) ->
        if (pq.size == k) {
            if (pq.peek().second < value) {
                pq.poll()
                pq.add(key to value)
            }
        } else {
            pq.add(key to value)
        }
    }
    val ans = IntArray(k)
    var i = 0
    while (pq.isNotEmpty()) {
        ans[i++] = pq.poll().first
    }
    return ans
}

/**
 * 第76题 数据流的中位数
 * 中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
 *
 * 例如 arr = [2,3,4] 的中位数是 3 。
 * 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
 *
 * 思路: 将数据流中的元素分为两队
 * 左边的元素都是较小值
 * 右边的元素是较大值
 * 左边队使用大顶堆存储,那么堆顶的元素就是中位数
 * 右边使用小顶堆,堆顶的元素也是中位数
 * 保证左边的元素比右边的多,那么数组是奇数个时,那么左堆顶的元素就是最后求得的中位数
 * 否则取二者堆顶的平均值
 */
class MedianFinder() {

    private val leftHeap = PriorityQueue<Int> { a, b -> b - a }
    private val rightHeap = PriorityQueue<Int>()

    /**
     * 1.先往左边堆中添加元素
     * 2.再把左堆顶的最大元素添加到右堆顶中去
     * 3.如果右堆顶元素的个数比左堆顶元素个数多,就把右边最小的元素移到左边的堆去
     * 这样就保证了左堆顶的元素个数不会少于右堆顶的,并且左堆顶元素都比右堆顶的元素小
     */
    fun addNum(num: Int) {
        leftHeap.add(num)
        rightHeap.add(leftHeap.poll())
        if (rightHeap.size > leftHeap.size) {
            leftHeap.add(rightHeap.poll())
        }
    }

    fun findMedian(): Double {
        return if (leftHeap.size == rightHeap.size) {
            (leftHeap.peek() + rightHeap.peek()) / 2.0
        } else {
            leftHeap.peek().toDouble()
        }
    }

}