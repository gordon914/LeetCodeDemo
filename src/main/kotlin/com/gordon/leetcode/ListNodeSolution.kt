package org.example.com.gordon.leetcode

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

fun removeElements(head: ListNode?, `val`: Int): ListNode? {
    val dummy = ListNode(-1)
    dummy.next = head
    var curr = dummy
    while (curr.next != null) {
        if (curr.next!!.`val` == `val`) {
            curr.next = curr.next!!.next
        } else {
            curr = curr.next!!
        }
    }
    return dummy.next
}

fun reverseList(head: ListNode?): ListNode? {
    var pre:ListNode? = null
    var curr = head
    while (curr != null) {
        val tmp = curr.next
        curr.next = pre
        pre = curr
        curr = tmp
    }
    return pre
}
