package net.playdoh.datastructures;

public class DatastructurePlay {

    public static void main(String[] args) {

        DatastructurePlay datastructurePlay = new DatastructurePlay();
        datastructurePlay.queuePlay();
        datastructurePlay.stackPlay();
        datastructurePlay.linkedListPlay();
        datastructurePlay.treePlay();
    }

    public void queuePlay() {

        Queue queue = new Queue();

        //Add 5 Elements
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        //Peak First Element
        System.out.println("QueueItem:Peak :::>>> " + queue.peek());


        int i = 0;
        while (!queue.empty())
            System.out.println("QueueItem[" + i++ + "] :::>>> " + queue.poll());
    }

    public void stackPlay() {

        Stack stack = new Stack();

        //Add 5 Elements
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);
        stack.push(60);

        //Peak First Element
        System.out.println("StackItem:Peak :::>>> " + stack.peek());


        int i = 0;
        while (!stack.empty())
            System.out.println("StackItem[" + i++ + "] :::>>> " + stack.pop());
    }

    public void linkedListPlay() {

        LinkedList linkedList = new LinkedList();

        //Add 5 Elements
        linkedList.append(11);
        linkedList.append(22);
        linkedList.append(33);
        linkedList.append(44);
        linkedList.append(55);
        linkedList.append(66);

        //Peak First Element
        System.out.println("LinkedListItem:Head :::>>> " + linkedList.getHead().data);
        System.out.println("LinkedListItem:Size :::>>> " + linkedList.getSize());
        System.out.println("LinkedListItem[4] :::>>> " + linkedList.get(4));

        //Remove 4th Element
        linkedList.remove(4);

        for (int i = 0; i < linkedList.getSize(); i++) {

            System.out.println("LinkedList[" + i + "] :::>>> " + linkedList.get(i));
        }
    }

    public void treePlay() {

        Tree tree = new Tree();

        Tree.Node node = new Tree.Node(6);
        node.insert(8);
        node.insert(3);
        node.insert(2);
        node.insert(4);
        node.insert(7);
        node.insert(9);
        node.insert(5);

        System.out.println("Tree:Root :::>>> " + node.data);
        System.out.println("Tree:Contains[7] :::>>> " + node.contains(7));
        System.out.println("Tree:Contains[6] :::>>> " + node.contains(4));
        System.out.println("Tree:Contains[9] :::>>> " + node.contains(6));
        System.out.println("Tree:Contains[1] :::>>> " + node.contains(1));

        System.out.println("Tree:DeepestNode[6, 8, 3, 2, 4, 7, 9, 5] :::>>> " + node.deepestNode());

        node.preOrderTraversal();
        node.inOrderTraversal();
        node.postOrderTraversal();
        node.levelOrderTraversal();
    }
}