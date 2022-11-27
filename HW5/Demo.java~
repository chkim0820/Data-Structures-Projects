import java.util.Arrays;

/**
 * Demo class to carry out benchmarking experiments for CSDS 233 HW 5
 * @author Chaehyeon Kim cxk445
 */
public class Demo {

    public static void main(String[] args){
        int size = Integer.parseInt(args[0]);
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
        int[] arr = Sort.generateRandomArray(size);
        System.out.println("Randomly generated array: " + Arrays.toString(arr));
        // Insertion sort
        long time = System.nanoTime();
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
        Sort.upgradedQuickSort(arr, 3, 3);
        time = System.nanoTime() - time;
        System.out.printf("Upgraded quick sort time: %1d\n", time);

        /* Sorted (Descending) array */
        Sort.mergeSort(arr);
        System.out.println("Sorted (descending) array: " + Arrays.toString(arr));
        // Insertion sort
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
        Sort.upgradedQuickSort(arr, 3, 3);
        time = System.nanoTime() - time;
        System.out.printf("Upgraded quick sort time: %1d\n", time);
        
        /* Reverse-sorted (ascending) array */
        Arrays.sort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
        // Insertion sort
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
        Sort.upgradedQuickSort(arr, 3, 3);
        time = System.nanoTime() - time;
        System.out.printf("Upgraded quick sort time: %1d\n", time);
        }
}
