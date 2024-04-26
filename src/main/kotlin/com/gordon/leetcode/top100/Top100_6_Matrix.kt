package org.example.com.gordon.leetcode.top100


/**
 * 第18题 矩阵置零
 * 73. 矩阵置零
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 */
fun setZeroes(matrix: Array<IntArray>): Unit {
    val m = matrix.size
    val n = matrix[0].size
    var firstRowHas0 = false
    var firstColHas0 = false
    //标记第一列中是否有0
    for (i in 0 until m) {
        if (matrix[i][0] == 0) {
            firstColHas0 = true
            break
        }
    }
    //标记第一行中是否有0
    for (j in 0 until n) {
        if (matrix[0][j] == 0) {
            firstRowHas0 = true
            break
        }
    }
    //从第二行第二列开始遍历,如果存在0,就把对应的行和列的第一个元素标记为0
    for (i in 1 until m) {
        for (j in 1 until n) {
            if (matrix[i][j] == 0) {
                matrix[i][0] = 0
                matrix[0][j] = 0
            }
        }
    }
    //再次遍历,如果该元素所在的行或者列第一个值是0,那么就把当前元素置为0
    for (i in 1 until m) {
        for (j in 1 until n) {
            if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                matrix[i][j] = 0
            }
        }
    }
    //把第一列的元素置为0
    if (firstColHas0) {
        for (i in 0 until m) {
            matrix[i][0] = 0
        }
    }
    //把第一行的元素置为0
    if (firstRowHas0) {
        for (j in 0 until n) {
            matrix[0][j] = 0
        }
    }
}

/**
 * 第19题 螺旋矩阵
 * 54. 螺旋矩阵
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 */
private fun spiralOrder2(matrix: Array<IntArray>): List<Int> {
    val m = matrix.size
    val n = matrix[0].size
    var t = 0
    var b = m - 1
    var l = 0
    var r = n - 1
    val ans = mutableListOf<Int>()
    while (l <= r && t <= b) {
        for (i in l until r) {
            ans.add(matrix[t][i])
        }
        for (j in t..b) {
            ans.add(matrix[j][r])
        }
        if (l < r && t < b) {
            for (i in r - 1 downTo l + 1) {
                ans.add(matrix[b][i])
            }
            for (j in b downTo t + 1) {
                ans.add(matrix[j][l])
            }
        }
        t++
        b--
        l++
        r--
    }
    return ans
}

/**
 * 第20题 旋转图像
 * 48. 旋转图像
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 *
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 * 示例 2：
 *
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 *
 * 思路:
 * 1. 先沿着中心的x轴上下翻转
 * 2. 再沿着对角线45度交换
 * 最后的结果就是旋转了90度
 */
private fun rotate2(matrix: Array<IntArray>): Unit {
    val m = matrix.size
    val n = matrix[0].size
    for (i in 0 until m / 2) {
        for (j in 0 until n) {
            val tmp = matrix[i][j]
            matrix[i][j] = matrix[n - i - 1][j]
            matrix[n - i - 1][j] = tmp
        }
    }
    for (i in 0 until m) {
        for (j in 0 until i) {
            val tmp = matrix[i][j]
            matrix[i][j] = matrix[j][i]
            matrix[j][i] = tmp
        }
    }
}

/**
 * 第21题 搜索二维矩阵 II
 * 240. 搜索二维矩阵 II
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 *
 */
private fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
    val m = matrix.size
    val n = matrix[0].size
    var i = 0
    var j = n - 1
    while (i < m && j >= 0) {
        if (matrix[i][j] == target) {
            return true
        } else if (matrix[i][j] > target) {
            j--
        } else {
            i++
        }
    }
    return false
}