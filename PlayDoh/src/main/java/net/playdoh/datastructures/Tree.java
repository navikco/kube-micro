package net.playdoh.datastructures;

import lombok.Getter;

import java.util.LinkedList;
import java.util.Queue;

@Getter
public class Tree {

    /**
     *          6
     *
     *        /  \
     *
     *      3     8
     *
     *    /  \  /  \
     *
     *  2    4 7    9
     *
     *        \
     *
     *         5
     */
    @Getter
    public static class Node {

        Node left;
        Node right;
        int data;

        public Node(int data) {
            this.data = data;
        }

        public void insert(int data) {

            if (data <= this.data) {
                if (left == null) {
                    left = new Node(data);
                } else {
                    left.insert(data);
                }
            } else {
                if (right == null) {
                    right = new Node(data);
                } else {
                    right.insert(data);
                }
            }
        }

        public boolean contains(int data) {

            if (data == this.data) {
                return true;
            } else if (data < this.data) {
                if (left == null) {
                    return false;
                } else {
                    return this.left.contains(data);
                }
            } else {
                if (right == null) {
                    return false;
                } else {
                    return right.contains(data);
                }
            }
        }

        //        DFS : root --> left --> right
        public void preOrderTraversal() {

            System.out.println("TreeNode@PreOrderTraversal@Data :::>>> " + data);
            if (left != null) {
                left.preOrderTraversal();
            }
            if (right != null) {
                right.preOrderTraversal();
            }
        }

        //        DFS : left --> root --> right
        public void inOrderTraversal() {

            if (left != null) {
                left.inOrderTraversal();
            }
            System.out.println("TreeNode@InOrderTraversal@Data :::>>> " + data);
            if (right != null) {
                right.inOrderTraversal();
            }
        }

        //        DFS : left --> right --> root
        public void postOrderTraversal() {

            if (left != null) {
                left.postOrderTraversal();
            }
            if (right != null) {
                right.postOrderTraversal();
            }
            System.out.println("TreeNode@PostOrderTraversal@Data :::>>> " + data);
        }

        //        BFS : left --> right
        public void levelOrderTraversal() {

            Queue<Node> queue = new LinkedList<>();
            queue.add(this);

            while (!queue.isEmpty()) {

                Node node = queue.remove();
                System.out.println("TreeNode@LevelOrderTraversal---BFS@Data :::>>> " + node.getData());

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        public int count() {

            return 1 + (left != null ? left.count() : 0) + (right != null ? right.count() : 0);
        }

        public int deepestNode() {

            if (left == null && right == null) {

                return data;
            }
            else {

                Queue<Node> queue = new LinkedList<>();
                queue.add(this);
                int deepestNode = data;
                while (!queue.isEmpty()) {

                    int queueSize = queue.size();

                    for (int i = 0; i < queueSize; i++) {

                        Node currentNode = queue.remove();
                        deepestNode = currentNode.data;

                        if (currentNode.getLeft() != null) {
                            queue.add(currentNode.getLeft());
                        }
                        if (currentNode.getRight() != null) {
                            queue.add(currentNode.getRight());
                        }
                    }
                }
                return deepestNode;
            }
        }
    }
}
