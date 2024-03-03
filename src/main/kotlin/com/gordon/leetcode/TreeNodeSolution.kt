package org.example.com.gordon.leetcode

import java.util.*
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

private fun isLeaf(node: TreeNode): Boolean {
    return node.left == null && node.right == null
}

/**
 * 路径总和,判断是否含有一条路径上节点的和为目标数
 */
private fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
    if (root == null) {
        return false
    }
    val remainingSum = targetSum - root.`val`
    if (isLeaf(root)) {
        return remainingSum == 0
    }
    return hasPathSum(root.left, remainingSum) || hasPathSum(root.right, remainingSum)
}

/**
 * 构建二叉树
 * 中序和后序
 *           4
 *      2       7
 *    1  3    6   8
 *  中序是 1-->2-->3-->4-->6-->7-->8
 *  后序是 1-->3-->2-->6-->8-->7-->4
 *  后序的最后一个点节点是根节点,
 *  中序根节点是中间节点,它的左边的个数等于后序左边的个数,右边的个数
 *
 */
fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
    val map = hashMapOf<Int, Int>()
    inorder.forEachIndexed { index, i ->
        map[i] = index
    }
    return findRoot(inorder, 0, inorder.size, postorder, 0, postorder.size, map)
}

private fun findRoot(
    inorder: IntArray,
    inL: Int,
    inR: Int,
    postorder: IntArray,
    postL: Int,
    postR: Int,
    map: Map<Int, Int>
): TreeNode? {
    if (inL >= inR || postL >= postR) {
        return null
    }
    val rootIndex = map[postorder[postR - 1]]
    val root = TreeNode(inorder[rootIndex!!])
    val leftLen = rootIndex - inL
    root.left = findRoot(
        inorder, inL, rootIndex, postorder, postL, postL + leftLen, map
    )
    root.right = findRoot(
        inorder, rootIndex + 1, inR, postorder, postL + leftLen, postR - 1, map
    )
    return root
}

/**
 * 合并二叉树
 * 首先判断二者为null的情况
 * 创建一个root节点,累加两个节点的值
 * root的left和right,使用递归方法的返回值赋值
 * 最后返回root
 */
private fun mergeTrees(root1: TreeNode?, root2: TreeNode?): TreeNode? {
    if (root1 == null) {
        return root2
    }
    if (root2 == null) {
        return root1
    }
    val root = TreeNode(root1.`val` + root2.`val`)
    root.left = mergeTrees(root1.left, root2.left)
    root.right = mergeTrees(root1.right, root2.right)
    return root
}

/**
 * 查找二叉搜索树中的值
 */
private fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
    if (root == null || root.`val` == `val`) {
        return root
    }
    if (root.`val` > `val`) return searchBST(root.left, `val`)
    return searchBST(root.right, `val`)
}

/**
 * 验证二叉树
 */
private fun isValidBST(root: TreeNode?): Boolean {
    return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE)
}

private fun isValidBST(node: TreeNode?, low: Long, high: Long): Boolean {
    if (node == null) {
        return true
    }
    if (node.`val` > high || node.`val` < low) {
        return false
    }
    return isValidBST(node.left, low, node.`val`.toLong()) && isValidBST(node.right, node.`val`.toLong(), high)
}

/**
 * BST中两个节点的最小绝对差值
 */
private fun getMinimumDifference(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }
    traversal(root)
    return result
}

/**
 * 中序遍历是有序的,记录上次的节点
 * 进行计算当前节点与上一个节点的差值,最后取历史中的最小值就好
 */
private fun traversal(root: TreeNode?) {
    if (root == null) {
        return
    }
    traversal(root.left)
    if (pre != null) {
        result = result.coerceAtMost(root.`val` - pre!!.`val`)
    }
    pre = root
    traversal(root.right)
}

private var pre: TreeNode? = null
private var result: Int = Int.MAX_VALUE

/**
 * 二叉搜索树中的众数
 */
private fun findMode(root: TreeNode?): IntArray {
    if (root == null) {
        return IntArray(0)
    }
    findModeT(root)
    return list.toIntArray()
}

/**
 * 查找所有的众数,就需要记录每个节点出现的次数
 * 使用中序遍历,就是有序的.可以使用一个变量来记录上次数的次数,
 * 并用一个maxCount来记录历史出现的最大次数
 * 因为需要统计所有的众数,需要一个列表来存储这个值.
 * 最后将列表转成数组即可
 */
private fun findModeT(root: TreeNode?) {
    if (root == null) {
        return
    }
    findModeT(root.left)
    val rootValue = root.`val`
    if (preTree == null || preTree!!.`val` != rootValue) {
        count = 1
    } else {
        count++
    }
    if (count > maxCount) {
        list.clear()
        list.add(rootValue)
        maxCount = count
    } else if (count == maxCount) {
        list.add(rootValue)
    }
    preTree = root
    findModeT(root.right)
}

private val list = mutableListOf<Int>()
private var preTree: TreeNode? = null
private var count: Int = 0
private var maxCount: Int = 0

/**
 * 二叉树的最近公共祖先
 */
private fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    if (root == null || root == p || root == q) {
        return root
    }
    val left = lowestCommonAncestor(root.left, p, q)
    val right = lowestCommonAncestor(root.right, p, q)
    if (left != null && right != null) {
        return root
    }
    if (left == null) {
        return right
    }
    return left
}

/**
 * 二叉搜索树中的插入操作
 */
private fun insertIntoBST(root: TreeNode?, `val`: Int): TreeNode? {
    if (root == null) {
        val node = TreeNode(`val`)
        return node
    }
    if (root.`val` > `val`) root.left = insertIntoBST(root.left, `val`)
    if (root.`val` < `val`) root.right = insertIntoBST(root.right, `val`)
    return root
}