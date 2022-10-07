import java.util.NoSuchElementException;

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

    public Integer removeFirst() throws NoSuchElementException {
        if (node == null)
            throw new NoSuchElementException();
        int save = node.getValue();
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
     */
    public int add(Integer e) {
        LLNode<Integer> end = node;
        LLIterator<Integer> it = iterator();
        if (!it.hasNext())
            node = new LLNode<Integer>(e);
        else {
            it.next();
            while (it.hasNext()) {
                end = end.getNext();
                it.next();
            }
            end.setNext(new LLNode<Integer>(e));
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
