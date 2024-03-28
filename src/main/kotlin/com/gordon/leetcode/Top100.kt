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
private fun moveZeroes2(nums: IntArray) {
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

/**
 * 6.三数之和
 * 求合为0的三元数组
 */
private fun threeSum(nums: IntArray): List<List<Int>> {
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

/**
 * 7.接雨水
 */
private fun trap(height: IntArray): Int {
    var l = 0
    var r = height.lastIndex
    var maxLeft = 0
    var maxRight = 0
    var ans = 0
    while (l < r) {
        maxLeft = maxLeft.coerceAtLeast(height[l])
        maxRight = maxRight.coerceAtLeast(height[r])
        if (height[l] < height[r]) {
            ans += maxLeft - height[l]
            l++
        } else {
            ans += maxRight - height[r]
            r--
        }
    }
    return ans
}

/**
 * 最长无重复的子字符串的长度
 * 窗口左边是当前字符所在的index
 * 用hash表记录字符串中所有出现的字符
 * 右指针不断地往后移动
 *
 * 当移动窗口时，从窗口中删除最左边的字符
 */
fun lengthOfLongestSubstring(s: String): Int {
    if (s.isEmpty()) {
        return 0
    }
    var max = 0
    val hash = hashSetOf<Char>()
    var rp = -1
    for (i in s.indices) {
        if (i > 0) {
            hash.remove(s[i - 1])
        }
        while (rp + 1 < s.length && !hash.contains(s[rp + 1])) {
            hash.add(s[rp + 1])
            rp++
        }
        max = max.coerceAtLeast(rp - i + 1)
    }
    return max
}

/**
 * 找到字符串中所有的字母异位词
 * 固定窗口大小为p的长度
 * 统计窗口中各个字符的次数，用int【】来表示
 * 用left，和right记录窗口的位置
 * 如果窗口的内容和表示p窗口的内容相等，就找到了left
 */
fun findAnagrams(s: String, p: String): List<Int> {
    if (s.length < p.length) {
        return emptyList()
    }
    val result = mutableListOf<Int>()
    val sArr = IntArray(26)
    val pArr = IntArray(26)
    for (i in p.indices) {
        sArr[s[i] - 'a']++
        pArr[p[i] - 'a']++
    }
    if (sArr.contentEquals(pArr)) {
        result.add(0)
    }
    var left = 0
    var right = p.length - 1
    while (true) {
        sArr[s[left] - 'a']--
        left++
        right++
        if (right >= s.length) {
            break
        }
        sArr[s[right] - 'a']++
        if (sArr.contentEquals(pArr)) {
            result.add(left)
        }
    }
    return result
}

/**
 * 和为k的子数组
 * 1. 假设[0..i]数组的和pre[i],那么pre[i] = pre[i-1]+numbs[i]
 * 2. 对于从j到i的数组和为k,则有pre[i]-pre[j-1] = k,也即pre[j-1] = pre[i]-k
 * 3. 用pre记录从左往右,累计nums,那么以和为key,value是其次数.
 * 4. map[0] = 1 对于数组累加和为0时,表示它们的次数为1次
 * 遍历完数组,累加count即可
 * 5. 举例: 对于[1,2,3,4,5] k=7 当遍历到4时,pre = 10,那么只需要知道出现pre-k = 10-7 =3 的次数即可.
 * 那么之前出现和为3的key有几次呢?是2次. [1,2] 和[3].
 * 6. 所以统计的次数,就是统计曾经出现过 pre-k为key的个数,不满足的话,就是0次
 *
 */
private fun subarraySum(nums: IntArray, k: Int): Int {
    val map = mutableMapOf<Int, Int>()
    map[0] = 1
    var pre = 0
    var count = 0
    nums.forEach { i ->
        pre += i
        if (map.containsKey(pre - k)) {
            count += map.getOrDefault(pre - k, 0)
        }
        map[pre] = map.getOrDefault(pre, 0) + 1
    }
    return count
}

fun main() {
    findAnagrams("aa", "bb")
}