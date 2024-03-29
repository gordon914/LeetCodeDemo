import java.util.*
import kotlin.math.max

class ConvertJava {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        if (intervals.isEmpty()) {
            return Array(0) { IntArray(2) }
        }
        Arrays.sort(intervals) { interval1, interval2 -> interval1[0] - interval2[0] }
        val merged: MutableList<IntArray> = ArrayList()
        for (i in intervals.indices) {
            val L = intervals[i][0]
            val R = intervals[i][1]
            if (merged.size == 0 || merged[merged.size - 1][1] < L) {
                merged.add(intArrayOf(L, R))
            } else {
                merged[merged.size - 1][1] = max(merged[merged.size - 1][1].toDouble(), R.toDouble())
                    .toInt()
            }
        }
        return merged.toTypedArray<IntArray>()
    }
}
