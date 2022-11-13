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
     * @param str the string to be processed through this method
     */
    public void wordCount(String str) {
        String[] arr = str.split("\\P{Alpha}+"); // array containing str divided up to alphabetical sub-strings (words)
        table = new HashTable(arr.length); // new hash table to input all the words from str in
        for (int i = 0; i < arr.length; i++) { // inserting the words from arr into the hash table
            table.insert(arr[i]);
        }
        for (int j = 0; j < table.getTableSize(); j++) { // print out the words and their number of times repeated in str
            if (!table.isEmpty(j) && !table.getWord(j).isEmpty()) // if hash table not empty at i or word is null
                System.out.println("The word " + '"' + table.getWord(j) + '"' + " is repeated " + table.getNumItems(j) + " times.");
        }
    }

    /**
     * The main method for WordCount class
     * @param args the input String array
     */
    public static void main(String[] args) {
        // Testing duplicates in different cases
        WordCount wc = new WordCount();
        System.out.println("The method wordCount() is case-insensitive, meaning the distinction between upper-case and lower-case letters don't matter. \nHere, the word " + '"' + "hello" + '"' + " is in the input String three times; one in all upper-case, the other in all lower-case, and the other the mix of two.");
        System.out.println("The output is printed below:");
        wc.wordCount("hello HELLO heLLo");
        
        // Testing different words
        System.out.println("\nNow, wordCount() will receive a String that contains many different words as its input.\nThe input string in this case is " + '"' + "Hi, my name is Chaehyeon." + '"');
        System.out.println("The output is printed below:");
        wc.wordCount("Hi, my name is Chaehyeon.");

        // Testing duplicates in a long sentence with different words
        System.out.println("\nAnother possibility is a sentence containing different words as well as duplicates. \nThe input is " + '"' + "Hello. Hi! Hello. Home sweet home." +'"');
        System.out.println("The output is printed below:");
        wc.wordCount("Hello. Hi! Hello. Home SWEET home.");

        // Having multiple non-alphabetical characters in between words
        System.out.println("\nThis time, a String input with multiple non-alphabetical characters in between words is tested.\nThe input is " + '"' + "Hi.   -___-  \n HI!!!!!!!! hello......\t hello?" + '"' +"HELLO?" +'"' + '"' +".");
        System.out.println("The output is printed below:");
        wc.wordCount("Hi.   -___-  \n HI!!!!!!!! hello......\t hello? " + '"' +"HELLO?" +'"');
    }
}
