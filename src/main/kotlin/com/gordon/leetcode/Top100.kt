package org.example.com.gordon.leetcode

/**
 * 1. 两数之和
 */
private fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = HashMap<Int, Int>()
    nums.forEachIndexed { index, value ->
        if (map.contains(value)) {
            return intArrayOf(map.getOrDefault(value, 0), index)
        } else {
            map.put(target - value, index)
        }
    }
    return intArrayOf()
}



/**
 * 2.字母异位词分组
 * 对字符串进行字符排序,那么字母异位词就是相同的key了.
 * 因为key对应多个值,用List来存储.
 * 所以使用Map<String,List<String>>存储结果
 * 注意,map先取出默认的空列表,然后再put进去,下次取出来就不是空的List了
 */
private fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val map = hashMapOf<String, ArrayList<String>>()
    strs.forEach {
        val chars = it.toCharArray()
        chars.sort()
        val key = String(chars)
        val list = map.getOrDefault(key, arrayListOf())
        list.add(it)
        map[key] = list
    }
    return map.values.toList()
}

/**
 * 3.最长连续序列
 */
private fun longestConsecutive(nums: IntArray): Int {
    val hashNum = hashSetOf<Int>()
    nums.forEach {
        hashNum.add(it)
    }
    var maxLen = 0
    for (num in hashNum) {
        if (!hashNum.contains(num - 1)) {
            var curr = num
            var len = 1
            while (hashNum.contains(curr + 1)) {
                curr += 1
                len++
            }
            maxLen = maxLen.coerceAtLeast(len)
        }
    }
    return maxLen
}

/**
 * 4.移动零
 */
private fun moveZeroes(nums: IntArray): Unit {
    var i = 0
    var j = 0
    while (j < nums.size) {
        if (nums[j] != 0) {
            nums[i++] = nums[j]
        }
        j++
    }
    while (i < nums.size) {
        nums[i] = 0
        i++
    }
}

/**
 * 4.移动零
 * 优化写法,如果数组中0比较多,用这种方法移动的数字就会比较少
 */
private fun moveZeroes2(nums: IntArray){
    var i = 0
    var j = 0
    while (j < nums.size) {
        if (nums[j] != 0) {
            val tmp = nums[i]
            nums[i] = nums[j]
            nums[j] = tmp
            i++
        }
        j++
    }
}

/**
 * 5.接雨水,
 * 左右指针,左指针指向最左边,右指针指向最右边. 那么所接的雨水最多是area = min(l,r)* (r-l)
 * 然后移动值较小的那个指针, 每次记录计算的area值,保留最大的值.
 * 当循环完时,返回记录的最大值
 */
private fun maxArea(height: IntArray): Int {
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