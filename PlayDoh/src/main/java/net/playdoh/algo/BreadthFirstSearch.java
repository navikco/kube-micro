package net.playdoh.algo;

import net.playdoh.datastructures.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {

    public static void main(String[] args) {

        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();

        Tree.Node node = new Tree.Node(6);
        node.insert(8);
        node.insert(3);
        node.insert(2);
        node.insert(4);
        node.insert(7);
        node.insert(9);
        node.insert(5);

        List<Integer> sums = new ArrayList<>();
        breadthFirstSearch.findSumByLevel(sums, node);
        System.out.println("Tree:Sum by Level :::>>> ");
        sums.stream().forEach(sum -> System.out.print(sum + " "));
        breadthFirstSearch.findAverageByLevel(sums);
        System.out.println("");
        System.out.println("Tree:Average by Level :::>>> ");
        sums.stream().forEach(sum -> System.out.print(sum + " "));

        System.out.println("");
        System.out.println("Tree:NodesByLevel [6, 8, 3, 2, 4, 7, 9, 5] ");
        List<List<Integer>> results = breadthFirstSearch.getNodesByLevel(node);
        results.stream().forEach(result -> System.out.println(result));
    }

    public void findSumByLevel(List<Integer> sums, Tree.Node node) {

        if (node == null) {
            return;
        }
        Queue<Tree.Node> queue = new LinkedList();
        queue.add(node);
        while (!queue.isEmpty()) {

            int queueSize = queue.size();
            int currentLevelSum = 0;
            for (int i = 0; i < queueSize; i++) {
                Tree.Node queueNode = queue.remove();
                currentLevelSum += queueNode.getData();

                if (queueNode.getLeft() != null) {
                    queue.add(queueNode.getLeft());
                }
                if (queueNode.getRight() != null) {
                    queue.add(queueNode.getRight());
                }
            }
            sums.add(currentLevelSum);
        }
    }

    private void findAverageByLevel(List<Integer> sums) {

        for (int i = 0; i < sums.size(); i++) {

            sums.set(i, sums.get(i) / 2);
        }
    }

    /**
     *          6           --      6
     *
     *        /  \
     *
     *      3     8         --     11
     *
     *    /  \  /  \
     *
     *  2    4 7    9       --     22
     *
     *        \
     *
     *         5            --      5
     */
    public List<List<Integer>> getNodesByLevel(Tree.Node node) {

        List<List<Integer>> results = new ArrayList<>();

        if (node == null) {
            return results;
        }

        Queue<Tree.Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            List<Integer> currentLevel = new ArrayList<>();
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                Tree.Node currentNode = queue.remove();
                currentLevel.add(currentNode.getData());
                if (currentNode.getLeft() != null) {
                    queue.add(currentNode.getLeft());
                }
                if (currentNode.getRight() != null) {
                    queue.add(currentNode.getRight());
                }
            }
            results.add(currentLevel);
        }
        return results;
    }
}