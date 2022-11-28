import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;
import java.util.Arrays;
import java.util.Scanner;

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
        // loop through the whole array to find appropriate indeces for each values at i
        for (int i = 1; i < arr.length; i++) {
            int save = arr[i]; // saves the value at i index
            int j; // for traversal while shifting over values
            // shift values until value at j-1 is smaller or equal to value at i
            for (j = i; j > 0 && arr[j - 1] < save; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = save; // insert the saved value at the appropriate index found in the loop above
        }
    }

    /**
     * Uses the bubble sort algorithm to sort the input array in descending order.
     * @param arr an input array of integers.
     */
    public static void bubbleSort(int[] arr) {
        // loop through the whole array; i start from end to align with end value of the nested loop
        for (int i = arr.length - 1; i > 0; i--) {
            // loop through array while swapping to move the biggest value of the unsorted region to the end
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[j + 1]) // swap so the bigger value moves back
                    swap(arr, j, j + 1);
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
        // find the ideal initial increment value; biggest number in Hibbard's sequence less than length of array
        int incr = 1;
        while (2 * incr <= arr.length)
            incr = 2 * incr;
        incr = incr - 1;
        // loop through to sort with shell sort while stride is 1
        while (incr >= 1) {
            // insertion sort at i stride
            for (int i = incr; i < arr.length; i++) {
                int save = arr[i];
                int j;
                // shift values over until appropriate index to insert found
                for (j = i; j > incr - 1 && save > arr[j - incr]; j = j - incr)
                    arr[j] = arr[j - incr];
                arr[j] = save;
            }
            incr = incr / 2; // the next lower number in sequence to be incr
        }
    }

    /**
     * Uses the quick sort algorithm to sort the input array in descending order.
     * @param arr an input array of integers.
     */
    public static void quickSort(int[] arr) {
        recQuickSort(arr, 0, arr.length - 1);
    }

    /**
     * Helper recursive method for quickSort()
     * @param arr array to be sorted recursively using quick sort
     * @param left left (first) index of array
     * @param right right (last) index of array
     */
    private static void recQuickSort(int[] arr, int left, int right) {
        // if subarray length is 1, return; base case
        if (left >= right)
            return;
        int split = partition(arr, left, right); // partition the array into two subarrays
        // left subarray
        if (right == split) // if recursion call repeated, decrement split by 1
            recQuickSort(arr, left, split - 1);
        else
            recQuickSort(arr, left, split);
        // right subarray
        if (left == split) // if recursion call repeated, increment split by 1
            recQuickSort(arr, split + 1, right);
        else
            recQuickSort(arr, split, right);
    }

    /**
     * Helper method for quickSort() that sorts the array using pivot and returns the last index of to-be-left array
     * @param arr array to be sorted and divided
     * @param left left index of the array to be sorted
     * @param right right index of the array to be sorted
     * @return the last index of to-be-left array
     */
    private static int partition(int[] arr, int left, int right) { // return j (right pointer) location
        int pivot = (arr[left] + arr[right] + arr[(left + right) / 2]) / 3; // take first, middle, last's median
        int i = left - 1; // pointer starting from the left
        int j = right + 1; // pointer starting from the right
        // loop through while i and j crosses while swapping values
        while (true) {
            do {
                i++;
            } while (arr[i] > pivot);
            do {
                j--;
            } while (arr[j] < pivot);
            if (i < j)
                swap(arr, i, j);
            else
                return j;
        }
    }

    /**
     * Uses the merge sort algorithm to sort the input array into descending order.
     * @param arr an input array of integers.
     */
    public static void mergeSort(int[] arr) {
        int[] temp = new int[arr.length]; // temp array for merge sort
        recMergeSort(arr, temp, 0, arr.length - 1);
    }

    /**
     * Helper method for mergeSort() that is called recursively
     * @param arr array to be sorted
     * @param temp temporary array for sorting
     * @param left left (first) index of the array
     * @param right right (last) index of the array
     */
    private static void recMergeSort(int[] arr, int[] temp, int left, int right) {
        // base case: subarray length of 1
        if (left >= right)
            return;
        int split = (left + right) / 2; // find the middle index of the array
        recMergeSort(arr, temp, left, split); // left array
        recMergeSort(arr, temp, split + 1, right); // right array
        merge(arr, temp, left, split, split + 1, right); // merge left and right array
    }

    /**
     * Helper method for mergeSort() that merges arrays
     * @param arr array to be sorted with merge sort method
     * @param temp temporary array
     * @param leftStart index at the start of the left array
     * @param leftEnd index at the end of the left array
     * @param rightStart index at the start of the right array
     * @param rightEnd index at thte end of the right array
     */
    private static void merge(int[] arr, int[] temp, int leftStart, int leftEnd, int rightStart, int rightEnd) {
        int i = leftStart; // pointer for left array
        int j = rightStart; // pointer for right array
        int index = leftStart; // index of temp array
        // insert value of subarrays into the temp array until one of the subarray's pointers reach the end
        while (i < rightStart && j < rightEnd + 1) {
            if (arr[i] > arr[j]) {
                temp[index] = arr[i];
                i++;
            }
            else {
                temp[index] = arr[j];
                j++;
            }
            index++;
        }
        // insert already-sorted values of subarray if end not reached
        while (i < rightStart) {
            temp[index] = arr[i];
            i++;
            index++;
        }
        while (j < rightEnd + 1) {
            temp[index] = arr[j];
            j++;
            index++;
        }
        // move values in temp array back to arr
        for (int move = leftStart; move < rightEnd + 1; move++) {
            arr[move] = temp[move];
        }
    }

    /**
     * Uses the upgraded quick sort version from written problem 2 and 3 to sort the input array into descending order, where we switch to merge sort when it reaches the depth limit d and switch to insertion sort when the subarrays have fewer than k elements.
     * @param input an input array of integers.
     * @param d the depth limit.
     * @param k the minimum number of elements a subarray can have before switching to insertion sort.
     */
    public static void upgradedQuickSort(int[] input, int d, int k) {
        int depth = 0; // keep track of depth
        // return 1 if switching to merge sort; -1 if switching to insertion sort; 0 if already sorted
        int whichSort = recUpgradedQuickSort(input, depth, d, k, 0, input.length - 1);
        if (whichSort > 0)
            mergeSort(input);
        else if (whichSort < 0)
            insertionSort(input);
    }

    /**
     * Helper method for upgradedQuickSort()
     * @param arr an input array of integers.
     * @param depth current depth.
     * @param depthLimit the depth limit.
     * @param k the minimum number of elements a subarray can have before switching to insertion sort.
     * @param left left (first) index of current subarray.
     * @param right right (last) index of current subarray.
     * @return return when the current subarray's length is 1.
     */
    private static int recUpgradedQuickSort(int[] arr, int depth, int depthLimit, int k, int left, int right) {
        // if subarray length is 1, return; base case
        if (left >= right)
            return 0;
        // depth reachs the depth limit
        else if (depth >= depthLimit)
            return 1;
        // subarray has fewer than k elements
        else if ((right - left) + 1 < k)
            return -1;
        int split = partition(arr, left, right); // partition the array into two subarrays
        depth++;
        // keep track of whether subarrays need to be switched to other sorting methods
        // check left subarray first
        int s1 = recUpgradedQuickSort(arr, depth, depthLimit, k, left, split); // left subarray
        if (s1 > 0) // left subarray reached depth limit; switch to merge sort
            return 1;
        else if (s1 < 0) // left subarray has fewer than k elements; switch to insertion sort
            return -1;
        // then check right subarray
        int s2 = recUpgradedQuickSort(arr, depth, depthLimit, k, split + 1, right); // right subarray
        if (s1 == 0 && s2 == 0) // both left & right subarrays have been sorted
            return 0;
        if (s2 > 0) // one of the subarrays reached depth limit; switch to merge sort
            return 1;
        else // one of the subarrays have fewer than k elements; switch to insertion sort
            return -1;
    }

    /**
     * Returns an array of random integers of size n.
     * @param n the size of an array to be returned.
     * @return an array of random integers of size n.
     */
    public static int[] generateRandomArray(int n) {
        int[] arr = new int[n]; // a new array of size n
        // insert a random integer at each index of arr
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * n * 10);
        }
        return arr;
    }

    /**
     * Reads the commands specified in the file and then prints the corresponding result of the commands.
     * @param filepath a String input corresponding to the filepath of a .txt file.
     */
    public static void readCommands(String filepath) {
        File file = new File(filepath); // Java File class object to store file of the input filepath

        try {
            Scanner scanner = new Scanner(file); // Java Scanner to scan through the file
            // while there's contents in the file, print them out
            while (scanner.hasNextLine()) {
                // The current line of the file
                String command = scanner.nextLine();
                // Safety feature for empty lines
                if (command.charAt(0) != ' ') {
                    String[] arr = command.split("[:\\[\\] ]"); // Split the command line into arrays with ':', '[', ']', ' ' as dividers
                    String sortType = arr[0]; // the first chunk of line indicates the sort type

                    // if statements to figure out which sorting method to use & print out accordingly
                    if (sortType.equals("insertionSort")) {
                        int[] array = toArray(arr[arr.length - 1]);
                        insertionSort(array);
                        System.out.println("insertionSort: " + Arrays.toString(array));
                    }
                    else if (sortType.equals("bubbleSort")) {
                        int[] array = toArray(arr[arr.length - 1]);
                        bubbleSort(array);
                        System.out.println("bubbleSort: " + Arrays.toString(array));
                    }
                    else if (sortType.equals("shellSort")) {
                        int[] array = toArray(arr[arr.length - 1]);
                        shellSort(array);
                        System.out.println("shellSort: " + Arrays.toString(array));
                    }
                    else if (sortType.equals("quickSort")) {
                        int[] array = toArray(arr[arr.length - 1]);
                        quickSort(array);
                        System.out.println("quickSort: " + Arrays.toString(array));
                    }
                    else if (sortType.equals("mergeSort")) {
                        int[] array = toArray(arr[arr.length - 1]);
                        mergeSort(array);
                        System.out.println("mergeSort: " + Arrays.toString(array));
                    }
                    else if (sortType.equals("upgradedQuickSort")) {
                        int[] array = toArray(arr[arr.length - 1]);
                        upgradedQuickSort(array, (int)(Math.log(arr.length) / Math.log(2)), array.length / 4);
                        System.out.println("upgradedQuickSort: " + Arrays.toString(array));
                    }         
                }       
            }
            scanner.close(); // close scanner

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method for readCommands() that turns a string of int[] back into int[]
     * @param str a string of int[]
     * @return the input string converted back to int[]
     */
    private static int[] toArray(String str) {
        // turn String into array and perform method accordingly
        String[] temp = str.split("[^-0123456789]"); // split the string into numbers
        int[] arr = new int[temp.length]; // array of int to be returned
        for (int i = 0; i < temp.length; i++) {
            arr[i] = Integer.parseInt(temp[i]);
        }
        return arr;
    }
}