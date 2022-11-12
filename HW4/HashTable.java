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
    /** Field storing the number of deleted items; occupied to empty indeces */
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

    public int getTableSize() {
        return tableSize;
    }

    public int getNumRepeats(int index) {
        /**
        HashLList.LIterator it = table[index].list.iterator();
        int i = 0;
        while (it.hasNext()) {
            i++;
            it.next();
        }
        return i;
        */
        if (table[index] != null)
            return table[index].list.getNumItem();
        else
            throw new NoSuchElementException("The table at this index is empty.");
    }

    public String getWord(int index) {
        if (table[index] != null)
            return table[index].list.getWord();
        else
            return null;
    }

    // account for the possibility of more than one words ending up at the same index
    // try to make every index be for unique words
    private int probe(String str) {
        String s = str.toLowerCase(); // all letters converted to lowercase for case-insensitive table
        int index = (Math.abs(s.hashCode())) % tableSize; // h1
        int h2 = (Math.abs(s.hashCode())) % 13; // h2
        int i = 0;
        while (!s.equals(getWord(index)) && (table[index] != null && table[index].removed) && (i < tableSize)) { // use h2 if original index full
            index = (index + h2) % tableSize;
            i++;
        }
        return index;
    }

    public void insert(String str) { // have to initialize entry when entering a new value
        if ((numItem / tableSize) >= 1 || (numDeleted / tableSize) >= 1) // rehashing conditions
            rehash();
        int i = probe(str);
        if (table[i] == null)
            table[i] = new Entry(str.toLowerCase());    
        table[i].list.addLast(str); // insert all lower case String
        numItem++;
    }

    public void delete(String str) throws Exception { // rehash?
        int i = probe(str);
        if (table[i].list.delete(str)) {
            table[i].removed = true;
            numDeleted++;
        }
        numItem--;
    }

    public void rehash() {
        Entry[] temp = table;
        if ((numItem / tableSize) >= 1) {
            table = new Entry[tableSize * 2 - 1];
            tableSize = tableSize * 2 - 1;
        }
        else
            table = new Entry[tableSize];
        for (int i = 0; i < temp.length; i++) { // move values over to temp
            if (temp[i] != null) {
                HashLList li = temp[i].list;
                HashLList.LIterator it = li.iterator();
                //int index = probe(temp[i].key);
                while (it.hasNext()) {
                    String str = it.next();
                    int index = probe(str);
                    if (table[index] == null)
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
        private String key; // needed?
        private HashLList list;
        private boolean removed;

        private Entry (String key){
            this.key = key;
            list = new HashLList();
            removed = false;
        }
    }
}