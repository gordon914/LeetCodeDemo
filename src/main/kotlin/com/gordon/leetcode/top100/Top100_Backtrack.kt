package org.example.com.gordon.leetcode.top100

import java.util.*

/**
 * 第55题 全排列
 */
fun permute(nums: IntArray): List<List<Int>> {
    used = BooleanArray(nums.size)
    if (nums.isEmpty()) {
        return ans
    }
    dfs(nums)
    return ans
}

private val path = LinkedList<Int>()
private val ans = mutableListOf<MutableList<Int>>()
private lateinit var used: BooleanArray

private fun dfs(nums: IntArray) {
    if (nums.size == path.size) {
        ans.add(ArrayList(path))
        return
    }
    for (i in nums.indices) {
        if (used[i]) {
            continue
        }
        used[i] = true
        path.add(nums[i])
        dfs(nums)
        path.removeLast()
        used[i] = false
    }
}

/**
 * 第56题 子集
 * 78. 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的
 * 子集
 * （幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 */
fun subsets(nums: IntArray): List<List<Int>> {
    backtrack(nums, 0)
    return subsetAns
}

private val subsetAns = mutableListOf<List<Int>>()
private var subsetPath = mutableListOf<Int>()
private fun backtrack(nums: IntArray, startIndex: Int) {
    subsetAns.add(ArrayList(subsetPath))
    for (i in startIndex until nums.size) {
        subsetPath.add(nums[i])
        backtrack(nums, i + 1)
        subsetPath.removeLast()
    }
}

/**
 * 第57题 电话号码的字母组合
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例 1：
 *
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 */
fun letterCombinations(digits: String): List<String> {
    if (digits.isBlank()) {
        return emptyList()
    }
    backtrack2(digits, 0)
    return letterAns
}

private val letterMap = arrayOf(
    "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
)
private val sb = StringBuilder()
private val letterAns = mutableListOf<String>()

private fun backtrack2(digits: String, index: Int) {
    if (index == digits.length) {
        letterAns.add(sb.toString())
        return
    }
    val str = letterMap[digits[index] - '0']
    for (c in str) {
        sb.append(c)
        backtrack2(digits, index + 1)
        sb.deleteCharAt(sb.length - 1)
    }
}

/**
 * 第58题 组合总和
 * 39. 组合总和
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 */
fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
    if (candidates.isEmpty()) {
        return emptyList()
    }
    val ans = mutableListOf<List<Int>>()
    val path = mutableListOf<Int>()
    candidates.sort()
    combination(candidates,target,0,ans,path)
    return ans
}
private fun combination(candidates: IntArray,target: Int,startIndex:Int,ans:MutableList<List<Int>>,path:MutableList<Int>){
    if (target < 0) {
        return
    }
    if (target == 0) {
        ans.add(ArrayList(path))
        return
    }
    for (i in startIndex until candidates.size) {
        val candidate = candidates[i]
        if (target - candidate<0) {
            break
        }
        path.add(candidate)
        combination(candidates,target-candidate,i,ans,path)
        path.removeLast()
    }
}

/**
 * 使用kotlin特性对上述代码进行优化
 */
fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
    if (candidates.isEmpty()) {
        return emptyList()
    }
    val ans = mutableListOf<List<Int>>()
    val path = mutableListOf<Int>()
    candidates.sort()
    fun findCombination(remindingTarget:Int,startIndex:Int){
        if (remindingTarget == 0) {
            ans.add(ArrayList(path))
            return
        }
        for (i in startIndex until candidates.size) {
            val candidate = candidates[i]
            if (target - candidate < 0) {
                break
            }
            path.add(candidate)
            findCombination(target-candidate,i)
            path.removeLast()
        }
    }
    findCombination(target,0)
    return ans
}