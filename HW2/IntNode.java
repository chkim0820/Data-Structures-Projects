/**
 * An IntNode class for CSDS 233 HW 2
 * @author Chaehyeon Kim cxk445
 */
public class IntNode {

    /** the value of the node */
    private int value;
    /** the next node of this node */
    private IntNode next = null;
    /** the previous node of this node */
    private IntNode prev = null;

    /**
     * A constructor for the IntNode class with three inputs
     * @param value the value of the node
     * @param next the next node of this node
     * @param prev the previou node of this node
     */
    public IntNode(int value, IntNode next, IntNode prev) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }

    /**
     * A constructor for the IntNode class with value input
     * @param value the value of the node
     */
    public IntNode(int value) {
        this.value = value;
    }

    /**
     * returns the value of this node
     * @return the value of this node
     */
    public int getValue() {
        return value;
    }

    /**
     * returns the next node of this node
     * @return the next node of this node
     */
    public IntNode getNext() {
        return next;
    }

    /**
     * returns the previous node of this node
     * @return the previous node of this node
     */
    public IntNode getPrev() {
        return prev;
    }

    /**
     * set the value as the input value
     * @param value the value to be set as the node's value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * set the next as the input node
     * @param next the next node of this node
     */
    public void setNext(IntNode next) {
        this.next = next;
    }

    /**
     * set the prev as the input node
     * @param prev the prev node of this node
     */
    public void setPrev (IntNode prev) {
        this.prev = prev;
    }
}