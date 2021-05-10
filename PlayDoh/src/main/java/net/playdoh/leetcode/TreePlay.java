package net.playdoh.leetcode;

import net.playdoh.datastructures.Tree;

import java.util.ArrayList;
import java.util.List;

public class TreePlay {

    public static void main(String[] args) {

        TreePlay depthFirstSearch = new TreePlay();

        Tree.Node node = new Tree.Node(6);
        node.insert(8);
        node.insert(3);
        node.insert(2);
        node.insert(4);
        node.insert(7);
        node.insert(9);
        node.insert(5);

        depthFirstSearch.sumOfNodesInRange(node, 3, 7);
        System.out.println("Tree:sumOfNodesInRange[6, 8, 3, 2, 4, 7, 9, 5] - 3, 7 :::>>> " + depthFirstSearch.sum);

        System.out.println("Tree:ValidBSF[6, 8, 3, 2, 4, 7, 9, 5] :::>>> " + depthFirstSearch.validBinarySearchTree(node));

        depthFirstSearch.longestPath(node);
        System.out.println("Tree:Diameter[6, 8, 3, 2, 4, 7, 9, 5] :::>>> " + depthFirstSearch.diameter);

        depthFirstSearch.longestSequence(node, 1, node.getData());
        System.out.println("Tree:Longest Consecutive Sequence[6, 8, 3, 2, 4, 7, 9, 5] :::>>> " + depthFirstSearch.maxLength);

        System.out.println("Tree:Count :::>>> " + node.count());
        List<Integer> sums = new ArrayList<>();
        depthFirstSearch.findSumByLevel(sums, node, 0);
        System.out.println("Tree:Sum by Level :::>>> ");
        sums.stream().forEach(sum -> System.out.print(sum + " "));
        depthFirstSearch.findAverageByLevel(sums);
        System.out.println("");
        System.out.println("Tree:Average by Level :::>>> ");
        sums.stream().forEach(sum -> System.out.print(sum + " "));

        Tree.Node tree1 = new Tree.Node(6);
        tree1.insert(8);
        tree1.insert(3);
        tree1.insert(2);
        tree1.insert(4);
        tree1.insert(7);
        tree1.insert(9);

        Tree.Node tree2 = new Tree.Node(6);
        tree2.insert(8);
        tree2.insert(3);
        tree2.insert(2);
        tree2.insert(4);
        tree2.insert(7);
        tree2.insert(9);

        System.out.println("Are the Trees Same? :::>>> " + depthFirstSearch.areSame(tree1, tree2, true));

        System.out.println("Are the Trees[1 & 2] Symmetric? :::>>> " + depthFirstSearch.areSymmetric(tree1.getLeft(), tree2.getRight(), true));

        System.out.println("Find Closest Value on Tree[8, 3, 2, 4, 7, 9] From 1.4925699  :::>>> " + depthFirstSearch.closestValue(tree1, 1.4925699));
        depthFirstSearch.closestNode = 0;
        depthFirstSearch.diff = Double.MAX_VALUE;
        System.out.println("Find Closest Value on Tree[8, 3, 2, 4, 7, 9] From 4.8925699  :::>>> " + depthFirstSearch.closestValue(tree1, 4.8925699));
        depthFirstSearch.closestNode = 0;
        depthFirstSearch.diff = Double.MAX_VALUE;
        System.out.println("Find Closest Value on Tree[8, 3, 2, 4, 7, 9] From 6.600099  :::>>> " + depthFirstSearch.closestValue(tree1, 6.600099));
        depthFirstSearch.closestNode = 0;
        depthFirstSearch.diff = Double.MAX_VALUE;
        System.out.println("Find Closest Value on Tree[8, 3, 2, 4, 7, 9, 5] From 4.8925699  :::>>> " + depthFirstSearch.closestValue(node, 4.8925699));
        depthFirstSearch.closestNode = 0;
        depthFirstSearch.diff = Double.MAX_VALUE;
        System.out.println("Find Closest Value on Tree[8, 3, 2, 4, 7, 9, 5] From 7.5  :::>>> " + depthFirstSearch.closestValue(node, 7.5));
        depthFirstSearch.closestNode = 0;
        depthFirstSearch.diff = Double.MAX_VALUE;
        System.out.println("Find Closest Value on Tree[8, 3, 2, 4, 7, 9, 5] From 9  :::>>> " + depthFirstSearch.closestValue(node, 9));
        depthFirstSearch.closestNode = 0;
        depthFirstSearch.diff = Double.MAX_VALUE;
        System.out.println("Find Closest Value on Tree[8, 3, 2, 4, 7, 9, 5] From 9  :::>>> " + depthFirstSearch.closestValue(node, 6));

        Tree.Node tree3 = new Tree.Node(1);
        tree3.insert(2);
        tree3.insert(2);
        tree3.insert(3);
        tree3.insert(3);
        tree3.insert(4);
        tree3.insert(4);

        System.out.println("Tree:Count[6, 8, 3, 2, 4, 7, 9, 5] :::>>> " + depthFirstSearch.count(node));

        System.out.println("Tree:Balanced[6, 8, 3, 2, 4, 7, 9, 5] :::>>> " + depthFirstSearch.isBalanced(node));
        System.out.println("Tree:Balanced[1,2,2,3,3,null,null,4,4] :::>>> " + depthFirstSearch.isBalanced(tree3));
        System.out.println("Tree:Balanced[6, 8, 3, 2, 4, 7, 9] :::>>> " + depthFirstSearch.isBalanced(tree1));

        List<String> paths = new ArrayList<>();
        Tree.Node tree4 = new Tree.Node(6);
        tree4.insert(3);
        tree4.insert(8);
        tree4.insert(4);
        tree4.insert(2);
        tree4.insert(7);
        depthFirstSearch.tracePaths(tree4, tree4, 0, tree4.getData() + "", paths);
        paths.stream().forEach(path -> System.out.print(path + " "));
    }

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
    public void findSumByLevel(List<Integer> sums, Tree.Node node, int i) {

        if (node == null) {
            return;
        } else {
            if (sums.size() > i) {
                sums.set(i, sums.get(i) + node.getData());
            } else {
                sums.add(i, node.getData());
            }
            i++;
        }
        findSumByLevel(sums, node.getLeft(), i);
        findSumByLevel(sums, node.getRight(), i);
    }

