package org.example.com.gordon.leetcode


fun isAnagram(s: String, t: String): Boolean {
    val hash = IntArray(26) { 0 }
    s.forEach {
        hash[it - 'a']++
    }
    t.forEach {
        hash[it - 'a']--
    }
    return hash.all { it == 0 }
}

fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
    val hash = nums1.toHashSet()
    val resultHash = mutableSetOf<Int>()
    nums2.forEach {
        if (hash.contains(it)) {
            resultHash.add(it)
        }
    }
    return resultHash.toIntArray()
}

fun isHappy(n: Int): Boolean {
    val hashSet = mutableSetOf<Int>()
    var num = n
    while (num != 1) {
        if (hashSet.contains(num)) {
            return false
        }
        hashSet.add(num)
        var sum = 0
        while (num != 0) {
            val bi = num % 10
            sum += bi * bi
            num /= 10
        }
        num = sum
    }
    return true
}

/**
 * 只求满足条件的个数,而不是求所在的坐标,注意这个条件
 * 统计两数之和sum1的个数 用hash来记录
 * 然后另外两个数的和sum2用 0-sum2 = sum1 满足这个条件的个数从hash表中统计即可.
 */
fun fourSumCount(nums1: IntArray, nums2: IntArray, nums3: IntArray, nums4: IntArray): Int {
    var res = 0
    val map: MutableMap<Int, Int> = HashMap()

    //统计两个数组中的元素之和，同时统计出现的次数，放入map
    for (i in nums1) {
        for (j in nums2) {
            val sum = i + j
            map[sum] = map.getOrDefault(sum, 0) + 1
        }
    }

    //统计剩余的两个元素的和，在map中找是否存在相加为0的情况，同时记录次数
    for (i in nums3) {
        for (j in nums4) {
            res += map.getOrDefault(0 - i - j, 0)
        }
    }
    return res
}

fun canConstruct(ransomNote: String, magazine: String): Boolean {
    val hash = IntArray(26) { 0 }
    ransomNote.forEach {
        hash[it - 'a']++
    }
    for (c in magazine.toCharArray()) {
        if (hash[c - 'a'] > 0) {
            hash[c - 'a']--
        }
    }
    return hash.all { it == 0 }
}

/**
 * 排序,去重
 * 双指针,指向当前遍历的位置i的下一个位置和数组的末尾位置
 */
fun threeSum(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    nums.sort()
    for (i in nums.indices) {
        if (nums[i] > 0) {
            return result
        }
        if (i > 0 && nums[i] == nums[i - 1]) {
            continue
        }
        var l = i + 1
        var r = nums.lastIndex
        while (r > l) {
            val sum = nums[i] + nums[l] + nums[r]
            if (sum > 0) {
                r--
            } else if (sum < 0) {
                l++
            } else {
                result.add(listOf(nums[i], nums[l], nums[r]))
                while (r > l && nums[r] == nums[r - 1]) {
                    r--
                }
                while (r > l && nums[l] == nums[l + 1]) {
                    l++
                }
                r--
                l++
            }
        }
    }
    return result
}

fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    nums.sort()
    for (i in nums.indices) {
        if (nums[i] > 0 && nums[i] > target) {
            return result
        }
        if (i > 0 && nums[i] == nums[i - 1]) {
            continue
        }
        for (j in i + 1 until nums.size) {
            if (j > i + 1 && nums[j] == nums[j - 1]) {
                continue
            }
            var l = j + 1
            var r = nums.lastIndex
            while (r > l) {
                val sum: Long = (nums[i] + nums[j] + nums[l] + nums[r]).toLong()
                if (sum > target) {
                    r--
                } else if (sum < target) {
                    l++
                } else {
                    result.add(listOf(nums[i],nums[j], nums[l], nums[r]))
                    while (r > l && nums[r] == nums[r - 1]) {
                        r--
                    }
                    while (r > l && nums[l] == nums[l + 1]) {
                        l++
                    }
                    r--
                    l++
                }
            }
        }
    }
    return result
}