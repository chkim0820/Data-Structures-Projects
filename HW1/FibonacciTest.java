import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * JUnit test class for Fibonacci class
 * @author Chaehyeon Kim cxk445
 */
public class FibonacciTest{ 
    
    /** an object of Fibnacci class for testing */
    Fibonacci fib = new Fibonacci();

    /**
     * test method for fibonacciIterative() in Fibonacci class
     */
    @Test
    public void testFibonacciIterative() {
        assertEquals("iterative input 0", fib.fibonacciIterative(0), 0);
        assertEquals("iterative input 1", fib.fibonacciIterative(1), 1);
        assertEquals("iterative input 2", fib.fibonacciIterative(2), 1);
        assertEquals("iterative input 8", fib.fibonacciIterative(8), 21);
    }

    /**
     * test method for fibonacciRecursive() in Fibonacci class
     */
    @Test
    public void testFibonacciRecursive() {
        assertEquals("iterative input 0", fib.fibonacciRecursive(0), 0);
        assertEquals("iterative input 1", fib.fibonacciRecursive(1), 1);
        assertEquals("iterative input 2", fib.fibonacciRecursive(2), 1);
        assertEquals("iterative input 8", fib.fibonacciRecursive(8), 21);
    }

    /**
     * Test method for indexFinder() method of Fibonacci class
     */
    @Test
    public void testIndexFinder() {
        for (int i = 0; i < 10; i++) {
            fib.add(i);
        }
        
        assertEquals(fib.indexFinder(0), 0);
        assertEquals(fib.indexFinder(1), 1);
        assertEquals(fib.indexFinder(5), 5);
        assertEquals(fib.indexFinder(9), 9);
    }

    /**
     * test method for add() in Fibonacci class
     */
    @Test
    public void testAdd() {
        for (int i = 0; i < 10; i++) { // adds 10 fibonacci numbers to the array
            fib.add(fib.fibonacciIterative(i));
        }

        assertEquals("add input fib index 0", fib.lookup(0), 0);
        assertEquals("add input fib index 1", fib.lookup(1), 1);
        assertEquals("add input fib index 2", fib.lookup(2), 1);
        assertEquals("add input fib index 3", fib.lookup(3), 2);
        assertEquals("add input fib index 4", fib.lookup(4), 3);
        assertEquals("add input fib index 5", fib.lookup(5), 5);
        assertEquals("add input fib index 6", fib.lookup(6), 8);
        assertEquals("add input fib index 7", fib.lookup(7), 13);
        assertEquals("add input fib index 8", fib.lookup(8), 21);
        assertEquals("add input fib index 9", fib.lookup(9), 34);
        
        fib.add(-1); // adding random numbers to the array to test inserting in front, middle, end
        fib.add(4);
        fib.add(35);
        assertEquals("add input -1", fib.lookup(0), -1);
        assertEquals("add input 4", fib.lookup(6), 4);
        assertEquals("add input 35", fib.lookup(12), 35);
    }

    Fibonacci fib2 = new Fibonacci();

    /**
     * test method for remove() in Fibonacci class
     */
    @Test
    public void testRemove() {
        for (int i = 0; i < 5; i++)
            fib2.add(i);

        assertEquals(fib2.lookup(0), 0);
        assertEquals(fib2.lookup(1), 1);
        assertEquals(fib2.lookup(2), 2);
        assertEquals(fib2.lookup(3), 3);
        assertEquals(fib2.lookup(4), 4);

        fib2.remove(0);
        assertEquals("remove input 0", fib2.ifContains(0), false);
        fib2.remove(2);
        assertEquals("remove input 2", fib2.ifContains(2), false);
        fib2.remove(4);
        assertEquals("remove input 4", fib2.ifContains(4), false);
        assertEquals(fib2.ifContains(1), true); // undeleted item
   }

    /**
     * test method for ifContains() in Fibonacci class
     */
    @Test
    public void testIfContains() {
        for (int i = 0; i < 10; i++) { // adds 10 fibonacci numbers to the array
            fib.add(fib.fibonacciIterative(i));
        }
        assertEquals("ifContains(0) returns false", fib.ifContains(0), true);
        assertEquals("ifContains(5) returns false", fib.ifContains(5), true);
        assertEquals("ifContains(34) returns false", fib.ifContains(34), true);
        assertEquals("ifContains(-2) returns true", fib.ifContains(-2), false);
        assertEquals("ifContains(6) returns true", fib.ifContains(6), false);
        assertEquals("ifContains(14) returns true", fib.ifContains(14), false);
    }

    /**
     * test method for grab() in Fibonacci class
     */
    @Test
    public void testGrab() {
        assertEquals(fib.ifContains(fib.grab()), true);
        assertEquals(fib.ifContains(fib.grab()), true);
        assertEquals(fib.ifContains(fib.grab()), true);
        assertEquals(fib.ifContains(fib.grab()), true);
        assertEquals(fib.ifContains(fib.grab()), true);
        // testing multiple times to ensure the method really works
    }

    /**
     * test method for numItems() in Fibonacci class
     */
    @Test
    public void testNumItems() {
        Fibonacci fib3 = new Fibonacci();

        assertEquals("numItems() for an empty array",fib3.numItems(), 0);

        for (int i = 0; i < 10; i++) { // adds 10 fibonacci numbers to the array
            fib3.add(i);
        }
        assertEquals("numItems() for 10 items", fib3.numItems(), 10);

        
        fib3.add(1); // many duplicates; still ten unique item
        fib3.add(1);
        fib3.add(1);
        assertEquals("numItems() for an array with duplicates", fib3.numItems(), 10);

        fib3.add(100);
        assertEquals("numItems() after adding a new value", fib3.numItems(), 11);
    }
}
