/**
 * CSDS 233 HW1; a class for methods solving for Fibonacci methods
 * @author Chaehyeon Kim cxk445
 */

public class Fibonacci {

    private int[] fibArray;
    private int size;

    /**
     * A constructor for the Fibonacci class that initializes the array with an appropriate number
     */
    public void fibonacci(int num) {
        fibArray = new int[num + 10]; // initializing the array at a size 10 bigger than given
        size = 0;
    }

    /**
     * a method that prints out the nth Fibonacci sequence starting from 0, 1, using an iterative method
     * @param n specifies the nth sequence
     * @return returns the nth sequence
     */
    public int fibonacciIterative(int n) {
        int first = 0;
        int second = 1;
        for (int i = 0; i < n - 2 ; i++) {
            int temp = first;
            first = second;
            second = second + temp;
        }
        return second;
    }

    /**
     * a method that prints out the nth Fibonacci sequence starting from 0, 1, using a recursive method
     * @param n specifies the nth sequence
     * @return returns the nth sequence
     */
    public int fibonacciRecursive(int n) {
        if (n == 1) // base case
            return fibonacciRecursive(n) + fibonacciRecursive(n - 1);
        //
    }

    /**
     * The efficiency of the two methods in terms of time complexity & big-o notation as comments
     */

     /**
      * A helper method that finds the appropriate index of the input number using binary search
      * @param num the number that needs an appropriate index assigned
      * @return the appropriate index for the input number
      */
    public int indexFinder (int num) { // return 4 to denote when there's no value
        int ptr = (size - 1) / 2;
        while ((ptr != 0) || (ptr != size)) {
            if (fibArray(ptr) == num
                return ptr;
            else if (fibArray(ptr) < num)
                ptr = ptr + ptr/2;
            else if (fibArray(ptr) > num)
                ptr = ptr - ptr/2;
        }
        return 4;
    }
    /**
     * add a number to the list (allow to add duplicates)
     * @param item an item to be added to the list
     */
    public void add(int item) {
        int temp = indexFinder(item);
        while () // while loop to 
    }

    /**
     * remove the item (if exists) from the list
     * @param item an item to be removed from the list
     */
    public void remove(int item) {
        int temp = indexFinder(item);
        while (temp < size) {
            fibArray[temp] = fibArray[temp + 1];
        }
    }

    /**
     * check if the item exists in the list
     */
    public boolean ifContains(int item) {
        if (indexFinder(item) == 4)
            return false;
        else return true;
    }

    /**
     * random draw a number from the list without removing
     * @return a random number from the list
     */
    public int grab() {
        return indexFinder((java.lang.Math.random()) * size);
    }

    /**
     * prints out the number of unique items in the list
     */
    public int numItems() {
        if (size == 1)
            return 1;
        else
            return size - 1;
    }

    /**
     * The main method of the Fibonacci class that runs the more efficient method of calculating the Fibonacci number
     * @param args the sequence of Fibonacci number being searched
     */
    public static void main(String[] args) {
        fibonacciIterative(args); //convert to numbers
    }
}