package org.example.com.gordon.leetcode.top100

/**
 * 54. 螺旋矩阵
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 */
fun spiralOrder(matrix: Array<IntArray>): List<Int> {
    if (matrix.isEmpty() || matrix[0].isEmpty()) {
        return emptyList()
    }
    val ans = mutableListOf<Int>()
    val m = matrix.size
    val n = matrix[0].size
    var l = 0
    var r = n - 1
    var t = 0
    var b = m - 1
    while (l <= r && t <= b) {
        for (col in l..r) {
            ans.add(matrix[t][col])
        }
        for (row in (t + 1)..b) {
            ans.add(matrix[row][r])
        }
        if (l < r && t < b) {
            for (col in (r - 1) downTo (l + 1)) {
                ans.add(matrix[b][col])
            }
            for (row in b downTo (t + 1)) {
                ans.add(matrix[row][l])
            }
        }
        l++
        r--
        t++
        b--
    }
    return ans
}

/**
 * 旋转图像
 * 90度旋转图像
 * 1 2 3       7 4 1
 * 4 5 6  -->  8 5 2
 * 7 8 9       9 6 3
 */
fun rotate(matrix: Array<IntArray>): Unit {
    val n = matrix.size
    for (i in 0 until n / 2) {
        for (j in 0 until n) {
            val temp = matrix[i][j]
            matrix[i][j] = matrix[n - i - 1][j]
            matrix[n - i - 1][j] = temp
        }
    }
    for (i in 0 until n) {
        for (j in 0 until i) {
            val temp = matrix[i][j]
            matrix[i][j] = matrix[j][i]
            matrix[j][i] = temp
        }
    }
}