package org.example.com.gordon.leetcode

import java.util.*

private fun isValid(s: String): Boolean {
    val stack = LinkedList<Char>()
    val right = arrayOf(']', ')', '}')
    fun isMatch(l: Char, r: Char): Boolean {
        return l == '[' && r == ']'
                || l == '(' && r == ')'
                || l == '{' && r == '}'
    }
    for (c in s) {
        if (c in right) {
            if (stack.isEmpty() || !isMatch(stack.pop(), c)) {
                return false
            }
        } else {
            stack.push(c)
        }
    }
    return stack.isEmpty()
}

private fun removeDuplicates(s: String): String {
    val stack = LinkedList<Char>()
    for (c in s) {
        if (stack.isNotEmpty() && stack.peek() == c) {
            stack.pop()
        } else {
            stack.push(c)
        }
    }
    val sb = StringBuilder()
    for (c in stack) {
        sb.append(c)
    }
    return sb.reverse().toString()
}

private fun removeDuplicates2(s: String): String {
    val sb = StringBuilder()
    for (c in s) {
        if (sb.isNotEmpty() && sb.last() == c) {
            sb.deleteCharAt(sb.lastIndex)
        } else {
            sb.append(c)
        }
    }
    return sb.toString()
}

private fun evalRPN(tokens: Array<String>): Int {
    val stack = LinkedList<String>()
    val symbols = listOf("+", "-", "*", "/")
    fun isSymbol(s: String): Boolean {
        return s in symbols
    }
    for (token in tokens) {
        if (isSymbol(token)) {
            val second = stack.pop().toInt()
            val first = stack.pop().toInt()
            when (token) {
                symbols[0] -> {
                    stack.push((first + second).toString())
                }

                symbols[1] -> {
                    stack.push((first - second).toString())
                }

                symbols[2] -> {
                    stack.push((first * second).toString())
                }

                symbols[3] -> {
                    stack.push((first / second).toString())
                }
            }
        } else {
            stack.push(token)
        }
    }
    return stack.pop().toInt()
}

private fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
    if (nums.size == 1) {
        return nums
    }
    val result = IntArray(nums.size - k + 1)
    val queue = MyQueue()
    var index = 0
    repeat(k) {
        queue.add(nums[it])
    }
    result[index++] = queue.peek()
    for (i in k until nums.size) {
        queue.poll(nums[i - k])
        queue.add(nums[i])
        result[index++] = queue.peek()
    }
    return result
}

class MyQueue {
    val queue = LinkedList<Int>()

    fun add(value: Int) {
        while (queue.isNotEmpty() && value > queue.last()) {
            queue.removeLast()
        }
        queue.add(value)
    }

    fun peek(): Int {
        return queue.peek()
    }

    fun poll(value: Int) {
        if (queue.isNotEmpty() && queue.peek() == value) {
            queue.pop()
        }
    }
}

/**
 * 前k个高频元素
 */
private fun topKFrequent(nums: IntArray, k: Int): IntArray {
    val map = hashMapOf<Int, Int>()
    nums.forEach {
        map[it] = map.getOrDefault(it, 0) + 1
    }
    val pq = PriorityQueue<IntArray> { o1, o2 -> o1[1] - o2[1] }
    map.forEach { (key, v) ->
        val tmp = intArrayOf(key, v)
        pq.add(tmp)
        if (pq.size > k) {
            pq.poll()
        }
    }
    val result = IntArray(k)
    var index = k
    while (pq.isNotEmpty() && index > 0) {
        result[index--] = pq.poll()[1]
    }
    return result
}

/**
 * gpt优化后的代码
 */
private fun topKFrequent2(nums: IntArray, k: Int): IntArray {
    val map = hashMapOf<Int, Int>()
    nums.forEach { map[it] = map.getOrDefault(it, 0) + 1 }
    //按值进行降序排序
    val pq = PriorityQueue<Map.Entry<Int, Int>>(compareByDescending { it.value })
    pq.addAll(map.entries)

    val result = IntArray(k)
    //取出前k个元素
    for (i in 0 until k) {
        result[i] = pq.poll().key
    }

    return result
}