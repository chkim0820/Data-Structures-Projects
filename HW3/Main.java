/**
 * Main class containing the main method for BST_Class class
 * @author Chaehyeon Kim cxk445
 */
public class Main {

    /**
     * Main method for BST_Class
     * @param args argumetn for main method
     */
    public static void main(String[] args) {
        // an instance of BST_Class for this main method
        BST_Class bst = new BST_Class();

        // insert nodes to the instance of BST_Class
        bst.insert(7);
        bst.insert(10);
        bst.insert(12);
        bst.insert(45);
        bst.insert(50);
        bst.insert(90);

        System.out.print("The BST created with input data(left-root-right):\n");
        bst.inorder(bst.root);
        System.out.println("\nThe BST after Delete 12(leaf node):");
        bst.deleteKey(12);
        bst.inorder(bst.root);
        System.out.println("\nThe BST after Delete 90 (node with 1 child):");
        bst.deleteKey(90);
        bst.inorder(bst.root);
        System.out.println("\nThe BST after Delete 45 (node with two children):");
        bst.deleteKey(45);
        bst.inorder(bst.root);
        System.out.println("\nKey 50 found in BST: " + bst.search(50)); // would this work for boolean values or other primitive types like int
        System.out.println("Key 12 found in BST: " + bst.search(12));
        // resetting the tree for the next part
        bst.deleteKey(7);
        bst.deleteKey(10);
        bst.deleteKey(50);
        bst.insert(45);
        bst.insert(10);
        bst.insert(7);
        bst.insert(12);
        bst.insert(90);
        System.out.println("BST => PreOrder Traversal:");
        bst.preorder(bst.root);
        System.out.println("\nBST => InOrder Traversal:");
        bst.inorder(bst.root);
        System.out.println("\nBST => PostOrder Traversal:");
        bst.postorder(bst.root);
    }
}
