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