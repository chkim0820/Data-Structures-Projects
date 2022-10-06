/**
 * An Iterator class for the DoublyLinkedList class; for no direct modification of the modes(?)
 */

class Iterator { // inside DLL class so things can be accessed from inside; double check

    /** variable for the pointer of the next node */
    private IntNode nextNode; // iterates through the all nodes of the list
    /** variable for the pointer of the previous node */
    private IntNode prevNode;

    /**
     * A constructor for the Iterator class
     */
    public Iterator() {
        nextNode = getHead();
        prevNode = getTail();
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