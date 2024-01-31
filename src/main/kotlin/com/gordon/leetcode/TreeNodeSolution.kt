package org.example.com.gordon.leetcode

import java.util.LinkedList
import kotlin.math.abs

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Node(var `val`: Int) {
    var left: Node? = null
    var right: Node? = null
    var next: Node? = null
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
            sum += treeNode.`val`
            treeNode.left?.let(queue::offer)
            treeNode.right?.let(queue::offer)
        }
        list.add(sum / size)
    }
    return list.toDoubleArray()
}

private fun largestValues(root: TreeNode?): List<Int> {
    if (root == null) {
        return emptyList()
    }
    val result = mutableListOf<Int>()
    val queue = LinkedList<TreeNode>().apply {
        add(root)
    }
    while (queue.isNotEmpty()) {
        val size = queue.size
        var max = Int.MIN_VALUE
        for (i in 0 until size) {
            val treeNode = queue.poll()
            max = max.coerceAtLeast(treeNode.`val`)
            treeNode.left?.let(queue::offer)
            treeNode.right?.let(queue::offer)
        }
        result.add(max)
    }
    return result
}

private fun connect(root: Node?): Node? {
    if (root == null) {
        return null
    }
    val queue = LinkedList<Node>().apply {
        offer(root)
    }
    while (queue.isNotEmpty()) {
        val size = queue.size
        for (i in 0 until size) {
            val node = queue.poll()
            if (i < size - 1) {
                node.next = queue.peek()
            }
            val left = node.left
            val right = node.right
            left?.let(queue::offer)
            right?.let(queue::offer)
        }
    }
    return root
}

private fun maxDepth(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }
    val queue = LinkedList<TreeNode>()
        .apply { offer(root) }
    var level = 0
    while (queue.isNotEmpty()) {
        val size = queue.size
        level++
        for (i in 0 until size) {
            val treeNode = queue.poll()
            treeNode.left?.let(queue::offer)
            treeNode.right?.let(queue::offer)
        }
    }
    return level
}

private fun minDepth(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }
    val queue = LinkedList<TreeNode>()
        .apply { offer(root) }
    var level = 0
    while (queue.isNotEmpty()) {
        val size = queue.size
        level++
        for (i in 0 until size) {
            val treeNode = queue.poll()
            if (treeNode.left == null && treeNode.right == null) {
                return level
            }
            treeNode.left?.let(queue::offer)
            treeNode.right?.let(queue::offer)
        }
    }
    return level
}

/**
 * 完全二叉树最有最后一层可以不满节点
 * 满二叉树的个数是 2的n次方 -1
 */
private fun countNodes(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }
    var leftNode = root.left
    var rightNode = root.right
    var leftLen = 0
    var rightLen = 0
    while (leftNode != null) {
        leftNode = leftNode.left
        leftLen++
    }
    while (rightNode != null) {
        rightNode = rightNode.right
        rightLen++
    }
    if (leftLen == rightLen) {
        return 2 shl leftLen - 1
    }
    return 1 + countNodes(root.left) + countNodes(root.right)
}

private fun isBalanced(root: TreeNode?): Boolean {
    return getHeight(root) != -1
}

private fun getHeight(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }
    val leftHeight = getHeight(root.left)
    if (leftHeight == -1) {
        return -1
    }
    val rightHeight = getHeight(root.right)
    if (rightHeight == -1) {
        return -1
    }
    if (abs(leftHeight - rightHeight) > 1) {
        return -1
    }
    return leftHeight.coerceAtLeast(rightHeight) + 1
}

private fun binaryTreePaths(root: TreeNode?): List<String> {
    val result = mutableListOf<String>()
    if (root == null) {
        return result
    }
    val path = mutableListOf<TreeNode>()
    travers(root, path, result)
    return result
}

private fun travers(node: TreeNode, path: MutableList<TreeNode>, result: MutableList<String>) {
    path.add(node)
    if (node.left == null && node.right == null) {
        val sb = StringBuilder()
        for (i in 0 until path.lastIndex) {
            sb.append(path[i].`val`)
            sb.append("->")
        }
        sb.append(path.last().`val`)
        result.add(sb.toString())
        return
    }
    if (node.left != null) {
        travers(node.left!!, path, result)
        path.removeLast()
    }
    if (node.right != null) {
        travers(node.right!!, path, result)
        path.removeLast()
    }
}

/**
 * 翻转二叉树
 */
private fun invertTree(root: TreeNode?): TreeNode? {
    if (root == null) {
        return root
    }
    swapTree(root)
    invertTree(root.left)
    invertTree(root.right)
    return root
}

private fun swapTree(node: TreeNode) {
    val temp = node.left
    node.left = node.right
    node.right = temp
}

private fun invertTree2(root: TreeNode?): TreeNode? {
    if (root == null) {
        return null
    }
    val queue = LinkedList<TreeNode>().apply {
        offer(root)
    }
    while (queue.isNotEmpty()) {
        val size = queue.size
        for (i in 0 until size) {
            val treeNode = queue.poll()
            swapTree(treeNode)
            treeNode.left?.let(queue::offer)
            treeNode.right?.let(queue::offer)
        }
    }
    return root
}

/**
 * 左叶子之和
 * 判断是左叶子,需要用父节点. 父节点的左节点不为null,并且左节点的左右子节点为null.
 * 递归的左右节点需要有返回值
 * 单层逻辑是判断为左叶子节点时,用mid表示这个值,然后累加左,右和mid的值.
 * 本例使用递归算法-->后续遍历
 */
private fun sumOfLeftLeaves(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }
    val leftValue = sumOfLeftLeaves(root.left)
    val rightValue = sumOfLeftLeaves(root.right)
    var mid = 0
    if (root.left != null && root.left!!.left == null && root.left!!.right == null) {
        mid = root.left!!.`val`
    }
    return leftValue + rightValue + mid
}

