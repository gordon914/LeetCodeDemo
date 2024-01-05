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
    var pre: ListNode? = null
    var curr = head
    while (curr != null) {
        val tmp = curr.next
        curr.next = pre
        pre = curr
        curr = tmp
    }
    return pre
}

fun swapPairs(head: ListNode?): ListNode? {
    val dummy = ListNode(-1)
    dummy.next = head
    var curr = dummy
    while (curr.next != null && curr.next!!.next != null) {
        val tmp = curr.next!!.next!!.next
        var first = curr.next
        var second = curr.next!!.next
        curr.next = second
        second?.next = first
        first?.next = tmp
        curr = first!!
    }
    return dummy.next
}

fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
    val dummy = ListNode(-1).apply {
        next = head
    }
    var fast = head
    var count = n
    while (count > 0) {
        fast = fast?.next
        count--
    }
    var slow: ListNode? = dummy
    while (fast != null) {
        fast = fast.next
        slow = slow?.next
    }
    slow?.next = slow?.next?.next
    return dummy.next
}