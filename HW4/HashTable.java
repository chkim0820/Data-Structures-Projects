import java.util.NoSuchElementException;

/**
 * A HashTable class for CSDS 233 HW 4
 * @author Chaehyeon Kim cxk445
 */
public class HashTable {

    /** Field storing the table */
    private Entry[] table;
    /** Field storing the size of the table */
    private int tableSize;
    /** Field storing the number of items stored in the table */
    private int numItem;
    /** Field storing the number of deleted items; from occupied to empty indeces */
    private int numDeleted;

    /**
     * Constructor for the HashTable class
     * @param size the size of the table
     */
    public HashTable(int size) {
        table = new Entry[size];
        tableSize = size;
        numItem = 0;
        numDeleted = 0;
    }

    /**
     * Getter method for tableSize
     * @return tableSize of this HashTable
     */
    public int getTableSize() {
        return tableSize;
    }

    /**
     * Returns whether the table is empty at i index or not (for WordCount class)
     * @param i index of the table that is checked
     * @return whether the table is empty at i index or not
     */
    public boolean isEmpty(int i) {
        return (table[i] == null);
    }

    /**
     * Returns the number of items at each index
     * @param index the index of the table
     * @return the number of items at each index
     * @throws NoSuchElementException thrown when the table at this index is empty
     */
    public int getNumItems(int index) throws NoSuchElementException {
        if (table[index] != null)
            return table[index].list.getNumItem();
        else
            throw new NoSuchElementException("The table at this index is empty.");
    }

    /**
     * The word (key) stored at the input index
     * @param index the index of the table
     * @return the word (key) stored at the input index
     */
    public String getWord(int index) {
        if (table[index] != null)
            return table[index].key;
        else // if table empty at index, return null to indicate no word/key stored
            return null;
    }

    /**
     * Calculates the next prime number after i
     * @param i int value that will be used to calculate the next prime number
     * @return the next prime number after i
     */
    public int nextPrimeNumber(int i) {
        int prime = i + 1; // increments until prime found
        if (i >= 0) {
            if (i == 0 || i == 1 || i == 2) // if input 1 or 2, the output is 2 and 3 respectively
                return prime;
            while (true) { // loop through until prime number found
                boolean divided = false; // keeps track of whether prime can be divided by all the integers less than current value in prime and >= 2
                int j = 2; // starts dividing from 2
                while (j < prime && !divided) { // divides through all integers
                    if (prime % j == 0)
                        divided = true; // divided; not prime
                    j++; // to next integer
                }
                if (prime == j && divided == false) // all not divided & is prime
                    return prime; // end the loop
                prime++;
            }
        }
        return -1; // input value was less than 0
    }

    /**
     * Inserts a new value at the hash table at an appropriate index of the table
     * @param str the input String value
     */
    public void insert(String str) {
        if ((numItem / tableSize) >= 1 || ((2 * numDeleted) / tableSize) >= 1) // rehashing conditions
            rehash();
        int i = probe(str);
        if (table[i] == null) // have to initialize entry when entering a new value
            table[i] = new Entry(str.toLowerCase());    
        table[i].list.addLast(str);
        numItem++;
    }

    /**
     * Deletes the input from the hash table
     * @param str the string value of an item to be deleted
     * @throws Exception thrown when there is no such String value stored in the table
     */
    public void delete(String str) throws NoSuchElementException {
        int i = probe(str);
        if (table[i] != null) { 
            if (table[i].list.delete(str)) { // returns true if the list goes to null; table becomes empty at i
                table[i].removed = true;
                numDeleted++;
            }
            numItem--;
        }
        else // there is no str in the table
            throw new NoSuchElementException();
    }

    /**
     * Looks for an appropriate index for the input String value
     * @param str input String value
     * @return an appropriate index in the table for the input String value
     */
    private int probe(String str) {
        String s = str.toLowerCase(); // all letters converted to lowercase for case-insensitive table
        int index = (Math.abs(s.hashCode())) % tableSize; // h1
        int h2 = (Math.abs(s.hashCode())) % 13; // h2
        int i = 0; // for the while loop to not loop more than # tableSize
        while (!s.equals(getWord(index)) && (i <= tableSize) && (table[index] != null && !table[index].removed)) { // use h2 if original index full
            index = (index + h2) % tableSize;
            i++;
        }
        if (i > tableSize)
            return -1;
        return index;
    }

    /**
     * Rehashes the table accordingly when there's too many items in the table or
     * when there is too many deleted items
     */
    private void rehash() {
        Entry[] temp = table; // temp variable storing the old table
        if ((numItem / tableSize) >= 1) { // rehash() called because numItem too big; enlarge the table
            tableSize = nextPrimeNumber(tableSize);
            table = new Entry[tableSize];
        }
        else // rehash() called because numDeleted too big; keep the current table size
            table = new Entry[tableSize];
        for (int i = 0; i < temp.length; i++) { // move values over to temp
            if (temp[i] != null) {
                HashLList li = temp[i].list; // linked list in the original table at i
                HashLList.LIterator it = li.iterator(); // iterator for li
                while (it.hasNext()) {
                    String str = it.next(); // String value stored at node before pointer moves
                    int index = probe(str); // new index for str in the new table
                    if (table[index] == null) // create a new Entry if table null at index
                        table[index] = new Entry(str.toLowerCase());    
                    table[index].list.addLast(str); // insert all lower case String
                }
            }
        }
    }

    /**
     * A nested class for hash entries
     */
    private class Entry { // removed field not needed?
        /** A field storing the key */
        private String key;
        /** A field storing the list */
        private HashLList list;
        /** A field storing whether this Entry is removed or not */
        private boolean removed;

        /**
         * Constructor for the private Entry class
         * @param key key of this Entry
         */
        private Entry (String key){
            this.key = key;
            list = new HashLList();
            removed = false;
        }
    }
}