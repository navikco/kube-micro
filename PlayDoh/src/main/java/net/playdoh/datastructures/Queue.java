package net.playdoh.datastructures;

public class Queue {

    private Node first, last;

    public class Node {

        Node next;
        int data;

        public Node(int data) {
            this.data = data;
        }
    }

    public boolean add(int data) {

        Node node = new Node(data);

        if (first == null) {

            first = node;
            last = node;
        } else {

            last.next = node;
            last = node;
        }

        return true;
    }

    public int peek() {

        return empty() ? 0 : first.data;
    }

    public int poll() {

        if(empty())
            return 0;

        Node node = first;
        first = first.next;
        return node.data;
    }

    public boolean empty() {

        return first == null;
    }
}
