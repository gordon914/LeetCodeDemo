package org.example.com.gordon.leetcode.top100

import org.example.com.gordon.leetcode.ListNode

/**
 * 第22题 相交链表
 * 160. 相交链表
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 *
 * 图示两个链表在节点 c1 开始相交：
 *
 * 题目数据 保证 整个链式结构中不存在环。
 */
private fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
    var lenA = 0
    var lenB = 0
    var la = headA
    var lb = headB
    while (la != null) {
        la = la.next
        lenA++
    }
    while (lb != null) {
        lb = lb.next
        lenB++
    }
    if (lenA < lenB) {
        return getIntersectionNode(headB, headA)
    }
    var distance = lenA - lenB
    var fast = headA
    var slow = headB
    while (distance > 0) {
        fast = fast?.next
        distance--
    }
    while (fast != slow && fast != null) {
        fast = fast.next
        slow = slow?.next
    }
    return slow
}

/**
 * 第23题 翻转链表
 */
fun reverseList(head: ListNode?): ListNode? {
    var dummy = head
    var pre: ListNode? = null
    var tmp: ListNode? = null
    while (dummy != null) {
        tmp = dummy.next
        dummy.next = pre
        pre = dummy
        dummy = tmp
    }
    return pre
}

/**
 * 第24题 回文链表
 */
fun isPalindrome(head: ListNode?): Boolean {
    if (head == null) {
        return true
    }
    val halfEnd = halfListNode(head)
    val second = reverseList(halfEnd?.next)
    var p1 = head
    var p2 = second
    var ans = true
    while (ans && p2 != null) {
        if (p1?.`val` != p2.`val`) {
            ans = false
        }
        p1 = p1?.next
        p2 = p2.next
    }
    //还原链表
    halfEnd?.next = reverseList(second)
    return ans
}

private fun halfListNode(head: ListNode?):ListNode?{
    var fast = head
    var slow = head
    while (fast?.next != null && fast.next?.next != null) {
        fast = fast.next?.next
        slow = slow?.next
    }
    return slow
}