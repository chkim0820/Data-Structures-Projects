import javax.xml.bind.TypeConstraintException;

/**
 * A DoublyLinkedList class for CSDS 233 HW 2
 * @author Chaehyeon Kim cxk445
 */

public class DoublyLinkedList {

    private IntNode head;
    private IntNode tail;

    /**
    * A method that is invoked on a list object and reverse the list using no additional lists
    */
    public void reverse() { // check if working with my own node class is okay
        if (head instanceof IntNode) {
            Iterator iter = new Iterator();
            int size = 0;
            while (iter.hasNext()) {
                iter.next();
                size++; // calculates the number of nodes in the list
            }
            int i = size;
            while (i == size/2) { // while every node is swapped
                IntNode save = head;
                head.getNext().setPrev(tail); // iterator? ask TA what the benefit would be & what the implementation would look like 
                head.setNext(head.getNext());
                tail.getPrev().setNext(save);
                tail.setPrev(tail.getPrev());
                head = head.getNext();
                tail = tail.getNext();
            }

        }
        else
            throw new TypeConstraintException("The reverse() function can only operate on a node");
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