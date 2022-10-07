/**
 * A SinglyLinkedClass for CSDS 233 hw 2
 * @author Chaehyeon Kim cxk445
 */

public class SinglyLinkedList {
    
    private LLNode<Integer> node;
    private LLNode<Integer> next;

    public SinglyLinkedList () {
        this.node = null;
        this.next = null;
    }

    public LLNode<Integer> getNext() {
        return next;
    }

    public void setNext(LLNode<Integer> nextNode) {
        next.setNext(nextNode);
    }

    public LLIterator<Integer> iterator() {
        return new LLIterator<Integer>(node);
    }

    /**
     * A method that is invoked on a list object and reverse the list using no additional lists
     * (Got help from the hw2 office hour session)
     */
    public void reverse() {
        LLIterator<Integer> it = iterator();
        // variables to keep track of the former and latter nodes
        LLNode<Integer> prev = null;
        LLNode<Integer> current = node;
        LLNode<Integer> next = null;
        while (it.hasNext()) { // iterating through the whole list and reversing
            next = current.getNext();
            current.setNext(prev); // now do this with an iterator?
            prev = current;
            current = next;
        }
    }

    /**
     * A nested iterator class for SinglyLinkedList, LLIterator.
     */
    public class LLIterator <G> {

        /** A pointer to the head node of the list */
        private LLNode<Integer> pointer;

        /**
         * The constructor for the LLIterator class.
         * @param node the head node of the list.
         */
        public LLIterator (LLNode<Integer> node) {
            this.pointer = node;
        }
        
        /**
         * Returns true if the iteration has more elements and throw NullPointerException if null.
         * @return true if the iteration has more elements.
         */
        public boolean hasNext() throws NullPointerException {
            return (pointer != null);
        }

        /**
         * Returns the next element in the iteration and move the pointer to the next node.
         * @return the next element in the iteration.
         */
        public int next() {
            if (!hasNext())
                throw new NullPointerException();
            int save = pointer.getValue();
            pointer = pointer.getNext();
            return save;
        }
    }
}
