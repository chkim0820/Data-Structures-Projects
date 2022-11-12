import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * A JUnit test class for HW4 in CSDS 233
 * @author Chaehyeon Kim cxk445
 */
public class HW4JUnitTest {

    /** HashLList Test Methods */

    @Test
    public void testGetWord() {
        HashLList list = new HashLList();
        // head null
        assertEquals(null, list.getWord());
        // head not null
        list.addLast("Hello");
        assertEquals("Hello", list.getWord());
    }

    @Test
    public void testGetNumItem() {
        HashLList list = new HashLList();
        // 0 item
        assertEquals(0, list.getNumItem());
        // 1 or more items
        list.addLast("Hello");
        assertEquals(1, list.getNumItem());
        list.addLast("hi");
        assertEquals(2, list.getNumItem());
        list.addLast(null);
        assertEquals(3, list.getNumItem());
    }

    @Test
    public void testAddLast() {
        HashLList list = new HashLList();

        list.addLast("A");
        list.addLast("B");
        list.addLast(null);

        HashLList.LIterator it = list.iterator();
        assertEquals("A", it.next());
        assertEquals("B", it.next());
        assertEquals(null, it.next());
    }

    @Test
    public void testDelete() throws Exception {
        HashLList list = new HashLList();

        // checking for exception handling
        try {
            list.delete(null);
        }
        catch (Exception e) {
            // exception caught; all good since it was supposed to throw an exception
        }

        // addling nodes to the list
        list.addLast("1");
        list.addLast("2");
        list.addLast("3");
        list.addLast("4");
        list.addLast("5");

        // deleting the head of the list
        list.delete("1");
        HashLList.LIterator it = list.iterator();
        int i = 2; 
        while (it.hasNext()) {
            assertEquals(i + "", it.next());
            i++;
        }
    }

    /** Test methods for LLiterator class, a nested class of HashLList */
    @Test
    public void testHasNext() {
        HashLList list = new HashLList(); // a SLL for this test method
        HashLList.LIterator it = list.iterator(); // iterator for list2
        assertEquals(it.hasNext(), false);
        for (int i = 1; i < 4; i++) // adds 1, 2, 3 to list2
            list.addLast(i + "");
        HashLList.LIterator it2 = list.iterator(); // iterator for list2
        assertEquals(it2.hasNext(), true);
        it2.next(); // pointer to the next node in the list
        assertEquals(it2.hasNext(), true);
        it2.next(); // pointer to the next node in the list
        assertEquals(it2.hasNext(), true);
        it2.next(); // pointer to the next node in the list
        assertEquals(it2.hasNext(), false);
    }

    @Test
    public void testNext() {
        HashLList list = new HashLList(); // a SLL for this test method
        HashLList.LIterator it = list.iterator(); // iterator for list2
        try {
            it.next(); // pointer to a null list
        }
        catch (NullPointerException e) {
            // exception thrown correctly
        } 
        for (int i = 1; i < 6; i++) // adds 1, 2, 3, 4, 5 to the list
            list.addLast(i + "");
        HashLList.LIterator it2 = list.iterator(); // another iterator for list2
        assertEquals(it2.next(), "1");
        assertEquals(it2.next(), "2");
        assertEquals(it2.next(), "3");
        try {
            it2.next(); // the pointer will now point to a null node (after 5)
        }
        catch (NullPointerException e2) {
            // exception thrown correctly
        }
    }
}
