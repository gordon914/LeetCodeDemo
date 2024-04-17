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

private fun halfListNode(head: ListNode?): ListNode? {
    var fast = head
    var slow = head
    while (fast?.next != null && fast.next?.next != null) {
        fast = fast.next?.next
        slow = slow?.next
    }
    return slow
}

/**
 * 第25题 环形链表
 * 判断链表中是否有环
 */
fun hasCycle(head: ListNode?): Boolean {
    var fast = head
    var slow = head
    while (fast?.next != null && fast.next?.next != null) {
        fast = fast.next?.next
        slow = slow?.next
        if (fast == slow) {
            return true
        }
    }
    return false
}

/**
 * 第26题 环形链表II
 * 找到环形链表节点的入口位置
 */
fun detectCycle(head: ListNode?): ListNode? {
    var fast = head
    var slow = head
    while (fast?.next != null) {
        fast = fast.next?.next
        slow = slow?.next
        if (fast == slow) {
            var l1 = head
            var l2 = slow
            while (l1 != l2) {
                l1 = l1?.next
                l2 = l2?.next
            }
            return l2
        }
    }
    return null
}

/**
 * 第27题 合并两个有序链表
 */
fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    val dummy = ListNode(-1)
    var pre: ListNode? = dummy
    var l1 = list1
    var l2 = list2
    while (l1 != null && l2 != null) {
        if (l1.`val` < l2.`val`) {
            pre?.next = l1
            l1 = l1.next
        } else {
            pre?.next = l2
            l2 = l2.next
        }
        pre = pre?.next
    }
    pre?.next = l1 ?: l2
    return dummy.next
}

/***
 * 第28题 两数相加
 * 2. 两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 *
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 示例 3：
 *
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 */
fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    var head: ListNode? = null
    var tail: ListNode? = null
    var t1 = l1
    var t2 = l2
    var carry = 0
    while (t1 != null || t2 != null) {
        val n1 = t1?.`val` ?: 0
        val n2 = t2?.`val` ?: 0
        val sum = n1 + n2 + carry
        if (head == null) {
            head = ListNode(sum % 10).also {
                tail = it
            }
        } else {
            tail?.next = ListNode(sum % 10)
            tail = tail?.next
        }
        carry = sum / 10
        t1 = t1?.next
        t2 = t2?.next
    }
    if (carry > 0) {
        tail?.next = ListNode(carry)
    }
    return head
}

/**
 * 第29题 删除链表倒数第N个元素
 *
 */
fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
    val dummy = ListNode(0).apply {
        next = head
    }
    var fast: ListNode? = dummy
    var slow: ListNode? = dummy
    for (i in 0..n) {
        fast = fast?.next
    }
    while (fast != null) {
        fast = fast.next
        slow = slow?.next
    }
    slow?.next = slow?.next?.next
    return dummy.next
}

/**
 * 第30题 两两交换链表中的节点
 * 24. 两两交换链表中的节点
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 *
 * 示例 1：
 *
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 *
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1]
 * 输出：[1]
 */
fun swapPairs(head: ListNode?): ListNode? {
    val dummy = ListNode(0).apply {
        next = head
    }
    var curr:ListNode? = dummy
    while (curr?.next != null && curr.next?.next != null) {
        val first = curr.next
        val second = curr.next?.next
        curr.next = second
        first?.next = second?.next
        second?.next = first
        curr = first
    }
    return dummy.next
}