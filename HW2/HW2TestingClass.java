import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.Iterator;

/**
 * A JUnit testing class for the CSDS 233 hw2
 * @author Chaehyeon Kim cxk445
 */
public class HW2TestingClass {
    
    DoublyLinkedList list = new DoublyLinkedList();

    /**
     * A test method for add() in DoublyLinkedList class
     */
    @Test
    public void testAdd() {
        list.add(5);
        java.util.Iterator it = list.iterator();
        assertEquals(null, list.iterator(), list);
    }

    /**
     * A JUnit test method for reverse() in DoublyLinkedList class
     */
    @Test
    public void testReverse() {
        assertEquals();
    }
}