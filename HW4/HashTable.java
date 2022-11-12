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

    // account for the possibility of more than one words ending up at the same index
    // try to make every index be for unique words
    public int probe(String str) {
        String s = str.toLowerCase(); // all letters converted to lowercase for case-insensitive table
        int index = (Math.abs(s.hashCode())) % tableSize; // h1
        int h2 = (Math.abs(s.hashCode())) % 13; // h2
        while (!s.equals(getWord(index)) || (table[index] != null && table[index].removed)) { // use h2 if original index full
            index = index + h2;
        }
        return index;
    }

    public void insert(String str) {
        if ((numItem / tableSize) >= 1 || (numDeleted / tableSize) >= 1) // rehashing conditions
            rehash();
        table[probe(str)].list.addLast(str.toLowerCase()); // insert all lower case String
        numItem++;
    }

    public void delete(String str) throws Exception {
        int i = probe(str);
        if (table[i].list.delete(str)) {
            table[i].removed = true;
            numDeleted++;
        }
        numItem--;
    }

    public void rehash() {
        Entry[] temp;
        if ((numItem / tableSize) >= 1)
            temp = new Entry[(int)(tableSize * 1.25)];
        else
            temp = new Entry[tableSize];
        for (int i = 0; i < table.length; i++) { // move values over to temp
            HashLList li = table[i].list;
            HashLList.LIterator it = li.iterator();
            while (it.hasNext()) {
                String str = it.next();
                temp[probe(str)].list.addLast(str);
            }
        }
        table = temp;
        tableSize = (int)(tableSize * 1.25);
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
        return table[index].list.getNumItem();
    }

    public String getWord(int index) {
        if (table[index] != null)
            return table[index].list.getWord();
        else
            return null;
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
            list = null;
            removed = false;
        }
    }
}