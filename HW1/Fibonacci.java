/**
 * CSDS 233 HW1; a class for methods solving for Fibonacci methods
 * @author Chaehyeon Kim cxk445
 */

public class Fibonacci {

    /**
     * a method that prints out the nth Fibonacci sequence starting from 0, 1, using an iterative method
     * @param n specifies the nth sequence
     * @return returns the nth sequence
     */
    public int fibonacciIterative(int n) {
        int first = 1;
        int second = 2;
        int sequence = 0;
        for (int i = 0; i < n; i++) {
            if (n == first) {
                sequence = n;
                i = n + 1;
            }
            int temp = first;
            first = second;
            second = temp + first;
        }
        return sequence;
    }

    /**
     * a method that prints out the nth Fibonacci sequence starting from 0, 1, using a recursive method
     * @param n specifies the nth sequence
     * @return returns the nth sequence
     */
    public int fibonacciRecursive(int n) {
        if (n == 1) // base case
            return;
        fibonacciRecursive(n);
    }

    /**
     * The efficiency of the two methods in terms of time complexity & big-o notation as comments
     */


    /**
     * add a number to the list (allow to add duplicates)
     * @param item an item to be added to the list
     */
    public void add(int item) {

    }

    /**
     * remove the item (if exists) from the list
     * @param item an item to be removed from the list
     */
    public void remove(int item) {

    }

    /**
     * check if the item exists in the list
     */
    public boolean ifContains(int item) {

    }

    /**
     * random draw a number from the list without removing
     * @return a random number from the list
     */
    public int grab() {

    }

    /**
     * prints out the number of unique items in the list
     */
    public int numItems() {

    }

    /**
     * The main method of the Fibonacci class that runs the more efficient method of calculating the Fibonacci number
     * @param args the sequence of Fibonacci number being searched
     */
    public static void main(String[] args) {
        fibonacciIterative(args); //convert to numbers
    }
}


klklj;lkj