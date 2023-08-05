package datastructures.trees;

public class BstTreeNode {
    int data;
    BstTreeNode left;
    BstTreeNode right;


    public BstTreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    void insert(BstTreeNode node, int data){
        if(data < node.data){
            if(node.left != null){
                insert(node.left, data);
            }else {
                node.left = new BstTreeNode(data);
            }
        }else {
            if(node.right != null){
                insert(node.right, data);
            }else {
                node.right = new BstTreeNode(data);
            }
        }
    }
}
