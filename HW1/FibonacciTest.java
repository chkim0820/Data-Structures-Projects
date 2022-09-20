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
        assertEquals("add input 35", fib.lookup(13), 35);
    }

    /**
     * test method for remove() in Fibonacci class
     */
    @Test
    public void testRemove() {
        fib.remove(-1); // removing the random numbers added to the array in the previous method
        fib.remove(4);
        fib.remove(35);

        assertEquals("remove input -1", fib.ifContains(-1), false);
        assertEquals("remove input 4", fib.ifContains(4), false);
        assertEquals("remove input 35", fib.ifContains(35), false);
    }

    /**
     * test method for ifContains() in Fibonacci class
     */
    @Test
    public void testIfContains() {
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
        assertEquals("numItems() for 10 items", fib.numItems(), 10);

        Fibonacci fib2 = new Fibonacci(); // emtpy Fibonacci object
        assertEquals("numItems() for an empty array",fib.numItems(), 0);

        fib2.add(1); // one item
        assertEquals("numItems() for an array with one item", fib.numItems(), 1);

        fib2.add(1); // many duplicates; still one item
        fib2.add(1);
        fib2.add(1);
        assertEquals("numItems() for an array with duplicates", fib.numItems(), 1);

        fib2.add(2); // two items
        assertEquals("numItems() with two distinct items", fib.numItems(), 2);

        fib2.add(1); // two items; it's a duplicate
        assertEquals("numItems() adding another duplicate", fib.numItems(), 2);
    }

    /**
     * test the main method in Fibonacci class
     */
    @Test
    public void testMainMethod() {

    }
}
