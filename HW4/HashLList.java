import java.util.NoSuchElementException;

/**
 * A singly Linked List class for the HashTable class using separate chaining
 * @author Chaehyeon Kim
 */
public class HashLList {
   
    private Node head;
    private int numItem;

    public HashLList() {
        this.head = new Node(null);
    }

    public void addLast(String str) {
        Node trav = head;
        if (trav == null)
            head = new Node(str);
        else {
            while (trav.next != null) {
                trav = trav.next;
            }
            trav.next = new Node(str);
        }
    }

    public void delete(String str) {
        Node trav = head;
        if (trav == null)
            throw new NoSuchElementException();
        else {
            boolean nodeDeleted = false;
            while (trav.next != null || nodeDeleted) {
                if (trav.next.item.equals(str)) {
                    if (trav == head)
                        head = trav.next;
                    else 
                        trav.next = trav.next.next;
                    nodeDeleted = true;
                }
                trav = trav.next;
             }
        }
    }

    /**
     * A Node class for HashLList class
     */
    public class Node {
        private String item;
        private Node next;

        public Node(String item) {
            this.item = item;
            next = null;
        }
    }

}
