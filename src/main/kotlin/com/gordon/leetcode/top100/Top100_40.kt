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
private fun getIntersectionNode(headA: ListNode?, headB:ListNode?):ListNode? {
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
        return getIntersectionNode(headB,headA)
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