package org.example.com.gordon.leetcode.think

/**
 * 组合总和III
 * 从1-9中选取k个数,使得它们的和为n
 */
private val combinePath = mutableListOf<Int>()
private val combineAns = mutableListOf<List<Int>>()

fun combinationSum3(k: Int, n: Int): List<List<Int>> {
    backCombine(n, k, 1, 0)
    return combineAns
}

private fun backCombine(targetSum: Int, k: Int, startIndex: Int, sum: Int) {
    if (combinePath.size == k) {
        if (targetSum == sum) {
            combineAns.add(ArrayList(combinePath))
        }
        return
    }
    for (i in startIndex..9) {
        combinePath.add(i)
        backCombine(targetSum, k, i + 1, sum + i)
        combinePath.removeAt(combinePath.size - 1)
    }
}

/**
 * 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，
 * 找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 */
fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
    val res = mutableListOf<List<Int>>()
    candidates.sort() // 先进行排序
    backtracking(res, mutableListOf(), candidates, target, 0, 0)
    return res
}

private fun backtracking(res: MutableList<List<Int>>, path: MutableList<Int>, candidates: IntArray, target: Int, sum: Int, idx: Int) {
    // 找到了数字和为 target 的组合
    if (sum == target) {
        res.add(ArrayList(path))
        return
    }

    for (i in idx until candidates.size) {
        // 如果 sum + candidates[i] > target 就终止遍历
        if (sum + candidates[i] > target) break
        path.add(candidates[i])
        //可以重复选取数字,所以纵向遍历时,只更新sum字段.注意这里用的是sum+候选值,方法执行完,会有一个回溯的过程
        backtracking(res, path, candidates, target, sum + candidates[i], i)
        path.removeAt(path.size - 1) // 回溯，移除路径 path 最后一个元素
    }
}


/**
 * 1. 分割回文字符串
 * 类似于组合问题,选取一个字符,再剩余的字符串中在选取一个.所以需要一个startIndex来记录,判断结束的位置
 * 判断[startIndex,i]的字符串是否为回文子串
 * 递归调用是纵向深度遍历,需要增加startIndex的值,横向遍历是for循环,增加i
 *
 */

private val result = mutableListOf<List<String>>()
private val path = mutableListOf<String>()

fun partition(s: String): List<List<String>> {
    result.clear()
    path.clear()
    backtracking(s, 0)
    return result
}

private fun backtracking(s: String, startIndex: Int) {
    if (startIndex >= s.length) {
        result.add(ArrayList(path))
        return
    }
    for (i in startIndex until s.length) {
        if (isPalindrome(s, startIndex, i)) {
            val str = s.substring(startIndex, i + 1)
            path.add(str)
            backtracking(s, i + 1)
            path.removeAt(path.size - 1)
        }
    }
}

private fun isPalindrome(s: String, start: Int, end: Int): Boolean {
    var i = start
    var j = end
    while (i < j) {
        if (s[i] != s[j]) {
            return false
        }
        i++
        j--
    }
    return true
}

/**
 * 复原ip地址
 * 判断一个数字组成的字符串是否为有效地ip地址
 * 使用回溯算法.
 * 每次选择一个字符,判断其有效性.如果满足,就递归往下层继续截取.
 *
 */
private val ipResult = mutableListOf<String>()

fun restoreIpAddresses(s: String): List<String> {
    //将s转成StringBuilder,方便插入删除元素
    val sb = StringBuilder(s)
    backTracking(sb, 0, 0)
    return ipResult
}

private fun backTracking(s: StringBuilder, startIndex: Int, dotCount: Int) {
    //当插入了三个圆点时,判断圆点后面的字符是否是有效地数字,如果是就是合法的ip,添加到结果集里去
    if (dotCount == 3) {
        if (isValid(s, startIndex, s.length - 1)) {
            ipResult.add(s.toString())
        }
        return
    }
    for (i in startIndex until s.length) {
        if (isValid(s, startIndex, i)) {
            s.insert(i + 1, '.')
            //因为插入了圆点,所以下次的起始位置为i+2,原点数量+1
            backTracking(s, i + 2, dotCount + 1)
            s.deleteCharAt(i + 1) //删除末尾的原点
        } else {
            break
        }
    }
}

// [start, end]
private fun isValid(s: StringBuilder, start: Int, end: Int): Boolean {
    if (start > end) return false
    if (s[start] == '0' && start != end) return false
    var num = 0
    for (i in start..end) {
        val digit = s[i] - '0'
        num = num * 10 + digit
        if (num > 255) return false
    }
    return true
}