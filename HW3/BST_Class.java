/**
 * A Binary Search Tree class for CSDS 233 hw 3
 * @author Chaehyeon Kim cxk445
 */
public class BST_Class {

    /** The root node of the BST */
    Node root;

    /**
     * A constructor for an instance of BST_Class
     */
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
        if (node.getKey() <= key) // if new node's key is larger than/same as trav's key
            node.setRight(insertRecursive(node.getRight(), key));
        else if (node.getKey() > key) // if new node's key is smaller than trav's key
            node.setLeft(insertRecursive(node.getLeft(), key));
        return node;
    }

    /**
     * Post-order traversal of BST
     * @param node the root node of the BST to be traversed post-orderly
     */
    public void postorder(Node node) {
        if (node == null) // the tree is empty
            throw new NullPointerException("The tree is empty");
        // call postorder() recursively and print out values as appropriate
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
        if (node == null) // the tree is empty
            throw new NullPointerException("The tree is empty");
        // call inorder() recursively and print out values as appropriate
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
        if (node == null) // the tree is empty
            throw new NullPointerException("The tree is empty");
        // call preorder() recursively and print out values as appropriate
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
        if (root == null) // tree is empty
            return false;
        else {
            Node trav = root; // node to traverse the tree
            while (trav != null) { // traverses the tree to find a node with the input key
                if (key == trav.getKey()) // node with input key found
                    return true;
                else if (key > trav.getKey())
                    trav = trav.getRight();
                else if (key < trav.getKey())
                    trav = trav.getLeft();
            }
            return false; // loop ended (all nodes traversed) and input key not found; input key not in this tree
        }
    }

    /**
     * Finds the smallest element in BST
     * @param root the root of the BST tree that will be searched through for the minimum value
     * @return the smallest element in BST
     */
    public int minValue(Node root) {
        Node trav = root; // node to traverse the tree
        if (trav == null) // tree is empty
            throw new NullPointerException("The tree is empty");
        while (trav.getLeft() != null) { // going to the left-most node in the tree
            trav = trav.getLeft();
        }
        return trav.getKey();
    }

    /**
     * Deletes a node from BST
     * @param key a key of the node to be deleted
     */
    public void deleteKey(int key) {
        Node parent = null; // parent node of trav node
        Node trav = root; // node to traverse the tree
        while (trav != null && trav.getKey() != key) { // move trav through the key to find a node with the given key
            parent = trav;
            if (key < trav.getKey())
                trav = trav.getLeft();
            else
                trav = trav.getRight();
        }
        if (trav != null) // if tree not empty & key found in the tree
            deleteNode(trav, parent);
    }

    /**
     * Helper method for deleteKey() method
     * @param trav node traversing the tree; node to be deleted
     * @param parent the parent node of the trav node
     */
    private void deleteNode(Node trav, Node parent) { // case 1 and 2; no child or one child
        if (trav.getRight() == null || trav.getLeft() == null) { // no child
            Node toDeleteChild = null; // child of the node to be deleted (if there's any)
            if (trav.getLeft() != null) // child of the node to be deleted is on the left
                toDeleteChild = trav.getLeft();
            else // child on the right
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
            int replacement = minValue(trav.getRight()); // find the left-most node of the right subtree of the node to be deleted
            deleteKey(replacement);
            trav.setKey(replacement);
        }
    }

    /**
     * A nested Node class for BST_Class
     */
    public class Node {
    
        /** the key of the Node */
        int key;
        /** pointer to the left of the node */
        Node left; 
        /** pointer to the right of the node */
        Node right;
    
        /**
         * Constructor for Node class
         * @param key key of the node
         */
        public Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    
        /**
         * Getter method for key
         * @return key of this Node
         */
        public int getKey() {
            return key;
        }
    
        /**
         * Getter method for left
         * @return left of this Node
         */
        public Node getLeft() {
            return left;
        }
    
        /**
         * Getter method for right
         * @return right of this Node
         */
        public Node getRight() {
            return right;
        }
    
        /**
         * Setter method for key
         * @param key new key to this Node
         */
        public void setKey(int key) {
            this.key = key;
        }
    
        /**
         * Setter method for left
         * @param left new left to this Node
         */
        public void setLeft(Node left) {
            this.left = left;
        }
    
        /**
         * Setter method for right
         * @param right new right to this Node
         */
        public void setRight(Node right) {
            this.right = right;
        }
    }
}