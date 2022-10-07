import java.util.Stack;

/**
 * A CustomSQueue class for CSDS 233 HW 2
 * Demonstrate that Stacks can be modified to act like Queues
 * @author Chaehyeon Kim cxk445
 */
public class CustomSQueue {
    
    /** The first stack used for the implementation of queue */
    private Stack<Integer> s1;
    /** The second stack used for the implementation of queue */
    private Stack<Integer> s2;
    /** The number of elements in this Queue */
    private int size = 0;

    /**
     * A constructor for the CustomSQueue class
     * @param stack1 the first stack used for the implementation of queue
     * @param stack2 the second stack used for the implementation of queue
     */
    public CustomSQueue(Stack<Integer> stack1, Stack<Integer> stack2) {
        this.s1 = stack1;
        this.s2 = stack2;
    }

    /**
     * Inserts the specified element into this queue if it's possible
     * @param e the element to be inserted into this queue
     * @return true upon success
     */
    public boolean add(Integer e) {
        s1.push(e);
        size++;
        return true;
    }

    /**
     * Retrieves and removes the head of this queue, or returns null if this queue is empty
     * @return the head of this queue
     */
    public Integer poll() {
        if (size == 0) // returns null if the queue is empty
            return null;
        else { // queue is not empty
            for (int i = 0; i < size; i++) { // moves all the elements into s2
                s2.push(s1.pop());
            }
            int returnThis = s2.pop(); // pops the first-inserted element out of s2 and save to the variable to be returned later
            size--;
            for (int i = 0; i < size; i++) { // moves the remaining elements back to s1
                s1.push(s2.pop());
            }
            return returnThis;
        }
    }
}
