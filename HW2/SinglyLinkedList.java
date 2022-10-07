/**
 * A SinglyLinkedClass used for nodes containing Integer values; for CSDS 233 hw 2
 * @author Chaehyeon Kim cxk445
 */
public class SinglyLinkedList {
    
    /** A field storing the head of the linked list */
    private LLNode<Integer> node;

    /**
     * A constructor for the SinglyLinkedList class
     */
    public SinglyLinkedList () {
        this.node = null;
    }

    /**
     * Removes the front node of the list
     * @return The value contained in the removed node
     * @throws NullPointerException thrown when there is no node to remove in the list
     */
    public Integer removeFirst() throws NullPointerException {
        if (node == null) // throw an exception in case of an empty list
            throw new NullPointerException();
        int save = node.getValue(); // saves the value to be returned
        node = node.getNext();
        return save;
    }

    /**
     * An iterator() method to return a newly-created Iterator for this linked list
     * @return a newly-created Iterator for this linked list
     */
    public LLIterator<Integer> iterator() {
        return new LLIterator<Integer>(node);
    }

    /**
     * Apends the specified element to the end of the list
     * @param e the element to be added at the end of the list
     * @return the element to be added at the end of the list
     */
    public int add(Integer e) {
        LLNode<Integer> end = node; // the node at the end of the list
        LLIterator<Integer> it = iterator(); // iterator for this list
        // if the list is empty, add a new node to the head node of the list
        if (!it.hasNext())
            node = new LLNode<Integer>(e);
        // if the list is not empty, traverse through the list and add a new node at the end
        else {
            it.next();
            while (it.hasNext()) { // traversing through the list
                end = end.getNext();
                it.next();
            }
            end.setNext(new LLNode<Integer>(e)); // setting a new node
        }
        return e;
    }

    /**
     * A method that is invoked on a list object and reverse the list using no additional lists
     */
    public void reverse() {
        LLIterator<Integer> it = iterator(); // creating an Iterator for this list
        // variables to keep track of the former and latter nodes
        LLNode<Integer> prev = null;
        LLNode<Integer> current = node;
        LLNode<Integer> next = null;
        while (it.hasNext()) { // iterating through the whole list and reversing
            it.next();
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        node = prev;
    }

    /**
     * A nested iterator class for SinglyLinkedList, LLIterator.
     */
    public class LLIterator <G> {

        /** A pointer to the head node of the list */
        private LLNode<G> pointer;

        /**
         * The constructor for the LLIterator class
         * @param node the head node of the list
         */
        public LLIterator (LLNode<G> node) {
            this.pointer = node;
        }
        
        /**
         * Returns true if the iteration has more elements
         * @return true if the iteration has more elements
         */
        public boolean hasNext() {
            return (pointer != null);
        }

        /**
         * Returns the next element in the iteration and move the pointer to the next node.
         * @return the next element in the iteration.
         */
        public int next() throws NullPointerException {
            if (!hasNext()) // throw an exception if the list is null
                throw new NullPointerException();
            int save = (int) pointer.getValue(); // variable to save the value of the pointer; converted to int b/c assumed to only use integers
            pointer = pointer.getNext(); // making the pointer to point at the next node
            return save;
        }
    }
}
