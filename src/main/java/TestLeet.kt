class TestLeet {
    fun spiralOrder(matrix: Array<IntArray>?): List<Int> {
        val order: MutableList<Int> = ArrayList()
        if (matrix.isNullOrEmpty() || matrix[0].isEmpty()) {
            return order
        }
        val rows = matrix.size
        val columns = matrix[0].size
        var left = 0
        var right = columns - 1
        var top = 0
        var bottom = rows - 1
        while (left <= right && top <= bottom) {
            for (column in left..right) {
                order.add(matrix[top][column])
            }
            for (row in top + 1..bottom) {
                order.add(matrix[row][right])
            }
            if (left < right && top < bottom) {
                for (column in right - 1 downTo left + 1) {
                    order.add(matrix[bottom][column])
                }
                for (row in bottom downTo top + 1) {
                    order.add(matrix[row][left])
                }
            }
            left++
            right--
            top++
            bottom--
        }
        return order
    }
}
