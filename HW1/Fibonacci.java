/**
 * CSDS 233 HW1; a class for methods solving for Fibonacci methods
 * @author Chaehyeon Kim cxk445
 */

public class Fibonacci {

    /** an int array for the Fibonacci object */
    private int[] fibArray;
    /** the size of the array (number of un-unique items) */
    private int size;
    /** number of index in the array initialized */
    private int numIndex;

    /**
     * A constructor for the Fibonacci class that doesn't get an input
     */
    public Fibonacci() {
        fibArray = new int[30];
        numIndex = 30;
        size = 0;
    }

    /**
     * a method that prints out the nth Fibonacci sequence starting from 0, 1, using an iterative method
     * @param n specifies the nth sequence
     * @return returns the nth sequence
     */
    public int fibonacciIterative(int n) {
        int first = 1; // first of the two consecutive Fibonacci numbers
        int second = 2; // second of the two consecutive Fibonacci numbers
        // edge cases; the first three of the sequence
        if (n == 0) 
            return 0;
        else if (n == 1 || n == 2)
            return 1;
        // going through Fibonacci sequence to find the appropriate number
        for (int i = 0; i < n - 3 ; i++) {
            int temp = first; // saves value before being replaced
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
        if (n == 0)
            return 0;
        else if (n == 1) // base case
            return 1;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }
    // Efficiency: O(2^n); worse efficiency than the iterative method

     /**
      * A helper method that finds the appropriate index of the input number using binary search
      * @param num the number that needs an appropriate index assigned
      * @return the appropriate index for the input number
      */
    public int indexFinder (int num) {
        int front = 0; // front of array
        int back = size - 1; // back of array
        while (front <= back) {
            int half = (back + front) / 2;
            if (fibArray[half] == num)
                return half; // returns the index if matched
            else if (fibArray[half] > num)
                back = half - 1;
            else
                front = half + 1;
        }
        return -1 * (back + 1); // return an index appropriate for insertion; - to indicate no value found
    }
    /**
     * add a number to the list (allow to add duplicates)
     * @param item an item to be added to the list
     */
    public void add(int item) throws NullPointerException{
        int index = Math.abs(indexFinder(item)); // appropriate index for the new item
        // enough room in the array for new items
        if (size + 1 <= numIndex) {
            for (int i = size; i > index; i--) { // move surrounding values
                fibArray[i] = fibArray[i - 1];
            }
            fibArray[index] = item;
            size++;
        }
        // not enough room in the array; return an exception
        else 
            throw new NullPointerException();
    }

    /**
     * remove the item (if exists) from the list
     * @param item an item to be removed from the list
     */
    public void remove(int item) {
        if (ifContains(item)) {
            int index = indexFinder(item); // appropriate index for the item we're looking for
            while (index < size - 1) { // move the surrounding values around
                fibArray[index] = fibArray[index + 1];
                index++;
            }
            fibArray[size - 1] = 0;
            size--;
        }
    }

    /**
     * check if the item exists in the list
     */
    public boolean ifContains(int item) {
        int index = indexFinder(item); // index of the item we're looking for
        if (index > 0)
            return true;
        else if (index == 0) {
            if (fibArray[0] == item)
                return true;
            else 
                return false;
        }
        else return false;
    }

    /**
     * random draw a number from the list without removing
     * @return a random number from the list
     */
    public int grab() {
        return indexFinder ((int)((Math.random()) * size)); // Math.random() used to generate a random #
    }

    /**
     * prints out the number of unique items in the list
     */
    public int numItems() {
        int noDupl = 0; // number of non-duplicative items in the array
        int last = fibArray[0]; // last array checked
        if (size > 0) {
            noDupl++;
            for (int i = 1; i < size; i++) {
                if (last != fibArray[i]) {
                    noDupl++;
                    last = fibArray[i];
                }
            }
        }
        return noDupl;
    }

    /**
     * a helper method that returns the value that is stored in the array
     * @param index index of the value 
     * @return the value at the given index
     */
    public int lookup(int index) {
        return fibArray[index];
    }

    /**
     * The main method of the Fibonacci class that runs the more efficient method of calculating the Fibonacci number
     * @param args the sequence of Fibonacci number being searched
     */
    public static void main(String[] args) {
        Fibonacci subject = new Fibonacci();
        for (String s : args) {
            subject.add(subject.fibonacciIterative(Integer.parseInt(s)));
        }
        for (int i = 0; i < subject.size; i++) {
            System.out.print(subject.lookup(i));
        }
    }
}