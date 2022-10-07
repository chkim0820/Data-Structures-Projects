/**
 * An LLNode class built for the SinglyLinkedList class; for CSDS 233 HW 2
 * @author Chaehyeon Kim cxk445
 */
public class LLNode <G> {

    /** the value of the node */
    private G value;
    /** the next node of this node */
    private LLNode<G> next;

    /**
     * A constructor for the LLNode class with three inputs
     * @param value the value of the node
     * @param next the next node of this node
     */
    public LLNode(G value, LLNode<G> next) {
        this.value = value;
        this.next = next;
    }

    /**
     * A constructor for the LLNode class with value input
     * @param value the value of the node
     */
    public LLNode(G value) {
        this.value = value;
        this.next = null;
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
    public LLNode<G> getNext() {
        return next;
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
     * @param llNode the next node of this node
     */
    public void setNext(LLNode<G> llNode) {
        this.next = llNode;
    }
}