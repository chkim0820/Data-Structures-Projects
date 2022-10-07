import java.util.Stack;

/**
 * A CustomSQueue class for CSDS 233 HW 2
 * Demonstrate that Stacks can be modified to act like Queues
 * @author Chaehyeon Kim cxk445
 */
public class CustomSQueue <G> {
    
    /** The first stack used for the implementation of queue */
    private Stack<G> s1;
    /** The second stack used for the implementation of queue */
    private Stack<G> s2;
    /** The number of elements in this Queue */
    private int size = 0;

    /**
     * A constructor for the CustomSQueue class
     * @param stack1 the first stack used for the implementation of queue
     * @param stack2 the second stack used for the implementation of queue
     */
    public CustomSQueue(Stack<G> stack1, Stack<G> stack2) {
        this.s1 = stack1;
        this.s2 = stack2;
    }

    /**
     * Inserts the specified element into this queue if it's possible
     * @param e the element to be inserted into this queue
     * @return true upon success, IllegalStateException if no space available
     */
    public boolean add(G e) throws IllegalStateException {
        if (s1.push(e) != e) // idk what the condition should be; should I just not
            throw new IllegalStateException(); 
        s1.push(e);
        size++;
        return true;
    }

    /**
     * Retrieves and removes the head of this queue, or returns null if this queue is empty
     * @return the head of this queue
     */
    public G poll() {
        for (int i = 0; i < size; i++) {
            s2.push(s1.pop());
        }
        G returnThis = s2.pop();
        size--;
        for (int i = 0; i < size; i++) {
            s1.push(s2.pop());
        }
        return returnThis;
    }
}
