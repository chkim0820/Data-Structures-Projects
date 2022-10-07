import java.util.EmptyStackException;
import java.util.Queue;

/**
 * A CustomQStack class for CSDS 233 HW 2
 * Demonstrate that Queues can be modified to act like Stacks
 * @author Chaehyeon Kim cxk445 
 */
public class CustomQStack {

    /** The queue used for the implementation of a Stack */
    private Queue<Integer> queue;
    /** A variable for keeping track of the number of elements in this Stack */
    private int size = 0;

    /**
     * The constructor for the CustomQStack class
     * @param queue the queue used for the implementation of a Stack
     */
    public CustomQStack(Queue<Integer> queue) {
        this.queue = queue;
    }

    /**
     * Tests if the stack is empty
     * @return true if the stack is empty and returns false otherwise
     */
    public boolean empty() {
        return (size == 0);
    }

    /**
     * Removes the object at the top of this stack and returns that object as the value of this function
     * @return the value that is returned
     */
    public Integer pop() throws EmptyStackException{
        if (size == 0)
            throw new EmptyStackException();
        for (int i = 1; i < size; i++) {
            queue.add(queue.remove());
        }
        size--;
        return queue.remove(); // int vs. Integer? check if conversion necessary
    }

    /**
     * Pushes an item onto the top of this stack
     * @return the item that is pushed onto the top of this stack
     */
    public Integer push(Integer e) {
        queue.add(e);
        size++;
        return e;
    }
}