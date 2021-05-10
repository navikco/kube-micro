package net.playdoh.algo;

import net.playdoh.datastructures.Tree;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearch {

    public static void main(String[] args) {

        DepthFirstSearch depthFirstSearch = new DepthFirstSearch();

        Tree.Node node = new Tree.Node(6);
        node.insert(8);
        node.insert(3);
        node.insert(2);
        node.insert(4);
        node.insert(7);
        node.insert(9);

        List<Integer> sums = new ArrayList<>();
        depthFirstSearch.findSumByLevel(sums, node, 0);
        System.out.println("Tree:Sum by Level :::>>> ");
        sums.stream().forEach(sum -> System.out.print(sum + " "));
        depthFirstSearch.findAverageByLevel(sums);
        System.out.println("");
        System.out.println("Tree:Average by Level :::>>> ");
        sums.stream().forEach(sum -> System.out.print(sum + " "));
    }

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
}