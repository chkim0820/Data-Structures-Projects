/**
 * A class for CSDS 233 hw 3
 * @author Chaehyeon Kim cxk445
 */
public class BST_Class {

    Node root;

    public BST_Class() {
        this.root = null;
    }

    /**
     * Inserts a node in the binary search tree (BST)
     * @param key key of the node to be inserted
     */
    public void insert(int key) {
        root = insertRecursive(root, key); // calls the recursive helper method, insertRecursive, below
    }

    /**
     * A helper method for insert() that traverses the tree and insert a node recursively
     * @param node node to be compared
     * @return new node to be inserted at the base case or the current trav node
     */
    private Node insertRecursive(Node node, int key) {
        if (node == null) // base case; if the node is null
            return new Node(key);
        if (node.getKey() <= key)
            node.setRight(insertRecursive(node.getRight(), key));
        else if (node.getKey() > key)
            node.setLeft(insertRecursive(node.getLeft(), key));
        return node;
    }
    // choose the more efficient out of the two later
    /**
     * Insert method iterative for the BST
     * @param key key of the node to be inserted
     */
    public void insertIterative(int key) { // iterative; try to make one with recursion
        Node trav = root;
        Node parent = null;
        while (trav != null) {
            parent = trav;
            if (trav.getKey() <= key)
                trav = trav.getRight();
            else
                trav = trav.getLeft();
        }
        if (parent == null) // the tree was empty
            root = new Node(key);
        else if (key < parent.getKey())
            parent.setRight(new Node(key));
        else
            parent.setLeft(new Node(key));
    }
    
    /**
     * Post-order traversal of BST
     * @param node the root node of the BST to be traversed post-orderly
     */
    public void postorder(Node node) {
        if (node == null)
            throw new NullPointerException("The tree is empty");
        if (node.getLeft() != null)
            postorder(node.getLeft());
        if (node.getRight() != null)
            postorder(node.getRight());
        System.out.print(node.getKey() + " ");
    }

    /**
     * In-order traversal of BST
     * @param node the root node of the BST to be traversed in-orderly
     */
    public void inorder(Node node) {
        if (node == null)
            throw new NullPointerException("The tree is empty");
        if (node.getLeft() != null)
            inorder(node.getLeft());
        System.out.print(node.getKey() + " ");
        if (node.getRight() != null)
            inorder(node.getRight());
    }

    /**
     * Pre-order traversal of BST
     * @param node the root node of the BST to be traversed pre-orderly
     */
    public void preorder(Node node) {
        if (node == null) 
            throw new NullPointerException("The tree is empty");
        System.out.print(node.getKey() + " ");
        if (node.getLeft() != null)
            preorder(node.getLeft());
        if (node.getRight() != null)
            preorder(node.getRight());
        }

    /**
     * Checks whether a given key exists in BST
     * @param key a key the method is searching for
     * @return whether a given key exists in BST
     */
    public boolean search(int key) {
        if (root == null)
            return false;
        else {
            Node trav = root;
            while (trav != null) {
                if (key == trav.getKey())
                    return true;
                else if (key > trav.getKey())
                    trav = trav.getRight();
                else if (key < trav.getKey())
                    trav = trav.getLeft();
            }
            return false;
        }
    }

    /**
     * Finds the smallest element in BST
     * @param root the root of the BST tree that will be searched through for the minimum value
     * @return the smallest element in BST
     */
    public int minValue(Node root) {
        Node trav = root;
        if (trav == null)
            throw new NullPointerException("The tree is empty");
        while (trav.getLeft() != null) {
            trav = trav.getLeft();
        }
        return trav.getKey();
    }

    /**
     * Deletes a node from BST
     * @param key a key of the node to be deleted
     */
    public void deleteKey(int key) {
        Node parent = null;
        Node trav = root;
        while (trav != null && trav.getKey() != key) { // move pointers
            parent = trav;
            if (key < trav.getKey())
                trav = trav.getLeft();
            else
                trav = trav.getRight();
        }
        if (trav != null)
            deleteNode(trav, parent);
    }

    /**
     * Helper method for deleteKey() method
     * @param trav node traversing the tree; node to be deleted
     * @param parent the parent node of the trav node
     */
    private void deleteNode(Node trav, Node parent) { // case 1 and 2; no child or one child
        if (trav.getRight() == null || trav.getLeft() == null) { // no child
            Node toDeleteChild = null; // variable to store the one child of the node to be replaced (if there's any)
            if (trav.getLeft() != null)
                toDeleteChild = trav.getLeft();
            else
                toDeleteChild = trav.getRight();
            // making the child be the left/right of parent node accordingly
            if (trav == root)
                root = toDeleteChild;
            else if (trav.getKey() < parent.getKey())
                parent.setLeft(toDeleteChild);
            else
                parent.setRight(toDeleteChild);
        }
        else { // case 3; have both left and right children
            int replacement = minValue(trav.getRight());
            deleteKey(replacement);
            trav.setKey(replacement);
        }
    }

    public class Node {
    
        int key;
        Node left; 
        Node right;
    
        public Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    
        public int getKey() {
            return key;
        }
    
        public Node getLeft() {
            return left;
        }
    
        public Node getRight() {
            return right;
        }
    
        public void setKey(int key) {
            this.key = key;
        }
    
        public void setLeft(Node left) {
            this.left = left;
        }
    
        public void setRight(Node right) {
            this.right = right;
        }
    }
}