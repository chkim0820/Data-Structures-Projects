import javax.xml.bind.TypeConstraintException;

/**
 * A DoublyLinkedList class for CSDS 233 HW 2
 * @author Chaehyeon Kim cxk445
 */

public class DoublyLinkedList {

    /** The head node of the list */
    private IntNode head;
    /** The tail node of the list */
    private IntNode tail;
    /** The size of the list (number of nodes) */
    private int size = 0;

    /**
     * A constructor for the DoublyLinkedList class
     */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    protected IntNode getHead() {
        return head;
    }

    protected IntNode getTail() {
        return tail;
    }

    /**
     * Creates a new Iterator for the instance
    */
    public Iterator iterator() {
        return new Iterator();
    }

    /**
     * Appends the specified element to the end of this list
     * @param element the specified element to be added at the end of the list
     */
    public void add(int element) {
        if (size == 0) {
            head = new IntNode(element);
            tail = head;
        }
        else {
            IntNode newNode = new IntNode(element);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    /**
    * A method that is invoked on a list object and reverse the list using no additional lists
    */
    public void reverse() throws NullPointerException{ // check if working with my own node class is okay
        if (head == null)
            throw new NullPointerException();
        else if (size > 1) {
            IntNode headTrav = head;
            IntNode tailTrav = tail;
            for (int i = 0; i < size/2; i++) { // while every node is swapped
                IntNode saveFront = headTrav.getNext();
                IntNode saveBack = tailTrav.getPrev();
                headTrav.setNext(tailTrav.getNext());
                tailTrav.setPrev(headTrav.getPrev());
                headTrav.setPrev(tailTrav.getPrev());
                tailTrav.setNext(headTrav.getNext());
                headTrav.getNext().setPrev(saveBack); // iterator? ask TA what the benefit would be & what the implementation would look like 
                tailTrav.getPrev().setNext(saveFront);


            headTrav = headTrav.getNext();
            tailTrav = tailTrav.getPrev();
        }
    }

    /**
     * An Iterator class for the DoublyLinkedList class; for no direct modification of the modes(?)
     */
    public class Iterator { // inside DLL class so things can be accessed from inside; double check

        /** variable for the pointer of the next node */
        private IntNode nextNode; // iterates through the all nodes of the list
        /** variable for the pointer of the previous node */
        private IntNode prevNode;

        /**
         * A constructor for the Iterator class
         */
        public Iterator() {
            nextNode = head;
            prevNode = tail;
        }

        /**
         * returns the next of the node the pointer is currently on
         * @return the next of the node the pointer is currently on
         */
        public int next() {
            if (nextNode == null)
                throw new NullPointerException();
            int i = nextNode.getValue();
            nextNode = nextNode.getNext();
            return i; 
        }

        /**
         * returns whether there is a next node
         * @return whether there is a next node
         */
        public boolean hasNext() {
            return (nextNode != null);
        }

        /**
         * return the previous of the node the pointer is currently on
         * @return the previous of the node the pointer is currently on
         */
        public int previous() {
            if (prevNode == null)
                new NullPointerException();
            int i = prevNode.getValue();
            prevNode = prevNode.getPrev();
            return i;
        }

        /**
         * return whether there is a previous node
         * @return whether there is a previous node
         */
        public boolean hasPrevious() {
            return (prevNode != null);
        }
    }
}