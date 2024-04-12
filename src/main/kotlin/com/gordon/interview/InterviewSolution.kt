package org.example.com.gordon.interview


fun split(str: String): Array<String> {
    var post: Int
    val ans = mutableListOf<String>()
    var s = str
    while (true) {
        post = s.indexOf("&&")
        if (post != -1) {
            if (post != 0) {
                ans.add(str.substring(0, post))
            }
            s = s.substring(post + "&&".length)
        } else {
            break
        }
    }
    if (s.isNotBlank()) {
        ans.add(s)
    }
    return ans.toTypedArray()
}