package org.example.com.gordon.leetcode.top100

import org.example.com.gordon.leetcode.TreeNode
import java.util.*

/**
 * 第41题 二叉树的层序遍历
 * 102. 二叉树的层序遍历
 */
fun levelOrder(root: TreeNode?): List<List<Int>> {
    val ans = mutableListOf<List<Int>>()
    if (root == null) {
        return ans
    }
    val queue = LinkedList<TreeNode>()
    queue.add(root)
    while (queue.isNotEmpty()) {
        val size = queue.size
        val levelList = mutableListOf<Int>()
        for (i in 0 until size) {
            val node = queue.poll()
            levelList.add(node.`val`)
            node.left?.let {
                queue.add(it)
            }
            node.right?.let {
                queue.add(it)
            }
        }
        ans.add(levelList)
    }
    return ans
}

/**
 * 第42题 将有序数组转换为二叉搜索树
 * 108. 将有序数组转换为二叉搜索树
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 平衡 二叉搜索树。
 */
fun sortedArrayToBST(nums: IntArray): TreeNode? {
    return buildBST(nums, 0, nums.size - 1)
}

private fun buildBST(nums: IntArray, l: Int, r: Int): TreeNode? {
    if (l > r) {
        return null
    }
    val mid = (l + r) / 2
    val root = TreeNode(nums[mid])
    root.left = buildBST(nums, l, mid - 1)
    root.right = buildBST(nums, mid + 1, r)
    return root
}

/**
 * 第43题 验证二叉树
 * 每个节点都比它的左子结点大,比右子节点小
 * 思路: 使用Long的min和max,防止Int越界.
 * 空节点返回true
 * 递归过程先判断当前节点的值是否在最小值和最大值之间.不满足返回false
 * 递归左子树时,最大值就是当前节点的值,
 * 递归右子树时,最小值就是当前节点的值
 */
fun isValidBST(root: TreeNode?): Boolean {
    return verifyTree(root, Long.MIN_VALUE, Long.MAX_VALUE)
}

private fun verifyTree(node: TreeNode?, min: Long, max: Long): Boolean {
    if (node == null) {
        return true
    }
    if (node.`val` <= min || node.`val` >= max) {
        return false
    }
    return verifyTree(node.left, min, node.`val`.toLong()) && verifyTree(node.right, node.`val`.toLong(), max)
}

/**
 * 第44题 二叉搜索树中第K小的元素
 * 230. 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 */
fun kthSmallest(root: TreeNode?, k: Int): Int {
    if (root == null) {
        return 0
    }
    val leftCount = getCount(root.left)
    // 注意这里是>=k,少了=就过不了,因为等于k时,在左子树中也能找到
    if (leftCount >= k) {
        return kthSmallest(root.left, k)
    } else if (leftCount + 1 == k) {
        return root.`val`
    } else {
        return kthSmallest(root.right, k - leftCount - 1)
    }
}

private fun getCount(node: TreeNode?): Int {
    if (node == null) {
        return 0
    }
    return getCount(node.left) + getCount(node.right) + 1
}

/**
 * 第45题 二叉树的右视图
 * 199. 二叉树的右视图
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 */
fun rightSideView(root: TreeNode?): List<Int> {
    val ans: MutableList<Int> = mutableListOf()
    if (root == null) {
        return ans
    }
    val queue = LinkedList<TreeNode>()
    queue.add(root)
    while (queue.isNotEmpty()) {
        val size = queue.size
        for (i in 0 until size) {
            val node = queue.poll()
            if (i == size - 1) {
                ans.add(node.`val`)
            }
            node.left?.let {
                queue.add(it)
            }
            node.right?.let {
                queue.add(it)
            }
        }
    }
    return ans
}

/**
 * 第46题 二叉树展开为链表
 * 114. 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 *
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 */
fun flatten(root: TreeNode?): Unit {
    var curr = root
    while (curr != null) {
        if (curr.left != null) {
            //下一个要遍历的节点
            val next = curr.left
            var pre = next
            //找到左节点最右边的子节点,也就是前驱节点
            while (pre?.right != null) {
                pre = pre.right
            }
            //前驱节点的右节点就是当前节点的右节点
            pre?.right = curr.right
            //左节点置空
            curr.left = null
            //右节点指向下一个节点
            curr.right = next
        }
        curr = curr.right
    }
}

/**
 * 第47题 从前序与中序遍历序列构造二叉树
 * 105. 从前序与中序遍历序列构造二叉树
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 */
fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
    inorder.forEachIndexed { index, i ->
        nodeMap[i] = index
    }
    return buildTree(preorder, 0, preorder.size, inorder, 0, inorder.size)
}

private val nodeMap = mutableMapOf<Int, Int>()
private fun buildTree(preorder: IntArray, pl: Int, pr: Int, inorder: IntArray, il: Int, ir: Int): TreeNode? {
    if (pl >= pr || il > ir) {
        return null
    }
    val midNodeValue = preorder[pl]
    val pos = nodeMap[midNodeValue]!!
    val leftSum = pos - il
    val node = TreeNode(midNodeValue)
    node.left = buildTree(preorder, pl + 1, pl + leftSum + 1, inorder, il, pos)
    node.right = buildTree(preorder, pl + leftSum + 1, pr, inorder, pos + 1, ir)
    return node
}