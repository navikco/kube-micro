package net.playdoh.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LinkedListPlay {

    public static void main(String[] args) {

        LinkedListPlay linkedListPlay = new LinkedListPlay();
        ListNode l1 = new ListNode(1, new ListNode(4, new ListNode(6)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4, new ListNode(5))));
        ListNode l3 = linkedListPlay.mergeTwoLists(l1, l2);
        System.out.println("Merged LinkedList :::>>> " + l3);
        while(l3 != null) {
            System.out.println(l3.val);
            l3 = l3.next;
        }

        ListNode ln5 = new ListNode(5);
        ListNode ln4 = new ListNode(4);
        ListNode ln3 = new ListNode(3);
        ListNode ln2 = new ListNode(2);
        ListNode ln1 = new ListNode(1);
        ln1.next = ln2;
        ln2.next = ln3;
        ln3.next = ln4;
        ln4.next = ln5;
        ln5.next = ln2;

        System.out.println("LinkedList has Cycle :::>>> " + linkedListPlay.hasCycle(ln1));

        ListNode l6 = new ListNode(1, new ListNode(1, new ListNode(1)));
        System.out.println("Removed Duplicates from Sorted LinkedList[1, 1, 1]  :::>>> ");
        l6 = linkedListPlay.deleteDuplicates(l6);
        while(l6 != null) {
            System.out.println(l6.val);
            l6 = l6.next;
        }

        ListNode li5 = new ListNode(5);
        ListNode li4 = new ListNode(4);
        ListNode li3 = new ListNode(3);
        ListNode li2 = new ListNode(2);
        ListNode li1 = new ListNode(1);
        li1.next = li2;
        li2.next = li3;
        li3.next = li4;
        li4.next = li5;
        ListNode li6 = new ListNode(7, new ListNode(9, li3));
        System.out.println("Intersection Node of Two LinkedList[1, 2, 3, 4, 5] [7, 9, 3, 4, 5]  :::>>> " + linkedListPlay.getIntersectionNode(li1, li6).val);

        ListNode li = linkedListPlay.removeElements(li1, 1);
        System.out.println("Remove Elements from LinkedList[1, 2, 3, 4, 5]  :::>>> ");
        while(li != null) {
            System.out.println(li.val);
            li = li.next;
        }

        ListNode lr = linkedListPlay.reverseList(li1);
        System.out.println("Reversed LinkedList[1, 2, 3, 4, 5]  :::>>> ");
        while(lr != null) {
            System.out.println(lr.val);
            lr = lr.next;
        }

        ListNode lrr = linkedListPlay.recursiveReverseList(li1);
        System.out.println("Recursive Reversed LinkedList[1, 2, 3, 4, 5]  :::>>> ");
        while(lrr != null) {
            System.out.println(lrr.val);
            lrr = lrr.next;
        }

        ListNode lp = new ListNode(1, new ListNode(2, new ListNode(1, new ListNode(2, new ListNode(1)))));
        System.out.println("Palindrome LinkedList[1, 2, 1, 2, 1]  :::>>> " + linkedListPlay.isPalindrome(lp));

        ListNode ld = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        linkedListPlay.deleteNode(ld);
        System.out.println("Deleted Given Node LinkedList[1, 2, 3, 4, 5]  :::>>> ");
        while(ld != null) {
            System.out.println(ld.val);
            ld = ld.next;
        }

        ListNode lm = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println("MiddleNode of LinkedList[1, 2, 3, 4, 5]  :::>>> " + linkedListPlay.middleNode(lm).val);

        ListNode lb = new ListNode(1, new ListNode(0, new ListNode(0, new ListNode(1, new ListNode(1)))));
        System.out.println("Decimal Value of BINARY LinkedList[1, 0, 0, 1, 1]  :::>>> " + linkedListPlay.getDecimalValue(lb));

        ld = new ListNode(1,
                new ListNode(2,
                    new ListNode(3,
                        new ListNode(4,
                            new ListNode(5,
                                new ListNode(6,
                                    new ListNode(7,
                                        new ListNode(8,
                                            new ListNode(9,
                                                new ListNode(10,
                                                    new ListNode(11,
                                                        new ListNode(12,
                                                            new ListNode(13)))))))))))));
        System.out.println("Delete Multiple Nodes in a Pattern From LinkedList[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13]  :::>>> " + linkedListPlay.deleteNodes(ld, 2, 3));
        while(ld != null) {
            System.out.println(ld.val);
            ld = ld.next;
        }

        ListNode la1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode la2 = new ListNode(5, new ListNode(6, new ListNode(6)));
        la1 = linkedListPlay.addTwoNumbers(la1, la2);
        System.out.println("Add Two Numbers Represented in TWO LinkedLists[2, 4, 3] [5, 6, 6]  :::>>> ");
        while(la1 != null) {
            System.out.println(la1.val);
            la1 = la1.next;
        }

        ListNode ln = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ln = linkedListPlay.removeNthFromEnd(ln, 5);
        System.out.println("Delete Nth Node from the End of LinkedList[1, 2, 3, 4, 5]  :::>>> ");
        while(ln != null) {
            System.out.println(ln.val);
            ln = ln.next;
        }
    }

    static class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {


        ListNode top = null;
        ListNode current = null;

        while(l1 != null || l2 != null) {

            if (l1 == null) {
                current = fillCurrent(current, l2, true);
                return (top == null) ? current : top;
            } else if(l2 == null) {
                current = fillCurrent(current, l1, true);
                return (top == null) ? current : top;
            }

            if(l1.val < l2.val) {
                current = fillCurrent(current, l1, false);
                l1 = l1.next;
            } else {
                current = fillCurrent(current, l2, false);
                l2 = l2.next;
            }
            if(top == null)
                top = current;
        }

        return top;
    }

    private ListNode fillCurrent(ListNode current, ListNode node, boolean isEnd) {

        if(current == null) {
            current = isEnd ? node : new ListNode(node.val);
        }
        else {
            current.next = isEnd ? node : new ListNode(node.val);
            current = current.next;
        }
        return current;
    }

    public boolean hasCycle(ListNode head) {

        ListNode node = head;
        Set<String> nodeRefs = new HashSet<>();
        while (node != null) {

            if (nodeRefs.contains(node.toString())) {
                return true;
            } else {
                nodeRefs.add(node.toString());
            }
            node = node.next;
        }
        return false;
    }

    public ListNode deleteDuplicates(ListNode head) {

        Set<Integer> uniqueNodes = new HashSet<>();
        ListNode node = head;
        ListNode previousNode = head;
        while (node != null) {
            if(uniqueNodes.contains(node.val)) {
                if (node.next != null) {
                    previousNode.next = node.next;
                } else {
                    previousNode.next = null;
                }
            } else {
                uniqueNodes.add(node.val);
                previousNode = node;
            }
            node = node.next;
        }
        return head;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode a = headA;
        ListNode b = headB;
        Set<ListNode> listSet = new HashSet<>();

        while (a != null) {
            listSet.add(a);
            a = a.next;
        }

        while (b != null) {
            if (listSet.contains(b)) {
                return b;
            } else {
                b = b.next;
            }
        }

        return null;
    }

    public ListNode removeElements(ListNode head, int val) {

        ListNode node = head;
        ListNode previousNode = null;
        while (node != null) {

            if (node.val == val) {
                if (previousNode == null) {
                    head = node.next;
                } else if (node.next == null) {
                    previousNode.next = null;
                } else {
                    previousNode.next = node.next;
                }
            } else {
                previousNode = node;
            }
            node = node.next;
        }
        return head;
    }

    public ListNode reverseList(ListNode head) {

        ListNode reversedHead = null;
        ListNode previousNode = null;

        while (head != null) {
            reversedHead = new ListNode(head.val);
            reversedHead.next = previousNode;
            previousNode = reversedHead;
            head = head.next;
        }

        return reversedHead;
    }

    private ListNode recursiveReverseList(ListNode current) {

        if (current == null || current.next == null) {
            return current;
        }

        ListNode node = recursiveReverseList(current.next);
        current.next.next = current;
        current.next = null;
        return node;
    }

    public boolean isPalindrome(ListNode head) {

        List<Integer> list = new ArrayList<>();
        ListNode node = head;

        while (node != null) {
            list.add(node.val);
            node = node.next;
        }

        for (int i = 0, j = list.size() - 1; i<=j; i++, j--) {

            if (list.get(i) != list.get(j)) {
                return false;
            }
        }
        return true;
    }

    public void deleteNode(ListNode node) {

        ListNode previous = null;
        while (node != null) {
            if (node.next == null) {
                previous.next = null;
                return;
            }
            node.val = node.next.val;
            previous = node;
            node = node.next;
        }
    }

    public ListNode middleNode(ListNode head) {

        List<ListNode> nodes = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            nodes.add(node);
            node = node.next;
        }
        return nodes.size() > 0 ? nodes.get(nodes.size() / 2) : null;
    }

    public int getDecimalValue(ListNode head) {

        StringBuffer binaryNum = new StringBuffer();
        while (head != null) {
            binaryNum.append(head.val);
            head = head.next;
        }
        return Integer.parseInt(binaryNum.toString(), 2);
    }

    public ListNode deleteNodes(ListNode head, int m, int n) {

        int keep = m;
        int delete = n;
        ListNode node = head;
        ListNode previous = null;

        while (node != null) {

            while (keep > 0 && node != null) {
                previous = node;
                node = node.next;
                keep--;
            }
            keep = m;
            while (delete > 0 && node != null) {
                previous.next = node.next;
                node = node.next;
                delete--;
            }
            delete = n;
        }
        return head;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int remainder = 0;
        ListNode sum = null;
        ListNode current = null;
        while (true) {

            int num1 = 0;
            int num2 = 0;
            if (l1 == null && l2 == null) {
                break;
            }
            if (l1 != null) {
                num1 = l1.val;
            }
            if (l2 != null) {
                num2 = l2.val;
            }

            int total = num1 + num2 + remainder;
            int val = total >= 10 ? total - 10 : total;
            remainder = total >= 10 ? 1 : 0;
            ListNode node = new ListNode(val);
            if (sum == null) {
                sum = node;
                current = node;
            } else {
                current.next = node;
                current = current.next;
            }
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (remainder > 0) {
            current.next = new ListNode(remainder);
        }
        return sum;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {

        List<ListNode> list = new ArrayList<>();

        while (head != null) {

            list.add(head);
            head = head.next;
        }
        head = null;
        ListNode current = null;
        for (int i = 0; i < list.size(); i++) {

            if (i == list.size() - n) {
                continue;
            } else if (i == list.size() - 1 && i == n && n == list.size()) {
                continue;
            } else {
                if (head == null) {
                    head = list.get(i);
                    current = head;
                } else {
                    current.next = list.get(i);
                    current = current.next;
                }
                current.next = null;
            }
        }
        return head;
    }
}