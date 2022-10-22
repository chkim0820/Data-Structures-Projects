public class Main {

    public static void main(String[] args) {
        BST_Class bst = new BST_Class();

        bst.insert(7);
        bst.insert(10);
        bst.insert(12);
        bst.insert(45);
        bst.insert(50);
        bst.insert(90);

        System.out.print("The BST created with input data(left-root-right):");
        bst.inorder(bst.root);
        System.out.println("The BST after Delete 12(leaf node):");
        bst.deleteKey(12);
        bst.inorder(bst.root);
        System.out.println("The BST after Delete 90 (node with 1 child):");
        bst.deleteKey(90);
        bst.inorder(bst.root);
        System.out.println("The BST after Delete 45 (node with two children):");
        bst.deleteKey(45);
        bst.inorder(bst.root);
        System.out.println("Key 50 found in BST: " + bst.search(50)); // would this work for boolean values or other primitive types like int
        System.out.println("Key 12 found in BST: " + bst.search(12));
        System.out.println("BST => PreOrder Traversal:");
        bst.preorder(bst.root);
        System.out.println("BST => InOrder Traversal:");
        bst.inorder(bst.root);
        System.out.println("BST => PostOrder Traversal:");
        bst.postorder(bst.root);
    }
}
