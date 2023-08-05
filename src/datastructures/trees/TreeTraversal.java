package datastructures.trees;

import java.util.*;

/*

Two ways to travers any binary tree.
1. DFS:- Using Stack or Recursion Both cases T = O(n) S = O(n). Eg. Inorder, Preorder, Postorder
2. BFS:- Using Queue. T = O(n) S = O(n). Eg. Levelorder,




Construct the following tree
                   1
                 /   \
                /     \
               2       3
              /      /   \
             /      /     \
            4      5       6
                  / \
                 /   \
                7     8
*/
public class TreeTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);

        /*System.out.println(inOrder(root));
        System.out.println(preOrder(root));
        System.out.println(postOrder(root));
        System.out.println(countNodes(root));*/
        //levelOrder(root);
        //frontViewColWiseUtil(root);


        //Left view
        int[] arr = new int[4];

        leftView(root, arr, 0);
        System.out.println(Arrays.toString(arr));


    }

    public static void frontViewColWiseUtil(TreeNode root){
        TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();
        frontViewColWise(root, treeMap, 0);
        for (Map.Entry entry: treeMap.entrySet()){
            System.out.println(entry.getValue());
        }
    }

    private static void frontViewColWise(TreeNode root, TreeMap<Integer,List<Integer>> treeMap, int col) {
        if (root == null){
            return;
        }
        frontViewColWise(root.left, treeMap,col -1);
        if(treeMap.get(col) == null){
            List<Integer> list = new ArrayList<>();
            list.add(root.data);
            treeMap.put(col, list);
        }else {
            treeMap.get(col).add(root.data);
            treeMap.put(col, treeMap.get(col));
        }
        frontViewColWise(root.right, treeMap, col+1);
    }

    public static void topViewUtil(TreeNode root){
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        topView(root, treeMap, 0);
        for (Map.Entry entry: treeMap.entrySet()){
            System.out.println(entry.getValue());
        }
    }

    private static void topView(TreeNode root, TreeMap<Integer, Integer> treeMap, int col) {
        if (root == null){
            return;
        }
        topView(root.left, treeMap,col -1);
        if(col <= 0 && !treeMap.containsKey(col)){
            treeMap.put(col, root.data);
        }
        topView(root.right, treeMap, col+1);
        if(col > 0){
            treeMap.put(col, root.data);
        }
    }

    private static void mirrorTree(TreeNode root) {
        if(root == null){
            return;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        mirrorTree(root.left);
        mirrorTree(root.right);
    }

    private static void rightViewUtil(TreeNode root){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        rightView(root, list, 0);
        list.remove(list.size()-1);
        System.out.println(list);
    }
    // print last element at each level
    private static void rightView(TreeNode root, ArrayList<Integer> list, int level) {
        if(root == null){
            return;
        }
        if(list.get(level) == 0){
            list.set(level, root.data);
            list.add(0);
        }
        rightView(root.right, list, level+1);
        rightView(root.left, list, level+1);

    }

    //lefteview using  array
    private static void leftView(TreeNode root, int[] arr, int level) {
        if(root == null){
            return;
        }
        if(arr[level] == 0){
            arr[level] = root.data;
        }
        leftView(root.left, arr, level+1);
        leftView(root.right, arr, level+1);
        //interchange above two lines for right vi
    }

    private static void leftViewUtil(TreeNode root){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        leftView(root, list, 0);
        list.remove(list.size()-1);
        System.out.println(list);
    }
    // print first element at each level
    private static void leftView(TreeNode root, ArrayList<Integer> list, int level) {
        if(root == null){
            return;
        }
        if(list.get(level) == 0){
            list.set(level, root.data);
            list.add(0);
        }
        leftView(root.left, list, level+1);
        leftView(root.right, list, level+1);
    }
    //
    static void zigzagLevelOrder(TreeNode root, List<List<Integer>> list) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean isReverse = false;
        while (!queue.isEmpty()){
            List<Integer> list2 = new ArrayList<>();
            int noOfNodesAtlevel = queue.size();
            //Added for loop to get elements level wise
            for(int i = 0; i < noOfNodesAtlevel; i++){
                TreeNode cur = queue.poll();
                list2.add(cur.data);
                if(cur.left != null){
                    queue.add(cur.left);
                }
                if(cur.right != null){
                    queue.add(cur.right);
                }
            }

            if(isReverse) {
                Collections.reverse(list2);
            }
            isReverse = !isReverse;
            list.add(list2);
        }
    }


    // BFT -> Breadth First Traversal O(n)
    private static void reverseLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            stack.push(cur.data);
            if(cur.left != null){
                queue.add(cur.left);
            }
            if(cur.right != null){
                queue.add(cur.right);
            }
        }

        while (!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }
    }

    //
    static void levelOrderRecursiveMap(TreeNode root, HashMap<Integer, List<Integer>> map, int level) {
        if (root == null) {
            return;
        }
        List<Integer> list;
        if(map.get(level) == null){
            list = new ArrayList<>();
        }else {
            list = map.get(level);
        }
        list.add(root.data);
        map.put(level,list);
        levelOrderRecursiveMap(root.left, map, level + 1);
        levelOrderRecursiveMap(root.right, map, level + 1);

    }

    // BFT -> Breadth First Traversal O(n)
    private static void levelOrderQueue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            System.out.print(cur.data + " ");
            if(cur.left != null){
                queue.add(cur.left);
            }
            if(cur.right != null){
                queue.add(cur.right);
            }
        }
    }

    //O(n*n) using recursive if i = height then reverse level order
    private static void levelOrderRecursive(TreeNode root) {
        int height = height(root);
        for(int i = 0; i <= height; i++){
            nodesAtLevel(root, i+1);
        }
    }

    private static void nodesAtLevel(TreeNode root, int level){
        if(root == null){
            return;
        }
        if(level == 1){
            System.out.print(root.data + " ");
            return;
        }
        nodesAtLevel(root.left, level-1);
        nodesAtLevel(root.right, level-1);
    }

    private static int height(TreeNode root) {

        if (root == null) {
            return 0;
        }
        // if it is leaf node
        if(root.left == null && root.right == null){
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }
    private static int leafNodes(TreeNode root) {

        if (root == null) {
            return 0;
        }
        if(root.left == null && root.right == null){
            return 1;
        }
        return leafNodes(root.left) + leafNodes(root.right);
    }
    private static int sumOfNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return root.data + sumOfNodes(root.left) + sumOfNodes(root.right);
    }
    private static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }


    private static String postOrder(TreeNode root) {
        if(root== null){
            return "";
        }
        return postOrder(root.left) + postOrder(root.right) + " " + root.data + " ";
    }
    
    private static String preOrder(TreeNode root) {
        if(root== null){
            return "";
        }
        return " " + root.data + " " + preOrder(root.left) + preOrder(root.right);
    }
    private static String inOrder(TreeNode root) {
        if(root== null){
            return "";
        }
        return inOrder(root.left) + " " + root.data + " " + inOrder(root.right);
    }

    private static void inOrder2(TreeNode root) {
        if(root== null){
            return;
        }
        inOrder2(root.left);
        System.out.print("--> " + root.data);
        inOrder2(root.right);
    }
}
