/**
 * A Queue class built for CustomQStack class; for CSDS 233 hw2
 * @author Chaehyeon Kim cxk445
 */
public class Queue extends SinglyLinkedList {

    /** a field containing the linked list used to implement Queue ADT */
    private SinglyLinkedList list;

    /**
     * A constructor for the Queue class
     */
    public Queue () {
        this.list = new SinglyLinkedList();
    }

    /**
     * Inserts the specified element into this queue
     * @param e the value to be added to the queue
     * @return the value to be added to the queue
     */
    @Override
    public int add(Integer e) {
        int save = list.add(e);
        return new Integer(save);
    }

    /**
     * Retrieves and removes the head of this queue
     * @return the value to be removed
     */
    public Integer remove() {
        return list.removeFirst();
    }
}
