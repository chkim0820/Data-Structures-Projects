import java.util.Arrays;

/**
 * Demo class to carry out benchmarking experiments for CSDS 233 HW 5
 * @author Chaehyeon Kim cxk445
 */
public class Demo {

    /**
     * The main method to carry out different sorting methods of arrays with input size.
     * @param args size of the array.
     */
    public static void main(String[] args){
        int size = Integer.parseInt(args[0]); // the size of the array
        System.out.printf("Results for arrays of size %d\n", size);

        /*
        Carry out your timing for the methods here:
        -> Generate Sorted, unsorted, random arrays of size "size"
        -> Run the sorting algorithms on these arrays and time them
        -> Printout the results like this:
        long time = *get the the runtime*
        System.out.printf(" ____sort time: %ld\n",time);
        */

        /* Random array */
        int[] randomArray = Sort.generateRandomArray(size);
        System.out.println("Randomly generated array: " + Arrays.toString(randomArray));

        // Java's in-built sort
        int[] arr = randomArray;
        long time = System.nanoTime();
        Arrays.sort(arr);
        time = System.nanoTime() - time;
        System.out.printf("Java's in-built sort sort time: %1d\n", time);

        // Insertion sort
        arr = randomArray;
        time = System.nanoTime();
        Sort.insertionSort(arr);
        time = System.nanoTime() - time;
        System.out.printf("Insertion sort time: %1d\n", time);

        // Bubble sort
        arr = randomArray;
        time = System.nanoTime();
        Sort.bubbleSort(arr);
        time = System.nanoTime() - time;
        System.out.printf("Bubble sort time: %1d\n", time);

        // Shell sort
        arr = randomArray;
        time = System.nanoTime();
        Sort.shellSort(arr);
        time = System.nanoTime() - time;
        System.out.printf("Shell sort time: %1d\n", time);

        // Quick sort
        arr = randomArray;
        time = System.nanoTime();
        Sort.quickSort(arr);
        time = System.nanoTime() - time;
        System.out.printf("Quick sort time: %1d\n", time);

        // Merge sort
        arr = randomArray;
        time = System.nanoTime();
        Sort.mergeSort(arr);
        time = System.nanoTime() - time;
        System.out.printf("Merge sort time: %1d\n", time);

        // Upgraded quick sort
        arr = randomArray;
        int depthLimit = (int)(Math.log(arr.length) / Math.log(2));
        time = System.nanoTime();
        Sort.upgradedQuickSort(arr, depthLimit, arr.length / 4);
        time = System.nanoTime() - time;
        System.out.printf("Upgraded quick sort time: %1d\n", time);


        /* Sorted (Descending) array */
        Sort.mergeSort(arr);
        System.out.println("Sorted (descending) array: " + Arrays.toString(arr));

        // Java's in-built sort; using ascending sorted array since "sorted" arrays for Java's built-in sort() is in ascending order
        Arrays.sort(arr); // sorting the array in ascending order
        time = System.nanoTime();
        Arrays.sort(arr);
        time = System.nanoTime() - time;
        System.out.printf("Java's in-built sort time: %1d\n", time);

        // Insertion sort
        Sort.mergeSort(arr); // sorting the array back in descending order for Sort.java sorting methods
        time = System.nanoTime();
        Sort.insertionSort(arr);
        time = System.nanoTime() - time;
        System.out.printf("Insertion sort time: %1d\n", time);

        // Bubble sort
        time = System.nanoTime();
        Sort.bubbleSort(arr);
        time = System.nanoTime() - time;
        System.out.printf("Bubble sort time: %1d\n", time);

        // Shell sort
        time = System.nanoTime();
        Sort.shellSort(arr);
        time = System.nanoTime() - time;
        System.out.printf("Shell sort time: %1d\n", time);

        // Quick sort
        time = System.nanoTime();
        Sort.quickSort(arr);
        time = System.nanoTime() - time;
        System.out.printf("Quick sort time: %1d\n", time);

        // Merge sort
        time = System.nanoTime();
        Sort.mergeSort(arr);
        time = System.nanoTime() - time;
        System.out.printf("Merge sort time: %1d\n", time);

        // Upgraded quick sort
        time = System.nanoTime();
        Sort.upgradedQuickSort(arr, depthLimit, arr.length / 4);
        time = System.nanoTime() - time;
        System.out.printf("Upgraded quick sort time: %1d\n", time);
        

        /* Reverse-sorted (ascending) array */
        Arrays.sort(arr);
        int[] reverseSortedArray = arr;
        System.out.println("Reverse-sorted (descending) array: " + Arrays.toString(reverseSortedArray));

        // Java's in-built sort; using descending sorted array since "reverse-sorted" arrays for Java's built-in sort() is in descending order
        Sort.mergeSort(arr); // sorting the array in descending order
        time = System.nanoTime();
        Arrays.sort(arr); 
        time = System.nanoTime() - time;
        System.out.printf("Java's in-built sort sort time: %1d\n", time);

        // Insertion sort
        arr = reverseSortedArray; // setting array back to reverse-sorted array
        time = System.nanoTime();
        Sort.insertionSort(arr);
        time = System.nanoTime() - time;
        System.out.printf("Insertion sort time: %1d\n", time);

        // Bubble sort
        arr = reverseSortedArray;
        time = System.nanoTime();
        Sort.bubbleSort(arr);
        time = System.nanoTime() - time;
        System.out.printf("Bubble sort time: %1d\n", time);

        // Shell sort
        arr = reverseSortedArray;
        time = System.nanoTime();
        Sort.shellSort(arr);
        time = System.nanoTime() - time;
        System.out.printf("Shell sort time: %1d\n", time);

        // Quick sort
        arr = reverseSortedArray;
        time = System.nanoTime();
        Sort.quickSort(arr);
        time = System.nanoTime() - time;
        System.out.printf("Quick sort time: %1d\n", time);

        // Merge sort
        arr = reverseSortedArray;
        time = System.nanoTime();
        Sort.mergeSort(arr);
        time = System.nanoTime() - time;
        System.out.printf("Merge sort time: %1d\n", time);

        // Upgraded quick sort
        arr = reverseSortedArray;
        time = System.nanoTime();
        Sort.upgradedQuickSort(arr, depthLimit, arr.length / 4);
        time = System.nanoTime() - time;
        System.out.printf("Upgraded quick sort time: %1d\n", time);
    }
}