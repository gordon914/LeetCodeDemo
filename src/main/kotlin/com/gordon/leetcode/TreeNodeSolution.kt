package org.example.com.gordon.leetcode

import java.util.LinkedList

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun preorderTraversal(root: TreeNode?): List<Int> {
    val result = arrayListOf<Int>()
    preorder(root,result)
    return result
}

private fun preorder(node: TreeNode?,list:ArrayList<Int>){
    if (node == null) {
        return
    }
    list.add(node.`val`)
    preorder(node.left,list)
    preorder(node.right,list)
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