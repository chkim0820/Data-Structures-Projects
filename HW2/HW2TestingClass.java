import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.EmptyStackException;
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
        Iterator it = list.iterator();
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
        Iterator it = list.iterator();
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
        DoublyLinkedList.Iterator it = list.iterator();
        assertEquals(it.previous(), 5);
        assertEquals(it.previous(), 6);
        assertEquals(it.previous(), 7);
        assertEquals(it.previous(), 8);
        assertEquals(it.previous(), 9);
    }

    /**
     * A test method for hasPrevious() in Iterator
     */
    @Test
    public void testHasPrevious() {
        DoublyLinkedList.Iterator it = list.iterator();
        assertEquals(it.hasPrevious(), true);
        DoublyLinkedList list2 = new DoublyLinkedList();
        DoublyLinkedList.Iterator it2 = list2.iterator();
        assertEquals(it.hasPrevious(), false);
    }

    /** Test methods for CustomQStack class' methods */

    /**
     * A test method for empty() in CustomQStack
     */
    @Test
    public void testEmpty() {
        CustomQStack s1 = new CustomQStack(new Queue<Integer>);
        assertEquals(s1.empty(), true);
        s1.push(1);
        assertEquals(s1.empty(), false);
    }

    /**
     * A test method for pop() in CustomQStack
     */
    @Test
    public void testPop() {
        CustomQStack s1 = new CustomQStack(new Queue<Integer>);
        try {
            s1.pop()
        }
        catch (EmptyStackException e) {
            // exception thrown correctly 
        }
        s1.push(1);
        assertEquals(s1.pop(), 1);
        for (int i = 0; i < 3; i++) {
            s1.push(i + 1);
        }
        assertEquals(s1.pop(), 3);
        assertEquals(s1.pop(), 2);
        assertEquals(s1.pop(), 1);
        try {
            s1.pop();
        }
        catch (EmptyStackException e1) {
            // exception thrown correctly
        }
    }

    /**
     * A test method for push() in CustomQStack
     */
    @Test
    public void testPush() {
        CustomQStack s1 = new CustomQStack(new Queue<Integer>);
        assertEquals(s1.push(1), 1);
        assertEquals(s1.push(2), 2);
        assertEquals(s1.push(3), 3);
    }

    /** Test methods for CustomSQueue class' methods */

    /**
     * A test method for add() in CustomSQueue
     */
    @Test
    public void testSQueueAdd() {
        CustomSQueue q1 = new CustomSQueue(new Stack<Integer>(), new Stack<Integer());
        assertEquals(q1.add(1), true);
        assertEquals(q1.add(2), true);
        assertEquals(q1.add(3), true);
        assertEquals(q1.poll(), 1);
        assertEquals(q1.poll(), 2);
        assertEquals(q1.poll(), 3);
    }

    /**
     * A test method for poll() in CustomSQueue
     */
    @Test
    public void testPoll() {
        // not do it? maybe merge with testAdd()
    }
}