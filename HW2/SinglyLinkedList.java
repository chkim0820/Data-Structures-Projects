import javax.xml.bind.TypeConstraintException;

/**
 * A SinglyLinkedList for CSDS 233 HW 2
 * @author Chaehyeon Kim cxk445
 */

public class SinglyLinkedList {

    private IntNode first;

    /**
    * A method that is invoked on a list object and reverse the list using no additional lists
    */
    public void reverse() { // check if working with my own node class is okay
        if (first instanceof IntNode) {
            IntNode var = first;
            
        }
        else
            throw new TypeConstraintException("The reverse() function can only operate on a node");
    }

    public class IntNode {
        private int value;
        private IntNode next = null;

        public IntNode(int value, IntNode next) {
            this.value = value;
            this.next = next;
        }

        public IntNode(int value) {
            this.value = value;
        }

        public IntNode getNext() {
            return next;
        }

        public void setNext(IntNode next) {
            this.next = next;
        }

        public int getValue() {
            return value;
        }
    }
}