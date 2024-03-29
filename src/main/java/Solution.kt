internal class Solution {
    var ori: MutableMap<Char, Int> = HashMap()
    var cnt: MutableMap<Char, Int> = HashMap()

    /**
     * 先扩展窗口右边的指针,当满足条件时,再收缩左边的指针,缩小窗口
     * 用len来记录历史最小值,ansL和ansR记录窗口的左右指针,用于截取最小窗口的子字符串
     */
    fun minWindow(s: String, t: String): String {
        t.forEach { c ->
            ori[c] = ori.getOrDefault(c, 0) + 1
        }
        var l = 0
        var r = -1
        var len = Int.MAX_VALUE
        var ansL = -1
        var ansR = -1
        val sLen = s.length
        while (r < sLen) {
            ++r
            if (r < sLen && ori.containsKey(s[r])) {
                cnt[s[r]] = cnt.getOrDefault(s[r], 0) + 1
            }
            while (check() && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1
                    ansL = l
                    ansR = l + len
                }
                if (ori.containsKey(s[l])) {
                    cnt[s[l]] = cnt.getOrDefault(s[l], 0) - 1
                }
                ++l
            }
        }
        return if (ansL == -1) "" else s.substring(ansL, ansR)
    }

    private fun check(): Boolean {
        ori.forEach { (key, value) ->
            if (cnt.getOrDefault(key, 0) < value) {
                return false
            }
        }
        return true
    }
}

