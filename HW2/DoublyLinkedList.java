import javax.xml.bind.TypeConstraintException;

/**
 * A DoublyLinkedList class for CSDS 233 HW 2
 * @author Chaehyeon Kim cxk445
 */

public class DoublyLinkedList extends Iterator {

    /** The head node of the list */
    private IntNode head;
    /** The tail node of the list */
    private IntNode tail;
    /** The size of the list (number of nodes) */
    private int size = 0;

    /**
     * A constructor for the DoublyLinkedList class
     */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    protected IntNode getHead() {
        return head;
    }

    protected IntNode getTail() {
        return tail;
    }

    /**
     * Creates a new Iterator for the instance
    */
    public Iterator iterator() {
        return new Iterator();
    }

    /**
     * Appends the specified element to the end of this list
     * @param element the specified element to be added at the end of the list
     */
    public void add(int element) {
        if (size == 0) {
            head = new IntNode(element);
            tail = head;
        }
        else {
            IntNode newNode = new IntNode(element);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    /**
    * A method that is invoked on a list object and reverse the list using no additional lists
    */
    public void reverse() throws NullPointerException{ // check if working with my own node class is okay
        if (head == null)
            throw new NullPointerException();
        else if (size > 1) {
            IntNode headTrav = head;
            IntNode tailTrav = tail;
            for (int i = 0; i < size/2; i++) { // while every node is swapped
                IntNode saveFront = headTrav.getNext();
                IntNode saveBack = tailTrav.getPrev();
                headTrav.setNext(tailTrav.getNext());
                tailTrav.setPrev(headTrav.getPrev());
                headTrav.setPrev(tailTrav.getPrev());
                tailTrav.setNext(headTrav.getNext());
                headTrav.getNext().setPrev(saveBack); // iterator? ask TA what the benefit would be & what the implementation would look like 
                tailTrav.getPrev().setNext(saveFront);
                if (i >= 1) {
                    // add things to arrange the original head's previous node's next pointer, vise versa
                } // make it also work for even number nodes

            headTrav = headTrav.getNext();
            tailTrav = tailTrav.getPrev();
            }
        }
    }
}

/* Function to reverse the linked list */
Node reverse(Node node) {
    Node prev = null; // to store the previous value
    Node current = node; // for current pointer
    Node next = null; // for next
    while (current != null) {
        next = current.next;
        current.next = prev;
        prev = current;
        current = next;
    }
    node = prev;
    return node; // this will return the new head
}

// prints content of double linked list
void printList(Node node){
    while (node != null) {
    System.out.print(node.data + " ");
    node = node.next;
    }
}
