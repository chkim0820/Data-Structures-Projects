/**
 * A WordCount class for CSDS 233 HW 4
 * @author Chaehyeon Kim
 */
public class WordCount {

    private HashTable table;

    public WordCount() {
        table = null;
    }
    
    /**
     * Takes a string as an input and prints out all the words encountered in that input along with their number of occurrences
     * @param str 
     * @return prints out all the words encountered in the input string
     */
    public String wordCount(String str) { // static?
        HashTable table = wordInsert(str); // helper method that inserts the words into the hash table

    }

    /**
     * Inserts all the individual words into the hash table
     * @param str the string where its words will be inserted into the hash table
     */
    private HashTable wordInsert(String str) {
        table = new HashTable(str.length() / 4); // arbitrary; fix how to rearrange later
        //String[] words = String.split(“\\P{Alpha}+”);
        for (int i = 0; i < str.length() ; i++) {
            StringBuilder builder = new StringBuilder();
            while ((str.charAt(i) >= 65 && str.charAt(i) <= 90) || (str.charAt(i) >= 97 && str.charAt(i) <= 122) && (i < str.length())) {
                builder.append(str.charAt(i)); // is this okay?
                i++;
            }
            String word = builder.toString();
            table.insert(word);
        }
        return table;
    }

    private int wordOccurence (String str) {
        for (int i = 0; i < table.getTableSize() && table.getTableContents(i) != null; i++) {
            HashLList list = table.getTableContents(i);
            String word = list.get;
        }
    }

    public public static void main(String[] args) {
        
    }
}
