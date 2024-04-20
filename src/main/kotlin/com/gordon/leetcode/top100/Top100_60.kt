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