import javax.lang.model.util.ElementScanner6;

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
        root = insertRecursive(root, key); // calls the recursive helper method below
    }

    /**
     * A helper method for insert() that traverses the tree and insert a node recursively
     * @param node node to be compared
     * @return new node to be inserted at the base case or the current trav node
     */
    public Node insertRecursive(Node node, int key) {
        if (node == null) // base case; if the node is null
            return new Node(key);
        if (node.getKey() <= key)
            node.setRight(insertRecursive(node.getRight()));
        else if (node.getKey() > key)
            node.setLeft(insertRecursive(node.getLeft()));
        return node;
    }
    // choose the more efficient out of the two later
    /**
     * Insert method iterative for the BST
     * @param key key of the node to be inserted
     */
    public void insertIterative(int key) { // iterative; try to make one with recursion
        if (root == null) // if the tree is empty
            root = new Node(key);
        else { // tree not empty
            Node trav = root;
            Node parent = null;
            int leftRight = 0; // -1 if to parent's left, 1 if to parent's right
            while (trav != null) {
                if (trav.getKey() <= key) {
                    parent = trav;
                    trav = trav.getRight();
                    leftRight = 1;
                }
                else {
                    parent = trav;
                    trav = trav.getLeft();
                    leftRight = -1;
                }
            }
            if (leftRight < 0)
                parent.setLeft(key);
            else if (leftRight > 0)
                parent.setRight(key);
            else
                System.out.print("something is wrong with insert()"); // erase later
        }
    }
    
    /**
     * Post-order traversal of BST
     * @param node the root node of the BST to be traversed post-orderly
     */
    public void postorder(Node node) {
        if (node.getLeft() != null)
            postorder(node.getLeft());
        if (node.getRight() != null)
            postorder(node.getRight());
        System.out.print(node.getKey());
    }

    /**
     * In-order traversal of BST
     * @param node the root node of the BST to be traversed in-orderly
     */
    public void inorder(Node node) {
        if (node.getLeft() != null)
            inorderRecursive(node.getLeft());
        System.out.print(node.getKey());
        if (node.getRight() != null)
            inorderRecursive(node.getRight());
    }

    /**
     * Pre-order traversal of BST
     * @param node the root node of the BST to be traversed pre-orderly
     */
    public void preorder(Node node) {
        System.out.print(node.getKey());
        if (node.getLeft() != null)
            inorderRecursive(node.getLeft());
        if (node.getRight() != null)
            inorderRecursive(node.getRight());
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
            Node parent = null;
            Node trav = root;
            while (trav != null) {
                if (key == trav.getKey())
                    return true;
                else if (key > trav.getKey()) {
                    parent = trav;
                    trav = trav.getRight();
                }
                else if (key < trav.getKey()) {
                    parent = trav;
                    trav = trav.getLeft();
                }
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
        if (search(key)) {
            
        }
    }
}