    private void findAverageByLevel(List<Integer> sums) {

        for (int i = 0; i < sums.size(); i++) {

            sums.set(i, sums.get(i) / 2);
        }
    }

    private boolean areSame(Tree.Node left, Tree.Node right, boolean isSame) {

        if(!isSame) {
            return isSame;
        }
        if (left != null && right != null && left.getData() == right.getData()) {
            isSame = areSame(left.getLeft(), right.getLeft(), isSame);
            isSame = areSame(left.getRight(), right.getRight(), isSame);
        } else if (left == null && right == null) {
            return isSame;
        } else {
            return false;
        }
        return isSame;
    }

    private boolean areSymmetric(Tree.Node tree1, Tree.Node tree2, boolean areEqual) {
        if(!areEqual) {
            return areEqual;
        }
        if (tree1 != null && tree2 != null && tree1.getData() == tree2.getData()) {
            areEqual = areSymmetric(tree1.getLeft(), tree2.getRight(), areEqual);
            areEqual = areSymmetric(tree1.getRight(), tree2.getLeft(), areEqual);
        } else if (tree1 == null && tree2 == null) {
            return true;
        } else {
            return false;
        }
        return areEqual;
    }

    public int closestValue(Tree.Node root, double target) {

        return findClosestValue(root, target);
    }

    int closestNode = 0;
    double diff = Double.MAX_VALUE;
    private int findClosestValue(Tree.Node node, double target) {
        if (node == null) {
            return closestNode;
        } else if (node.getData() == target) {
            closestNode = node.getData();
            return closestNode;
        } else {
            if (Math.abs(target - node.getData()) < diff) {
                diff = Math.abs(target - node.getData());
                closestNode = node.getData();
            }
            findClosestValue(node.getLeft(), target);
            findClosestValue(node.getRight(), target);
            return closestNode;
        }
    }


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
    public int count(Tree.Node node) {

        if (node == null) {
            return 0;
        }

        return count(node.getLeft()) + count(node.getRight()) + 1;
    }

    public boolean isBalanced(Tree.Node root) {

        if (root == null) {
            return true;
        }
        int left = count(root.getLeft());
        int right = count(root.getRight());

        return left == right;
    }

    private void tracePaths(Tree.Node root, Tree.Node node, int index, String path, List<String> paths) {

        if (node.getLeft() == null && node.getRight() == null) {
            paths.add(path);
        }
        if (node.getLeft() != null) {
            String leftPath = path + "->" + node.getLeft().getData();
            tracePaths(root, node.getLeft(), index, leftPath, paths);
        }
        if (node.getRight() != null) {
            String rightPath = path + "->" + node.getRight().getData();
            tracePaths(root, node.getRight(), index, rightPath, paths);
        }
    }

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
    int diameter = 0;
    private int longestPath(Tree.Node node) {

        if (node == null) {
            return 0;
        }
        int leftPath = longestPath(node.getLeft());
        int rightPath = longestPath(node.getRight());

        diameter = Math.max(diameter, leftPath + rightPath);
        return Math.max(leftPath, rightPath) + 1;
    }

    int maxLength = 0;
    private void longestSequence(Tree.Node node, int longestSequence, int lastNumber) {

        if (node == null) {
            return;
        }

        if (node.getData() == lastNumber + 1) {
            lastNumber++;
            longestSequence++;
        } else {
            lastNumber = node.getData();
            longestSequence = 1;
        }
        maxLength = Math.max(longestSequence, maxLength);
        longestSequence(node.getLeft(), longestSequence, lastNumber);
        longestSequence(node.getRight(), longestSequence, lastNumber);
    }

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

    /**
     *  3, 7, 0
     *
     *  6,  0 --  6
     *  3,  6 --  9
     *  2,  9 --  9
     *  4,  9 -- 13
     *  5, 13 -- 18
     *  8, 18 -- 18
     *  7, 18 -- 25
     *  9, 25 -- 25
     */
    private long sum;
    private void sumOfNodesInRange(Tree.Node node, int low, int high) {

        if (node == null) {
            return;
        }

        if (node.getLeft() != null) {
            sumOfNodesInRange(node.getLeft(), low, high);
        }

        int data = node.getData();
        if (data >= low && data <= high) {
            sum += data;
        }

        if (node.getRight() != null) {
            sumOfNodesInRange(node.getRight(), low, high);
        }
    }

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
    Integer previous = null;
    private boolean validBinarySearchTree(Tree.Node node) {

        if (node == null) {
            return true;
        }

        if (!validBinarySearchTree(node.getLeft())) {
            return false;
        }
        if (previous != null && node.getData() <= previous) {
            return false;
        }
        previous = node.getData();
        return validBinarySearchTree(node.getRight());
    }
}