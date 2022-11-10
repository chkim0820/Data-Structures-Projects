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
    public void wordCount(String str) {
        HashTable table = wordInsert(str); // helper method that inserts the words into the hash table
        for (int i = 0; i < table.getTableSize(); i++) {
            HashLList list = table.getTableContents(i);
            String word = list.getItem();
            int occur = 0;
            if (list != null) {
                HashLList.LIterator it = list.iterator();
                while (it.hasNext()) {
                    it.next();
                    occur++;
                }
            }
            System.out.println("The word " + word + " appeared " + String.valueOf(occur) + " many times.");
        }
        // can create another hash table with the keys being the number occurrences or just arrays of 
    }
    // Yes, please use ''Math.abs(str.hashCode())'' to calculate the numerical code of the "words".

    /**
     * Inserts all the individual words into the hash table
     * @param str the string where its words will be inserted into the hash table
     */
    private HashTable wordInsert(String str) {
        table = new HashTable(str.length() / 4); // arbitrary; fix how to rearrange later
        //String[] words = String.split(“\P{Alpha}+”);
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

    private String wordOccur() {
        HashLList list = table.getTableContents(i);
        String word = list.getItem();
        if (list != null) {
            HashLList.LIterator it = list.iterator();
            int occur = 0;
            while (it.hasNext()) {
                it.next();
                occur++;
            }
        }
    }
}
