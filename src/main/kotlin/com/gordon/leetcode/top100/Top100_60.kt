package org.example.com.gordon.leetcode.top100

import java.util.*
import kotlin.collections.ArrayDeque

/**
 * 第51题 岛屿数量
 * https://leetcode.cn/problems/number-of-islands/description/?envType=study-plan-v2&envId=top-100-liked
 *200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 *
 */
fun numIslands(grid: Array<CharArray>): Int {
    var count = 0
    for (i in grid.indices) {
        for (j in grid[0].indices) {
            if (grid[i][j] == '1') {
                dfs(grid, i, j)
                count++
            }
        }
    }
    return count
}

private fun dfs(grid: Array<CharArray>, r: Int, c: Int) {
    if (!isValidPosition(grid, r, c)) {
        return
    }
    if (grid[r][c] != '1') {
        return
    }
    grid[r][c] = '2'
    dfs(grid, r, c - 1)
    dfs(grid, r, c + 1)
    dfs(grid, r - 1, c)
    dfs(grid, r + 1, c)
}

private fun isValidPosition(grid: Array<CharArray>, r: Int, c: Int): Boolean {
    return r in grid.indices && c in grid[0].indices
}

private fun exploreIsland(grid: Array<CharArray>, r: Int, c: Int) {
    val stack = LinkedList<Pair<Int, Int>>()
    stack.add(r to c)
    grid[r][c] = '2'
    val directs = listOf(
        Pair(-1, 0),
        Pair(1, 0),
        Pair(0, -1),
        Pair(0, 1),
    )
    while (stack.isNotEmpty()) {
        val (x, y) = stack.poll()
        for (direct in directs) {
            val newX = x + direct.first
            val newY = y + direct.second
            if (isValidPosition(grid, newX, newY) && grid[newX][newY] == '1') {
                stack.add(newX to newY)
                grid[newX][newY] = '2'
            }
        }
    }
}

/**
 * 第52题 腐烂的橘子
 *994. 腐烂的橘子
 * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 *
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 *
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 */
fun orangesRotting(grid: Array<IntArray>): Int {
    val queue = LinkedList<Pair<Int, Int>>()
    val m = grid.size
    val n = grid[0].size
    var count = 0
    for (i in 0 until m) {
        for (j in 0 until n) {
            if (grid[i][j] == 1) {
                count++
            } else if (grid[i][j] == 2) {
                queue.add(i to j)
            }
        }
    }
    var rount = 0
    while (count > 0 && queue.isNotEmpty()) {
        val size = queue.size
        rount++
        for (i in 0 until size) {
            val (r, c) = queue.poll()
            if (r - 1 >= 0 && grid[r - 1][c] == 1) {
                count--
                grid[r - 1][c] = 2
                queue.add(r - 1 to c)
            }
            if (r + 1 < m && grid[r + 1][c] == 1) {
                count--
                grid[r + 1][c] = 2
                queue.add(r + 1 to c)
            }
            if (c - 1 >= 0 && grid[r][c - 1] == 1) {
                count--
                grid[r][c - 1] = 2
                queue.add(r to c - 1)
            }
            if (c + 1 < n && grid[r][c + 1] == 1) {
                count--
                grid[r][c + 1] = 2
                queue.add(r to c + 1)
            }
        }
    }
    return if (count > 0) {
        -1
    } else {
        rount
    }
}

/**
 * 第53题 课程表
 * https://leetcode.cn/problems/course-schedule/description/?envType=study-plan-v2&envId=top-100-liked
 * 207. 课程表
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 *
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 *
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 */
fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
    val map = mutableMapOf<Int, MutableList<Int>>()
    //1. 构建邻接表
    for ((course, preCourse) in prerequisites) {
        map.getOrPut(preCourse) { mutableListOf() }.add(course)
    }
    //2. 统计每个课程的度数
    val courseDegrees = IntArray(numCourses)
    for (courses in map.values) {
        for (course in courses) {
            courseDegrees[course]++
        }
    }
    // 将度数为0的课程添加到队列中去
    val queue = ArrayDeque<Int>()
    for (i in 0 until numCourses) {
        if (courseDegrees[i] == 0) {
            queue.add(i)
        }
    }
    var count = 0
    // 每次从队列中删除度数为0的课程,并统计删除的个数
    while (queue.isNotEmpty()) {
        val course = queue.removeFirst()
        count++
        //遍历邻接表中对应课程的链表(背包),将入度减1,如果此时该课程的度数变为0了,就将其添加到队列中去
        map[course]?.forEach {
            courseDegrees[it]--
            if (courseDegrees[it] == 0) {
                queue.add(it)
            }
        }
    }
    return count == numCourses
}