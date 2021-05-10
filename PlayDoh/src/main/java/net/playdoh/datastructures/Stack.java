package net.playdoh.datastructures;

public class Stack {

    private Node last;

    public class Node {

        Node previous;
        int data;

        public Node(int data) {
            this.data = data;
        }
    }

    public int push(int data) {

        Node node = new Node(data);

        if (last == null) {

            last = node;
        } else {

            node.previous = last;
            last = node;
        }

        return data;
    }

    public int pop() {

        if(empty())
            return 0;
        int lastData = last.data;
        last = last.previous;

        return lastData;
    }

    public int peek() {

        return last == null ? 0 : last.data;
    }

    public boolean empty() {

        return last == null;
    }
}
