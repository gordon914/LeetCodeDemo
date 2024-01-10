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