package com.example.accessingdatamysql;

import java.util.*;

class TreeNode {
    int val;
    List<TreeNode> children;

    public TreeNode(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TreeNode [val=");
		builder.append(val);
		builder.append(", children=");
		builder.append(children);
		builder.append("]");
		return builder.toString();
	}
    
}

public class TreeBuilder {

    public static TreeNode buildTree(int[][] edges) {
        Map<Integer, List<Integer>> nodeMap = new HashMap<>();
        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];
            nodeMap.putIfAbsent(parent, new ArrayList<>());
            nodeMap.get(parent).add(child);
        }
        return buildTreeHelper(nodeMap, 1);
    }

    private static TreeNode buildTreeHelper(Map<Integer, List<Integer>> nodeMap, int nodeVal) {
        TreeNode node = new TreeNode(nodeVal);
        List<TreeNode> children = new ArrayList<>();
        if (nodeMap.containsKey(nodeVal)) {
            for (int childVal : nodeMap.get(nodeVal)) {
                TreeNode childNode = buildTreeHelper(nodeMap, childVal);
                children.add(childNode);
            }
        }
        node.children = children;
        return node;
    }

    public static void main(String[] args) {
//        int[][] edges = {{2, 1}, {1, 3}, {2, 4}, {2, 5}, {3, 6}};
//        int[][] edges= {{4,5},{5,3},{1,5},{2,1}};
		int[][] edges= {{4,5},{5,3},{1,5},{2,5}};
//		int[][] edges= {{4,5},{5,3},{1,2},{1,5},{2,5}};
        TreeNode root = buildTree(edges);
        System.out.println(root); // prints 1
//        System.out.println(root.children.get(0).val); // prints 2
//        System.out.println(root.children.get(1).val); // prints 3
//        System.out.println(root.children.get(0).children.get(0).val); // prints 4
//        System.out.println(root.children.get(0).children.get(1).val); // prints 5
//        System.out.println(root.children.get(1).children.get(0).val); // prints 6
    }
}
