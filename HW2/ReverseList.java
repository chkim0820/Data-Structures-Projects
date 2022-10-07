import java.util.LinkedList;
import java.util.Iterator;

public class ReverseList {
    
    private LinkedList<Integer> list;

    public ReverseList(LinkedList<Integer> list) {
        this.list = list;
    }

    /**
     * A method that is invoked on a list object and reverse the list using no additional lists
     * (Got help from the hw2 office hour session)
     */
    public void reverse() {
        Iterator<Integer> it = list.iterator();
        // variables to keep track of the former and latter nodes
        IntNode<Integer> prev = null;
        IntNode<Integer> current = node;
        IntNode<Integer> next = null;
        while (it.hasNext()) { // iterating through the whole list and reversing
            next = current.getNext();
            current.setNext(prev); // now do this with an iterator?
            prev = current;
            current = next;
        }
    }
}