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

    public int getTableSize() {
        return tableSize;
    }

    /**
     * Constructor for the HashTable class
     * @param size the size of the table
     */
    public HashTable(int size) {
        table = new Entry[size];
        tableSize = size;
        numItem = 0;
    }

    // account for the possibility of more than one words ending up at the same index
    // try to make every index be for unique words
    public int probe(String str) {
        String s = str.toLowerCase(); // all letters converted to lowercase for case-insensitive table
        int index = (Math.abs(s.hashCode())) % tableSize; // h1
        int h2 = (Math.abs(s.hashCode())) % 13;
        while (table[index] != null) { // linear probing if full
            index = index + h2;
        }
        return index;
    }

    public void insert(String str) {
        if ((numItem / tableSize) >= 1)
            rehash();
        table[probe(str)].list.addLast(str);
        numItem++;
    }

    public void delete(String str) throws Exception {
        table[probe(str)].list.delete(str); // exception!
        numItem--;
    }

    public void rehash() {
        Entry[] temp = new Entry[(int)(tableSize * 1.25)];
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
        HashLList.LIterator it = table[index].list.iterator();
        int i = 0;
        while (it.hasNext()) {
            i++;
            it.next();
        }
        return i;
    }

    public String getWord(int index) {
        return table[index].list.getWord();
    }

    /**
     * A nested class for hash entries
     */
    private class Entry { // removed field not needed?
        private String key; // needed?
        private HashLList list;

        private Entry (String key){
            this.key = key;
            list = null;
        }
    }
}