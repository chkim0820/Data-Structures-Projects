import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.EmptyStackException;

/**
 * A JUnit testing class for CSDS 233 hw2
 * @author Chaehyeon Kim cxk445
 */
public class HW2TestingClass {

    /** Test methods for SinglyLinkedList class' methods */

    /**
     * A test method for removeFirst() in SinglyLinkedClass
     */
    @Test
    public void testRemoveFirst() {
        SinglyLinkedList list = new SinglyLinkedList(); // a linked list object to be tested on
        for (int i = 0; i < 3; i++) { // adds 1, 2, 3 to the list
            list.add(i + 1);
        }
        assertEquals(list.removeFirst().intValue(), 1);
        assertEquals(list.removeFirst().intValue(), 2);
        assertEquals(list.removeFirst().intValue(), 3);
    }

    /**
     * A test method for add() in SinglyLinkedList class
     */
    @Test
    public void testSLLAdd() {
        SinglyLinkedList list = new SinglyLinkedList(); // SLL object for this test method
        list.add(5);
        SinglyLinkedList.LLIterator<Integer> it = list.iterator(); // an iterator for list
        assertEquals(it.next(), 5);
        for (int i = 6; i < 10; i++) // adds 6, 7, 8, 9 to the list; now 5, 6, 7, 8, 9 in the list
            list.add(i);
        SinglyLinkedList.LLIterator<Integer> it2 = list.iterator(); // another iterator for list
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
        SinglyLinkedList list = new SinglyLinkedList(); // a SLL for this test method
        for (int i = 5; i < 10; i++) // adds 5, 6, 7, 8, 9 to list
            list.add(i);
        list.reverse();
        SinglyLinkedList.LLIterator<Integer> it = list.iterator(); // iterator for list
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
        SinglyLinkedList list2 = new SinglyLinkedList(); // a SLL for this test method
        SinglyLinkedList.LLIterator<Integer> it = list2.iterator(); // iterator for list2
        try {
            it.next(); // pointer to a null list
        }
        catch (NullPointerException e) {
            // exception thrown correctly
        } 
        for (int i = 1; i < 6; i++) // adds 1, 2, 3, 4, 5 to the list
            list2.add(i);
        SinglyLinkedList.LLIterator<Integer> it2 = list2.iterator(); // another iterator for list2
        assertEquals(it2.next(), 1);
        assertEquals(it2.next(), 2);
        assertEquals(it2.next(), 3);
        assertEquals(it2.next(), 4);
        assertEquals(it2.next(), 5);
        try {
            it2.next(); // the pointer will now point to a null node (after 5)
        }
        catch (NullPointerException e2) {
            // exception thrown correctly
        }
    }

    /**
     * A test method for hasNext() in Iterator
     */
    @Test
    public void testHasNext() {
        SinglyLinkedList list2 = new SinglyLinkedList(); // a SLL for this test method
        SinglyLinkedList.LLIterator<Integer> it = list2.iterator(); // iterator for list2
        assertEquals(it.hasNext(), false);
        for (int i = 1; i < 4; i++) // adds 1, 2, 3 to list2
            list2.add(i);
        SinglyLinkedList.LLIterator<Integer> it2 = list2.iterator(); // iterator for list2
        assertEquals(it2.hasNext(), true);
        it2.next(); // pointer to the next node in the list
        assertEquals(it2.hasNext(), true);
        it2.next(); // pointer to the next node in the list
        assertEquals(it2.hasNext(), true);
        it2.next(); // pointer to the next node in the list
        assertEquals(it2.hasNext(), false);
    }

    /** Test methods for CustomQStack class' methods */

    /**
     * A test method for empty() in CustomQStack
     */
    @Test
    public void testEmpty() {
        CustomQStack s1 = new CustomQStack(); // s1 is empty
        assertEquals(s1.empty(), true);
        s1.push(1); // s1 no longer empty
        assertEquals(s1.empty(), false);
    }

    /**
     * A test method for pop() and push() in CustomQStack
     */
    @Test
    public void testPopAndPush() {
        CustomQStack s1 = new CustomQStack(); // a new CQS for this test method
        try {
            s1.pop(); // attempts to pop from an empty stack
        }
        catch (EmptyStackException e) {
            // exception thrown correctly; was an empty stack 
        }
        assertEquals(s1.push(1).intValue(), 1);
        assertEquals(s1.pop().intValue(), 1);
        for (int i = 0; i < 3; i++) { // adds 1, 2, 3 to the stack
            s1.push(i + 1);
        }
        assertEquals(s1.pop().intValue(), 3);
        assertEquals(s1.pop().intValue(), 2);
        assertEquals(s1.pop().intValue(), 1);
        try {
            s1.pop(); // stack no longer has items; is empty
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
        CustomSQueue q1 = new CustomSQueue(); // a new CSQ object for this test method
        assertEquals(q1.add(1), true);
        assertEquals(q1.add(2), true);
        assertEquals(q1.add(3), true);
        assertEquals(q1.poll().intValue(), 1);
        assertEquals(q1.poll().intValue(), 2);
        assertEquals(q1.poll().intValue(), 3);
    }
}