package net.playdoh.datastructures;

import lombok.Getter;

@Getter
public class LinkedList {

    private Node head;
    private int size;

    public class Node {

        Node next;
        int data;

        public Node(int data) {
            this.data = data;
        }
    }

    public int append(int data) {

        Node node = new Node(data);

        if (head == null) {

            head = node;
        } else {

            node.next = head;
            head = node;
        }
        size++;

        return data;
    }

    public int get(int index) {

        if (empty() || index >= size)
            return 0;

        Node current = head;
        for (int i = 1; i <= index; i++) {

            current = current.next;
        }

        return current.data;
    }

    public int remove(int index) {

        int removedData = 0;
        if (empty() || index >= size)
            return 0;

        if (index == 0) {

            removedData = head.data;
            head = head.next;
            size--;
            return removedData;
        } else {

            Node current = head;
            for (int i = 1; i < index; i++) {

                current = current.next;
            }
            Node nextNode = current.next.next == null ? null : current.next.next;
            Node removedNode = current.next;
            removedNode.next = null;
            removedData = removedNode.data;
            current.next = nextNode;
            size--;
        }
        return removedData;
    }

    public boolean empty() {

        return head == null;
    }
}
