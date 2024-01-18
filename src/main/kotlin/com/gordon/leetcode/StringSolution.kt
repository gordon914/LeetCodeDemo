package org.example.com.gordon.leetcode

fun reverseString(s: CharArray): Unit {
    var l = 0
    var r = s.lastIndex
    while (l < r) {
        val temp = s[l]
        s[l] = s[r]
        s[r] = temp
        l++
        r--
    }
}

fun reverseStr(s: String, k: Int): String {
    val arr = s.toCharArray()
    for (c in 0 until s.lastIndex step k * 2) {
        var l = c
        var r = (c + 1).coerceAtMost(s.lastIndex)
        while (l < r) {
            val temp = arr[l]
            arr[l] = arr[r]
            arr[r] = temp
            l++
            r--
        }
    }
    return String(arr)
}

fun reverseWords(s: String): String {
    val sb = removeSpace(s)
    reverse(sb, 0, sb.lastIndex)
    var start = 0
    var end = start + 1
    while (start < sb.length) {
        while (end < sb.length && sb[end] != ' ') {
            end++
        }
        reverse(sb, start, end - 1)
        start = end + 1
        end = start + 1
    }
    return sb.toString()
}

private fun removeSpace(s: String): StringBuilder {
    var start = 0
    var end = s.lastIndex
    while (s[start] == ' ') {
        start++
    }
    while (s[end] == ' ') {
        end--
    }
    val sb = StringBuilder()
    while (start <= end) {
        if (s[start] != ' ' || sb[sb.lastIndex] != ' ') {
            sb.append(s[start])
        }
        start++
    }
    return sb
}

private fun reverse(sb: StringBuilder, start: Int, end: Int) {
    var l = start
    var r = end
    while (l < r) {
        val temp = sb[l]
        sb[l] = sb[r]
        sb[r] = temp
        l++
        r--
    }
}

//前缀表（不减一）Java实现
fun strStr(haystack: String, needle: String): Int {
    if (needle.isEmpty()) return 0
    val next = IntArray(needle.length)
    getNext(next, needle)

    var j = 0
    for (i in haystack.indices) {
        //当不匹配时,前一个元素对应的前缀表中的值,
        while (j > 0 && needle[j] != haystack[i]) j = next[j - 1]
        if (needle[j] == haystack[i]) j++
        if (j == needle.length) return i - j + 1
    }
    return -1
}

/**
 * 构造前缀表
 */
private fun getNext(next: IntArray, s: String) {
    var j = 0
    next[0] = 0
    for (i in 1 until s.length) {
        while (j > 0 && s[i] != s[j]) {
            j = next[j - 1]
        }
        if (s[i] == s[j]) {
            j++
        }
        next[i] = j
    }
}

fun repeatedSubstringPattern(s: String): Boolean {
    return (s + s).indexOf(s, 1) != s.length
}
