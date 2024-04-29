package org.example.com.gordon.leetcode.top100

import java.util.*

fun main() {
    val s = "3[a]2[bc]"
    debugDecodeStr(s)
    val s2 = "2[abc]3[cd]ef"
    debugDecodeStr(s2)
}

/**
 * 第69题 有效的括号
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 */
fun isValid(s: String): Boolean {
    val brackets = mapOf(
        ')' to '(',
        '}' to '{',
        ']' to '['
    )
    val stack = LinkedList<Char>()
    for (c in s) {
        if (c in brackets.values) {
            stack.push(c)
        } else {
            if (c in brackets.keys) {
                if (stack.isEmpty() || stack.pop() != brackets[c]) {
                    return false
                }
            }
        }
    }
    return stack.isEmpty()
}

/**
 * 第70题 最小栈
 */
class MinStack() {
    val stack = LinkedList<Int>()
    val topStack = LinkedList<Int>().apply {
        push(Int.MAX_VALUE)
    }

    fun push(`val`: Int) {
        stack.push(`val`)
        topStack.push(minOf(`val`, topStack.peek()))
    }

    fun pop() {
        stack.pop()
        topStack.pop()
    }

    fun top(): Int {
        return stack.peek()
    }

    fun getMin(): Int {
        return topStack.peek()
    }

}

/**
 * 第71题 字符串解码
 * 394. 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 * 示例 1：
 *
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 示例 2：
 *
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * 示例 3：
 *
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 */
fun decodeString(s: String): String {
    var index = 0
    val stack = LinkedList<String>()
    while (index < s.length) {
        val c = s[index]
        if (c.isDigit()) {
            val sb = StringBuilder()
            while (index < s.length && s[index].isDigit()) {
                sb.append(s[index])
                index++
            }
            stack.push(sb.toString())
        } else if (c == '[') {
            stack.push(c.toString())
            index++
        } else if (c.isLetter()) {
            val sb = StringBuilder()
            while (index < s.length && s[index].isLetter()) {
                sb.append(s[index])
                index++
            }
            stack.push(sb.toString())
        } else {
            index++
            val sb = StringBuilder()
            while (stack.peek() != "[") {
                sb.insert(0, stack.pop())
            }
            stack.pop()
            val num = stack.pop().toInt()
            val str = sb.toString()
            stack.push(str.repeat(num))
        }
    }
    val sb = StringBuilder()
    for (item in stack) {
        sb.insert(0, item)
    }
    return sb.toString()
}

private fun debugDecodeStr(s: String) {
    println("s=$s")
    decodeString(s)
    println()
}

/**
 * 第72题 每日温度
 *739. 每日温度
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，
 * 下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * 示例 2:
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * 示例 3:
 *
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 *
 * 思路: 使用单调栈记录元素的index
 * 这里的单调栈是大的元素在栈底,最小的元素在栈顶,它们是有序的
 * 当添加元素时,不满足连续时,就依次移除栈顶的元素,使其满足单调有序的顺序
 * 注意这里的技巧是存储元素所在的index
 * 在遍历数组时,就可以用i-preIndex就是下次更高的天数.
 */
fun dailyTemperatures(temperatures: IntArray): IntArray {
    val n = temperatures.size
    val stack = LinkedList<Int>()
    val ans = IntArray(n)
    for (i in 0 until n) {
        val temper = temperatures[i]
        while (stack.isNotEmpty() && temper > temperatures[stack.peek()]) {
            val preIndex = stack.pop()
            ans[preIndex] = i - preIndex
        }
        stack.push(i)
    }
    return ans
}

/**
 * 第73题 柱状图中最大的矩形
 * 84. 柱状图中最大的矩形
 * 相关标签
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
fun largestRectangleArea(heights: IntArray): Int {
    TODO()
}