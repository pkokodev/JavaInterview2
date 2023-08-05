package datastructures.trees;

/*
                  5
               /    \
              2      8
               \    / \
                4  6   9
               /    \
              3      7


 */
public class BST {
    public static void main(String[] args) {
        BstTreeNode root = new BstTreeNode(5);
        root.insert(root, 2);
        root.insert(root, 4);
        root.insert(root, 8);
        root.insert(root, 6);
        root.insert(root, 7);
        root.insert(root, 3);
        root.insert(root, 9);

        BST cn = new BST();
        //System.out.println(cn.min(root));
        //System.out.println(cn.max(root));
        cn.delete(root, 2);
}


    private int delete(BstTreeNode root, int data) {

        if(root== null){
            return -1;
        }

        // found node that is to be deleted
        if(root.data == data){
            // leaf node
            if(root.left == null && root.right == null){
                root = null;
                System.out.println("Leaf Node deleted");
                return 1;
            }
            // Non-leaf node
            // One child
            if(root.left == null){
                root = root.right;
                System.out.println("Deleted & New node is : " + root.data);
                return 1;
            }
            if(root.right == null){
                root = root.left;
                System.out.println("Deleted & New node is : " + root.data);
                return 1;
            }
            // Two child : leftmost node (max) in right subtree or rightmost node in left subtree (min)
            root = maxNode(root.left);
            System.out.println("Deleted & New node is : " + root.data);
            return root.data;
        }
        if(data < root.data)
        {
           return delete(root.left, data);
        }else {
            return delete(root.right, data);
        }
    }

    private BstTreeNode maxNode(BstTreeNode root) {
        if(root== null){
            return null;
        }
        if (root.right == null){
            return root;
        }
        return maxNode(root.right);
    }
    private int max(BstTreeNode root) {
        if(root== null){
            return -1;
        }
        if (root.right == null){
            return root.data;
        }
        return max(root.right);
    }

    private int min(BstTreeNode root) {
        if(root== null){
            return -1;
        }
        if (root.left == null){
            return root.data;
        }
        return min(root.left);
    }
}
