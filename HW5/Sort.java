import java.lang.Math;

/**
 * Sort class for CSDS 233 HW 5
 * @author Chaehyeon Kim cxk445
 */
public class Sort {

    /**
     * Uses the insertion sort algorithm to sort the input array in descending order.
     * @param arr an input array of integers.
     */
    public static void insertionSort(int[] arr) {
        if (arr.length > 1) {
            int i = 1;
            while (i < arr.length) {
                int save = arr[i];
                int findIndex = i - 1;
                while (arr[findIndex] > arr[i] && findIndex > 0) {
                    findIndex--;
                }
                if (findIndex == i - 1  && arr[findIndex] <= arr[i]) // second condition is for arrays with length of 2
                    i++;
                else {
                    int trav = i;
                    while (trav > findIndex) {
                        arr[trav] = arr[trav - 1];
                        trav--;
                    }
                    arr[findIndex] = save;
                    i++;
                }
            }
        }
    }

    /**
     * Uses the bubble sort algorithm to sort the input array in descending order.
     * @param arr an input array of integers.
     */
    public static void bubbleSort(int[] arr) {
        if (arr.length > 1) {
            int sorted = arr.length - 1; // the last index of unsorted region
            while (sorted > 0) {
                int i = 0;
                while (i < sorted) {
                    if (arr[i] > arr[i + 1])
                        swap(arr, i, i + 1);
                    i++;
                }
                sorted--;
            }
        }
    }

    /**
     * A helper method for swapping values of two indeces in an array
     * @param arr the input array to have values swapped
     * @param i first index of array to be swapped
     * @param j second index of array to be swapped
     */
    private static void swap(int[] arr, int i, int j) {
        int save = arr[i];
        arr[i] = arr[j];
        arr[j] = save;
    }

    /**
     * Uses the shell sort algorithm using Hibbard’s sequence to sort the input array in descending order.
     * @param arr an input array of integers.
     */
    public static void shellSort(int[] arr) {
        if (arr.length > 1) {
            int i = (int)(Math.log(arr.length) / Math.log(2)) - 1;
            int incr = incrFinder(i);
            while (incr > 0) {
                // insertion sort with incr as strides
                
                incr = incrFinder(i - 1);
            }
        }
    }

    private static int incrFinder(int i) {
        return (int) Math.pow(2, i) - 1;
    }

    /**
     * Uses the quick sort algorithm to sort the input array in descending order.
     * @param arr an input array of integers.
     */
    public static void quickSort(int[] arr) {

    }

    /**
     * Uses the merge sort algorithm to sort the input array into descending order.
     * @param arr an input array of integers.
     */
    public static void mergeSort(int[] arr) {

    }

    /**
     * Uses the upgraded quick sort version from written problem 2 and 3 to sort the input array into descending order, where we switch to merge sort when it reaches the depth limit d and switch to insertion sort when the subarrays have fewer than k elements.
     * @param input an input array of integers.
     * @param d the depth limit.
     * @param k the minimum number of elements a subarray can have before switching to insertion sort.
     */
    public static void upgradedQuickSort(int[] input, int d, int k) {

    }

    /**
     * Returns an array of random integers of size n.
     * @param n the size of an array to be returned.
     * @return an array of random integers of size n.
     */
    public static int[] generateRandomArray(int n) {
        return null;
    }

    /**
     * Reads the commands specified in the file and then prints the corresponding result of the commands.
     * @param filepath a String input corresponding to the filepath of a .txt file.
     */
    public static void readCommands(String filepath) {

    }
}