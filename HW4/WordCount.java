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
        table = new HashTable(arr.length);
        for (int i = 0; i < arr.length; i++) { // inserting the words (Strings) into the hash table
            table.insert(arr[i]);
        }
        for (int j = 0; j < table.getTableSize(); j++) {
            System.out.println("The word " + table.getWord(j) + " is repeated " + table.getNumRepeats(j) + " times.");
        }
    }
}
