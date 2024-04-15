package org.example.com.gordon.leetcode.top100

import org.example.com.gordon.leetcode.ListNode

/**
 * 160. 相交链表
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 *
 * 图示两个链表在节点 c1 开始相交：
 *
 *
 *
 * 题目数据 保证 整个链式结构中不存在环。
 *
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 */
private fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
    var l1 = headA
    var l2 = headB
    var len1 = 0
    var len2 = 0
    while (l1 != null) {
        l1 = l1.next
        len1++
    }
    while (l2 != null) {
        l2 = l2.next
        len2++
    }
    if (len1 < len2) {
        return getIntersectionNode(headB, headA)
    }
    var dis = len1 - len2
    var fast = headA
    var slow = headB
    while (dis > 0 && fast != null) {
        fast = fast.next
        dis--
    }
    while (fast != slow && fast != null) {
        fast = fast.next
        slow = slow?.next
    }
    return slow
}