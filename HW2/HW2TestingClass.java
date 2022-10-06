import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.Iterator;

/**
 * A JUnit testing class for the CSDS 233 hw2
 * @author Chaehyeon Kim cxk445
 */
public class HW2TestingClass {
    
    DoublyLinkedList list = new DoublyLinkedList();

    /** Test methods for DoublyLinkedList class' methods */

    /**
     * A test method for add() in DoublyLinkedList class
     */
    @Test
    public void testAdd() { // do the test methods also have to follow the encapsulation rule?
        list.add(5);
        assertEquals(list.getHead().getValue(), 5);
        for (int i = 6; i < 10; i++)
            list.add(i);
        assertEquals(list.getHead().getNext().getValue(), 6);
        assertEquals(list.getHead().getNext().getNext().getValue(), 7);
        assertEquals(list.getHead().getNext().getNext().getNext().getValue(), 8);
        assertEquals(list.getHead().getNext().getNext().getNext().getNext().getValue(), 9);
    }

    /**
     * A test method for reverse() in DoublyLinkedList class
     */
    @Test
    public void testReverse() {
        for (int i = 5; i < 10; i++)
            list.add(i);
        list.reverse();
        assertEquals(list.getHead().getValue(), 9); 
        assertEquals(list.getHead().getNext().getValue(), 8);
        assertEquals(list.getHead().getNext().getNext().getValue(), 7);
        assertEquals(list.getHead().getNext().getNext().getNext().getValue(), 6);
        assertEquals(list.getHead().getNext().getNext().getNext().getNext().getValue(), 5);
    }

    /** Test methods for the Iterator class' methods */

    /**
     * A test method for next() in Iterator
     */
    @Test
    public void testNext() {
        DoublyLinkedList.Iterator it = list.iterator();
        assertEquals(it.next(), 9);
        assertEquals(it.next(), 8);
        assertEquals(it.next(), 7);
        assertEquals(it.next(), 6);
        assertEquals(it.next(), 5);
    }

    /**
     * A test method for hasNext() in Iterator
     */
    @Test
    public void testHasNext() {
        DoublyLinkedList.Iterator it = list.iterator();
        assertEquals(it.hasNext(), true);
        DoublyLinkedList list2 = new DoublyLinkedList();
        DoublyLinkedList.Iterator it2 = list2.iterator();
        assertEquals(it2.hasNext(), false);
    }

    /**
     * A test method for previous() in Iterator
     */
    @Test
    public void testPrevious() {
        
    }

    /**
     * A test method for hasPrevious() in Iterator
     */
    @Test
    public void testHasPrevious() {

    }

    /** Test methods for CustomQStack class' methods */

    /**
     * A test method for empty() in CustomQStack
     */
    @Test
    public void testEmpty() {

    }

    /**
     * A test method for pop() in CustomQStack
     */
    @Test
    public void testPop() {

    }

    /**
     * A test method for push() in CustomQStack
     */
    @Test
    public void testPush() {

    }

    /** Test methods for CustomSQueue class' methods */

    /**
     * A test method for add() in CustomSQueue
     */
    @Test
    public void testSQueueAdd() {

    }

    /**
     * A test method for poll() in CustomQStack
     */
    @Test
    public void testPoll() {

    }
}