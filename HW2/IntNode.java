/**
 * An IntNode class for CSDS 233 HW 2
 * @author Chaehyeon Kim cxk445
 */

public class IntNode <G> {

    /** the value of the node */
    private G value;
    /** the next node of this node */
    private IntNode<G> next = null;
    /** the previous node of this node */
    private IntNode<G> prev = null;

    /**
     * A constructor for the IntNode class with three inputs
     * @param value the value of the node
     * @param next the next node of this node
     * @param prev the previou node of this node
     */
    public IntNode(G value, IntNode<G> next, IntNode<G> prev) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }

    /**
     * A constructor for the IntNode class with value input
     * @param value the value of the node
     */
    public IntNode(G value) {
        this.value = value;
    }

    /**
     * returns the value of this node
     * @return the value of this node
     */
    public G getValue() {
        return value;
    }

    /**
     * returns the next node of this node
     * @return the next node of this node
     */
    public IntNode<G> getNext() {
        return next;
    }

    /**
     * returns the previous node of this node
     * @return the previous node of this node
     */
    public IntNode<G> getPrev() {
        return prev;
    }

    /**
     * set the value as the input value
     * @param value the value to be set as the node's value
     */
    public void setValue(G value) {
        this.value = value;
    }

    /**
     * set the next as the input node
     * @param next the next node of this node
     */
    public void setNext(IntNode<G> next) {
        this.next = next;
    }

    /**
     * set the prev as the input node
     * @param prev the prev node of this node
     */
    public void setPrev (IntNode<G> prev) {
        this.prev = prev;
    }
}