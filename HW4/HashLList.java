import java.util.NoSuchElementException;

/**
 * A singly Linked List class for the HashTable class using separate chaining
 * @author Chaehyeon Kim
 */
public class HashLList {
   
    /** private field storing the head of this linked list */
    private Node head;
    /** private field storing the number of items stored in this linked list */
    private int numItem;

    /**
     * Consctructor for the HashLList class
     */
    public HashLList() {
        this.head = null;
        this.numItem = 0;
    }

    /**
     * Getter method for the numItem field
     * @return number of items stored in this list
     */
    public int getNumItem() {
        return numItem;
    }

    /**
     * Adds the input String after the current last node of this list
     * @param str value of the node to be added at the end of this list
     */
    public void addLast(String str) {
        Node trav = head; // node to traverse the list
        if (trav == null) // make the new node to be the head if the list was empty
            head = new Node(str);
        else { // list was not empty
            while (trav.next != null) { // traverse while the next node is not null
                trav = trav.next;
            }
            trav.next = new Node(str); // set the next node of trav to be the new node containing the input String
        }
        numItem++;
    }

    /**
     * Deletes a node containing the input String value
     * @param str the item of the node to be deleted from this list
     * @return whether the list goes from not null to null
     * @throws Exception thrown when there is no node containing the input String value
     */
    public boolean delete(String str) throws NoSuchElementException {
        Node trav = head; // node to traverse the list
        if (trav == null) // the list is empty
            throw new NoSuchElementException("The list is empty.");
        else {
            boolean nodeDeleted = false;
            String travItem = trav.item.toLowerCase(); // the item at trav
            String strLower = str.toLowerCase(); // input string with lowered case
            boolean returnThis = false; // true if list goes from not null to null; false otherwise
            if (trav == head) { // trav is at head of the list
                if (travItem.equals(strLower)) { // head to be deleted
                    head = trav.next;
                    nodeDeleted = true; // while loop below will not be executed
                    if (head == null)
                        returnThis = true; // list goes from not null to null
                }
            }
            while (!nodeDeleted && trav.next != null) { // traverses the list to delete the node
                if (travItem.equals(strLower)) { // node to be deleted found
                    trav.next = trav.next.next;
                    nodeDeleted = true;
                }
                trav = trav.next;
            }
            if (!nodeDeleted) // node was not deleted b/c no node containing str was in the list
                throw new NoSuchElementException();
            else // node was deleted
                numItem--;
            return returnThis; 
        }
    }

    /**
     * Returns a new Iterator for the list
     * @return a new Iterator for the list
     */
    public LIterator iterator() {
        return new LIterator();
    }

    /**
     * An iterator class for the HashLList class
     */
    public class LIterator {

        /** private field for the trav node */
        private Node nextNode;

        /**
         * Constructor for LIterator class
         */
        private LIterator() {
            nextNode = head;
        }

        /**
         * Returns whether the list has a node next
         * @return whether the list has a node next
         */
        public boolean hasNext() {
            return (nextNode != null);
        }

        /**
         * Moves the pointer to the next node in the list
         * @return the String value contained in the current node
         */
        public String next() {
            if (nextNode == null) // throw an exception if the next node is null
                throw new NullPointerException();
            String str = nextNode.item;
            nextNode = nextNode.next;
            return str;
        }
    }

    /**
     * A Node class for HashLList class
     */
    private class Node {
        /** Private field containing the item stored in this node */
        private String item;
        /** Private field containing the pointer to the next node in the list */
        private Node next;

        /**
         * Constructor for Node class
         * @param item value the node contains
         */
        private Node(String item) {
            this.item = item;
            next = null;
        }
    }
}
