import java.util.NoSuchElementException;

/**
 * A singly Linked List class for the HashTable class using separate chaining
 * @author Chaehyeon Kim
 */
public class HashLList {
   
    private Node head;
    private int numItem;

    public HashLList() {
        this.head = null;
        this.numItem = 0;
    }

    public int getNumItem() {
        return numItem;
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
        numItem++;
    }

    public boolean delete(String str) throws Exception {
        Node trav = head;
        if (trav == null)
            throw new Exception("The list is empty.");
        else {
            boolean nodeDeleted = false;
            String travItem = trav.item.toLowerCase(); // the item at trav
            String strLower = str.toLowerCase();
            boolean returnThis = false;
            if (trav == head) {
                if (travItem.equals(strLower)) {
                    head = trav.next;
                    nodeDeleted = true;
                    if (head == null)
                        returnThis = true; // empty but removed
                }
            }
            while (!nodeDeleted && trav.next != null) {
                if (travItem.equals(strLower)) {
                    trav.next = trav.next.next;
                    nodeDeleted = true;
                }
                trav = trav.next;
            }
            if (!nodeDeleted)
                throw new NoSuchElementException();
            else
                numItem--;
            return returnThis; // array index didn't go from occupied to empty
        }
    }

    public LIterator iterator() {
        return new LIterator();
    }

    public class LIterator {

        private Node nextNode;

        private LIterator() {
            nextNode = head;
        }

        public boolean hasNext() {
            return (nextNode != null);
        }

        public String next() {
            if (nextNode == null)
                throw new NullPointerException();
            String str = nextNode.item;
            nextNode = nextNode.next;
            return str;
        }
    }

    /**
     * A Node class for HashLList class
     */
    private class Node {
        private String item;
        private Node next;

        private Node(String item) {
            this.item = item;
            next = null;
        }
    }
}
