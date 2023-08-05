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
public class CountNodes {
    public static void main(String[] args) {
        BstTreeNode root = new BstTreeNode(5);
        root.insert(root, 2);
        root.insert(root, 4);
        root.insert(root, 8);
        root.insert(root, 6);
        root.insert(root, 7);
        root.insert(root, 3);
        root.insert(root, 9);

        CountNodes cn = new CountNodes();
        //System.out.println(cn.countNodes(root));
        //cn.inorder(root);
        //System.out.println(cn.countLeaves(root));
        //System.out.println(cn.countFullNodes(root));
        System.out.println(cn.height(root));

    }

    int height(BstTreeNode root){
        if(root == null){
            return 0;
        }
        // if it is leaf node
        if(root.left == null && root.right == null){
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    int countFullNodes(BstTreeNode root){
        int f = 0;
        if(root == null){
            return 0;
        }
        if(root.left != null && root.right != null){
            f = 1;
        }
        return f + countFullNodes(root.left)+ countFullNodes(root.right);
    }

    int countLeaves(BstTreeNode root){
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return 1;
        }
        return countLeaves(root.left)+ countLeaves(root.right);
    }

    int countNodes(BstTreeNode root){
        if(root == null){
            return 0;
        }
        return 1 + countNodes(root.left)+ countNodes(root.right);
    }

    void inorder(BstTreeNode root){
        if(root != null){
            inorder(root.left);
            System.out.print(root.data + "  ");
            inorder(root.right);
        }
    }

}
