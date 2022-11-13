import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * A JUnit test class for HW4 in CSDS 233
 * @author Chaehyeon Kim cxk445
 */
public class HW4JUnitTest {

    /** HashLList Test Methods */

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
    public void testListDelete() throws Exception {
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

    /** HashTable Test Methods */
    
    @Test
    public void testGetNumRepeats() {
        HashTable table = new HashTable(5);
        
        try {
            table.getNumRepeats(1);
        }
        catch (NoSuchElementException e) {
            // Exception supposed to be thrown
        }

        int i = Math.abs("a".hashCode()) % 5;
        for (int j = 0; j < 3; j++)
            table.insert("A");
        assertEquals(3, table.getNumRepeats(i));

        int a = Math.abs("b".hashCode()) % 9; // will rehash
        for (int b = 0; b < 5; b++)
            table.insert("B");
        assertEquals(5, table.getNumRepeats(a));
    }

    @Test
    public void testTableGetWord() {
        HashTable table = new HashTable(5);

        assertEquals(null, table.getWord(0));

        table.insert("A");
        assertEquals("a", table.getWord(Math.abs("a".hashCode()) % 5));
    }

    @Test
    public void testInsert() { // and probe() and rehash()
        HashTable table = new HashTable(5);

        // Check if equal for upper and lower cases
        int a = Math.abs("a".hashCode()) % 5;
        table.insert("A");
        table.insert("a");
        assertEquals(2, table.getNumRepeats(a));

        // more test cases
        int b = Math.abs("b".hashCode()) % 5;
        int c = Math.abs("c".hashCode()) % 5;
        table.insert("B");
        table.insert("C");
        assertEquals("b", table.getWord(b));
        assertEquals("c", table.getWord(c));
        // Check if assigned to different indeces
        assertNotEquals(b, c);

        // Check if probe() works well for two values leading to the same result with hashCode() (not probe()) 
        table.insert("F");
        assertNotEquals("f", table.getWord(a));
        assertEquals("f", table.getWord(0));

        // Check if it rehashes correctly
        int d = Math.abs("d".hashCode()) % 9;
        table.insert("D");
        assertEquals("d", table.getWord(d));
    }

    @Test
    public void testTableDelete() throws Exception { // and probe() and rehash()
        HashTable table = new HashTable(5);

        for (int i = 0; i < 5; i++)
            table.insert("a");
        // check that delete() is case-insensitivie
        int a = Math.abs("a".hashCode()) % 5;
        table.delete("A");
        table.delete("a");
        assertEquals(3, table.getNumRepeats(a));
        
        // try deleting a String value not in the table
        try {
            table.delete("hello");
        }
        catch (NoSuchElementException e) {
            // exception supposed to be thrown
        }

        // check if it rehashes correctly with too many deleted items
        table.delete("a");
        table.delete("a");
        table.delete("a");
        table.insert("b");
        table.delete("b");
        table.insert("c");
        table.delete("c");
        // enough numDelete; should rehash() at next insert() call
        table.insert("f");
        int f = Math.abs("f".hashCode()) % 5;
        assertEquals(a, f); // should check that probe() leads to the same
        assertEquals("f", table.getWord(f));
    }

    /** Test method for WordCount class (just wordCount()) */

    @Test
    public void testWordCount() {
        WordCount wc = new WordCount();
        wc.wordCount("Hello hi hello");

    }

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            builder.append(args[i]);
        }
        String str = builder.toString();
        WordCount wc = new WordCount();
        wc.wordCount(str);
    }
}
