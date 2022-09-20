/**
 * CSDS 233 HW1; a class for methods solving for Fibonacci methods
 * @author Chaehyeon Kim cxk445
 */

public class Fibonacci {

    private int[] fibArray;
    private int size;
    private int numIndex;

    /**
     * A constructor for the Fibonacci class that initializes the array with an appropriate number
     */
    public void fibonacci(int num) {
        numIndex = num + 10;
        fibArray = new int[numIndex]; // initializing the array at a size 10 bigger than given
        size = 0;
    }

    /**
     * A constructor for the Fibonacci class that doesn't get an input
     */
    public void fibonacci() {
        numIndex = 0;
        fibArray = new int[0];
        size = 0;
    }

    /**
     * a method that prints out the nth Fibonacci sequence starting from 0, 1, using an iterative method
     * @param n specifies the nth sequence
     * @return returns the nth sequence
     */
    public int fibonacciIterative(int n) {
        int first = 1;
        int second = 2;
        if (n == 0)
            return 0;
        else if (n == 1 || n == 2)
            return 1;
        for (int i = 0; i < n - 3 ; i++) {
            int temp = first;
            first = second;
            second = second + temp;
        }
        return second;
    }  
    // Efficiency: O(n)

    /**
     * a method that prints out the nth Fibonacci sequence starting from 0, 1, using a recursive method
     * @param n specifies the nth sequence
     * @return returns the nth sequence
     */
    public int fibonacciRecursive (int n) {
        if (n <= 1)
            return 1;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }
    // Efficiency: O(2^n); worse efficiency than the iterative method

     /**
      * A helper method that finds the appropriate index of the input number using binary search
      * @param num the number that needs an appropriate index assigned
      * @return the appropriate index for the input number
      */
    public int indexFinder (int num) { // return 4 to denote when there's no value
        int front = 0;
        int back = size - 1;
        while (front <= back) {
            int half = (back + front) / 2;
            if (fibArray[half] == num)
                return num;
            else if (fibArray[half] > num)
                back = half - 1;
            else
                front = half + 1;
        }
        return -1 * front; // return an index appropriate for insertion; - to indicate no value found
    }
    /**
     * add a number to the list (allow to add duplicates)
     * @param item an item to be added to the list
     */
    public void add(int item) throws NullPointerException {
        int index = Math.abs(indexFinder(item));
        if (size <= numIndex) {
            while (index < size) {
                fibArray[index] = fibArray[index + 1];
            }
            size++;
        }
        else
            throw new NullPointerException();
    }

    /**
     * remove the item (if exists) from the list
     * @param item an item to be removed from the list
     */
    public void remove(int item) {
        int index = indexFinder(item);
        if (index >= 0) {
            while (index < size - 1) {
                fibArray[index] = fibArray[index + 1];
            }
            size--;
        }
    }

    /**
     * check if the item exists in the list
     */
    public boolean ifContains(int item) {
        if (indexFinder(item) >= 0)
            return true;
        else return false;
    }

    /**
     * random draw a number from the list without removing
     * @return a random number from the list
     */
    public int grab() {
        return indexFinder ((int)((Math.random()) * size));
    }

    /**
     * prints out the number of unique items in the list
     */
    public int numItems() {
        if (size <= 2)
            return size;
        else
            return size - 1;
    }

    /**
     * The main method of the Fibonacci class that runs the more efficient method of calculating the Fibonacci number
     * @param args the sequence of Fibonacci number being searched
     */
    public static void main(String[] args) {
        Fibonacci subject = new Fibonacci();
        for (int i = 0; args[i] != null; i++) {
            int index = Integer.parseInt(args[i]);
            System.out.println(subject.fibonacciIterative(index));
        }
    }
}