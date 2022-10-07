import java.util.EmptyStackException;

/**
 * A CustomQStack class for CSDS 233 HW 2
 * Demonstrate that Queues can be modified to act like Stacks
 * @author Chaehyeon Kim cxk445 
 */
public class CustomQStack {

    /** The queue used for the implementation of a Stack */
    private Queue queue;
    /** A variable for keeping track of the number of elements in this Stack */
    private int size = 0;

    /**
     * The constructor for the CustomQStack class
     */
    public CustomQStack() {
        this.queue = new Queue();
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
        if (size == 0) // the stack is empty
            throw new EmptyStackException();
        for (int i = 1; i < size; i++) { // for-loop to add the front values of the queue to the back after removing
            queue.add(queue.remove());
        }
        size--;
        return queue.remove();
    }

    /**
     * Pushes an item onto the top of this stack
     * @param e the element to be pushed onto the stack
     * @return the item that is pushed onto the top of this stack
     */
    public Integer push(Integer e) {
        queue.add(e);
        size++;
        return e;
    }
}