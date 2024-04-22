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

/**
 * 第48题 路径总和 III
 * 437. 路径总和 III
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 *
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 */
fun pathSum(root: TreeNode?, targetSum: Int): Int {
    if (root == null) {
        return 0
    }
    val prefix = mutableMapOf(0 to 1)
    return dfs(root,prefix,0,targetSum)
}
private fun dfs(root: TreeNode?,prefix:MutableMap<Int,Int>,curr:Int,targetSum: Int):Int{
    if (root == null) {
        return 0
    }
    val newCurr = curr+root.`val`
    var ans = prefix.getOrDefault(newCurr-targetSum,0)
    prefix[newCurr] = prefix.getOrDefault(newCurr,0)+1
    ans+= dfs(root.left,prefix, newCurr, targetSum)
    ans+= dfs(root.right,prefix,newCurr,targetSum)
    prefix[newCurr] = prefix.getOrDefault(newCurr,0)-1
    return ans
}

/**
 * 第49题 二叉树的最近公共祖先
 * 236. 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）
 *
 * 思路: 本题需要使用个后续遍历,才能找到最近的公共祖先节点
 * 1.递归的结束条件是 节点为空 或者 节点==p || 节点==q
 * 2.左右子树的递归需要有返回值
 * 3.如果left或者right有一个为空就返回另一个节点
 * 4.都不为空,说明当前节点就是最近的公共祖先
 */
fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    if (root == null||root==p||root==q) {
        return root
    }
    val left = lowestCommonAncestor(root.left,p,q)
    val right = lowestCommonAncestor(root.right,p,q)
    if (left == null) {
        return right
    }
    if (right == null) {
        return left
    }
    return root
}

/**
 * 第50题 二叉树中的最大路径和
 * 124. 二叉树中的最大路径和
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。
 * 同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 *
 * 路径和 是路径中各节点值的总和。
 *
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 * 思路:
 * 1.使用后序遍历
 * 2.记录每个节点的最大贡献值,--所谓最大贡献值也就是以当前节点为起始位置的任意路径
 * 3.所以贡献值的大小就是当前节点的值+左右最大贡献值中的较大值
 *
 * 最大的路径和就是 当前节点的值+max(left)+max(right) 最大贡献值最小为0,因为不贡献或者当前节点的贡献是负数时,可以不在路径中
 */
fun maxPathSum(root: TreeNode?): Int {
    maxGain(root)
    return maxPathSum
}
private var maxPathSum = Int.MIN_VALUE
private fun maxGain(node: TreeNode?):Int{
    if (node == null) {
        return 0
    }
    val left = maxGain(node.left).coerceAtLeast(0)
    val right = maxGain(node.right).coerceAtLeast(0)
    val pathSum = node.`val`+left+right
    maxPathSum = maxPathSum.coerceAtLeast(pathSum)
    return node.`val`+ maxOf(left,right)
}