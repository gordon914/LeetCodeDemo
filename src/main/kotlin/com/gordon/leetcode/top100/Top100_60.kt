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
    return buildBST(nums, 0, nums.size -1)
}

private fun buildBST(nums: IntArray, l: Int, r: Int): TreeNode? {
    if (l > r) {
        return null
    }
    val mid = (l + r) / 2
    val root = TreeNode(nums[mid])
    root.left = buildBST(nums, l, mid-1)
    root.right = buildBST(nums, mid + 1, r)
    return root
}