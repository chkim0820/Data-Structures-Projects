import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.EmptyStackException;

/**
 * A JUnit testing class for CSDS 233 hw2
 * @author Chaehyeon Kim cxk445
 */
public class HW2TestingClass {
    
    SinglyLinkedList list = new SinglyLinkedList();

    /** Test methods for SinglyLinkedList class' methods */

    /**
     * A test method for add() in SinglyLinkedList class
     */
    @Test
    public void testSLLAdd() {
        list.add(5);
        SinglyLinkedList.LLIterator<Integer> it = list.iterator();
        assertEquals(it.next(), 5);
        for (int i = 6; i < 10; i++)
            list.add(i);
        SinglyLinkedList.LLIterator<Integer> it2 = list.iterator();
        assertEquals(it2.next(), 5);
        assertEquals(it2.next(), 6);
        assertEquals(it2.next(), 7);
        assertEquals(it2.next(), 8);
        assertEquals(it2.next(), 9);
    }

    /**
     * A test method for reverse() in SinglyLinkedList class
     */
    @Test
    public void testReverse() {
        list.reverse();
        SinglyLinkedList.LLIterator<Integer> it = list.iterator();
        assertEquals(it.next(), 9);
        assertEquals(it.next(), 8);
        assertEquals(it.next(), 7);
        assertEquals(it.next(), 6);
        assertEquals(it.next(), 5);
    }

    /** Test methods for the LLIterator class' methods */

    /**
     * A test method for next() in Iterator
     */
    @Test
    public void testNext() {
        SinglyLinkedList list2 = new SinglyLinkedList();
        SinglyLinkedList.LLIterator<Integer> it = list2.iterator();
        for (int i = 1; i < 6; i++)
            list2.add(i);
        assertEquals(it.next(), 1);
        assertEquals(it.next(), 2);
        assertEquals(it.next(), 3);
        assertEquals(it.next(), 4);
        assertEquals(it.next(), 5);
    }

    /**
     * A test method for hasNext() in Iterator
     */
    @Test
    public void testHasNext() {
        SinglyLinkedList list2 = new SinglyLinkedList();
        SinglyLinkedList.LLIterator<Integer> it = list2.iterator();
        assertEquals(it.hasNext(), false);
        for (int i = 1; i < 4; i++)
            list2.add(i);
        SinglyLinkedList.LLIterator<Integer> it2 = list2.iterator();
        assertEquals(it2.hasNext(), true);
        assertEquals(it2.hasNext(), true);
        assertEquals(it2.hasNext(), true);
        assertEquals(it2.hasNext(), false);
    }

    /** Test methods for CustomQStack class' methods */

    /**
     * A test method for empty() in CustomQStack
     */
    @Test
    public void testEmpty() {
        CustomQStack s1 = new CustomQStack();
        assertEquals(s1.empty(), true);
        s1.push(1);
        assertEquals(s1.empty(), false);
    }

    /**
     * A test method for pop() and push() in CustomQStack
     */
    @Test
    public void testPopAndPush() {
        CustomQStack s1 = new CustomQStack();
        try {
            s1.pop();
        }
        catch (EmptyStackException e) {
            // exception thrown correctly; was an empty stack 
        }
        s1.push(1);
        assertEquals(s1.pop().intValue(), 1);
        for (int i = 0; i < 3; i++) {
            s1.push(i + 1);
        }
        assertEquals(s1.pop().intValue(), 3);
        assertEquals(s1.pop().intValue(), 2);
        assertEquals(s1.pop().intValue(), 1);
        try {
            s1.pop();
        }
        catch (EmptyStackException e1) {
            // exception thrown correctly
        }
    }

    /** Test methods for CustomSQueue class' methods */

    /**
     * A test method for add() and poll() in CustomSQueue
     */
    @Test
    public void testSQueueAddandPoll() {
        CustomSQueue q1 = new CustomSQueue();
        assertEquals(q1.add(1), true);
        assertEquals(q1.add(2), true);
        assertEquals(q1.add(3), true);
        assertEquals(q1.poll().intValue(), 1);
        assertEquals(q1.poll().intValue(), 2);
        assertEquals(q1.poll().intValue(), 3);
    }
}