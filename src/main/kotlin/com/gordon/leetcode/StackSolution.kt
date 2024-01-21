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