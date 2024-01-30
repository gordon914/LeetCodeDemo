package org.example.com.gordon.leetcode

import java.util.Collections
import java.util.LinkedList

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun preorderTraversal(root: TreeNode?): List<Int> {
    val result = arrayListOf<Int>()
    preorder(root, result)
    return result
}

private fun preorder(node: TreeNode?, list: ArrayList<Int>) {
    if (node == null) {
        return
    }
    list.add(node.`val`)
    preorder(node.left, list)
    preorder(node.right, list)
}

/**
 * 前序遍历，中--左--右
 * 入栈的顺序，就是先右后左
 */
fun preorderTraversal2(root: TreeNode?): List<Int> {
    val result = arrayListOf<Int>()
    if (root == null) {
        return result
    }
    val stack = LinkedList<TreeNode>()
    stack.push(root)
    while (stack.isNotEmpty()) {
        val node = stack.pop()
        result.add(node.`val`)
        node.right?.let {
            stack.push(it)
        }
        node.left?.let {
            stack.push(it)
        }
    }
    return result
}

private fun inorderTraversal(root: TreeNode?): List<Int> {
    val result = arrayListOf<Int>()
    inorder(root, result)
    return result
}

private fun inorder(node: TreeNode?, list: ArrayList<Int>) {
    if (node == null) {
        return
    }
    inorder(node.left, list)
    list.add(node.`val`)
    inorder(node.right, list)
}

/**
 * 中序遍历,左-->中-->右
 * 用一个临时节点curr来记录当前遍历的节点
 */
private fun inorderTraversal2(root: TreeNode?): List<Int> {
    val result = arrayListOf<Int>()
    if (root == null) {
        return result
    }
    val stack = LinkedList<TreeNode>()
    var curr = root
    while (curr != null || stack.isNotEmpty()) {
        if (curr != null) {
            stack.push(curr)
            curr = curr.left
        } else {
            val node = stack.pop()
            result.add(node.`val`)
            curr = node.right
        }
    }
    return result
}

private fun postorderTraversal(root: TreeNode?): List<Int> {
    val result = arrayListOf<Int>()
    postorder(root, result)
    return result
}

private fun postorder(node: TreeNode?, list: ArrayList<Int>) {
    if (node == null) {
        return
    }
    postorder(node.left, list)
    postorder(node.right, list)
    list.add(node.`val`)
}

/**
 * 后序遍历   左-->右-->中
 * 使用前序遍历的方式 中-->左-->右  添加的过程中变成 中-->右-->左 (栈添加的顺序是先左入栈,再右入栈)
 * 最后再反转结果就是  左-->右-->中
 */
private fun postorderTraversal2(root: TreeNode?): List<Int> {
    val result = arrayListOf<Int>()
    if (root == null) {
        return result
    }
    val stack = LinkedList<TreeNode>()
    stack.push(root)
    while (stack.isNotEmpty()) {
        val node = stack.pop()
        result.add(node.`val`)
        node.left?.let { stack.push(it) }
        node.right?.let { stack.push(it) }
    }
    result.reverse()
    return result
}

private fun levelOrderBottom(root: TreeNode?): List<List<Int>> {
    val result = ArrayList<List<Int>>()
    if (root == null) {
        return result
    }
    val queue = LinkedList<TreeNode>().apply {
        offer(root)
    }
    while (queue.isNotEmpty()) {
        var size = queue.size
        val list = ArrayList<Int>()
        while (size > 0) {
            val node = queue.poll()
            list.add(node.`val`)
            node.left?.let { queue.offer(it) }
            node.right?.let { queue.offer(it) }
            size--
        }
        result.add(0, list) //逆序插入元素
    }
    return result
}

private fun rightSideView(root: TreeNode?): List<Int> {
    val result = mutableListOf<Int>()
    if (root == null) {
        return result
    }
    val queue = LinkedList<TreeNode>().apply {
        offer(root)
    }
    while (queue.isNotEmpty()) {
        val size = queue.size
        for (i in 0 until size) {
            val node = queue.poll()
            node.left?.let { queue.offer(it) }
            node.right?.let { queue.offer(it) }
            if (i == size - 1) {
                result.add(node.`val`)
            }
        }
    }
    return result
}

private fun averageOfLevels(root: TreeNode?): DoubleArray {
    if (root == null) {
        return doubleArrayOf(0.0)
    }
    val list = arrayListOf<Double>()
    val queue = LinkedList<TreeNode>().apply {
        add(root)
    }
    while (queue.isNotEmpty()) {
        val size = queue.size
        var sum = 0.0
        for (i in 0 until size) {
            val treeNode = queue.poll()
            sum+=treeNode.`val`
            treeNode.left?.let(queue::offer)
            treeNode.right?.let(queue::offer)
        }
        list.add(sum / size)
    }
    return list.toDoubleArray()
}