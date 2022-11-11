/**
 * A WordCount class for CSDS 233 HW 4
 * @author Chaehyeon Kim
 */
public class WordCount {

    /** The field storing the HashTable used for this WordCount object */
    private HashTable table;

    /**
     * Constructor for WordCount class
     */
    public WordCount() {
        table = null;
    }
    
    /**
     * Takes a string as an input and prints out all the words encountered in that input along with their number of occurrences
     */
    public void wordCount(String str) {
        String[] arr = str.split("\\P{Alpha}+");
        for (int i = 0; i < arr.length; i++) { // inserting the words into the hash table
            table.insert(arr[i]);
        }
        // make a method in hashtable class to help iterate through the lists 
        // account for the possibility of more than one words ending up at the same index
    }
}
