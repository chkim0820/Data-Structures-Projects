/**
 * A HashTable class for CSDS 233 HW 4
 * @author Chaehyeon Kim cxk445
 */
public class HashTable {

    private Entry[] table;
    private int tableSize;

    public HashTable(int size) {
        table = new Entry[size];
        tableSize = size;
    }

    public int getTableSize() {
        return tableSize;
    }

    /**
     * Returns the linked list at the specified index from the table
     * @param index specified index of the table at which the linked list is searched for
     * @return the linked list at the index
     */
    public HashLList getTableContents(int index) {
        return table[index].list;
    }

    public int probe(String str) { //idk how to do this method; delete the middle section?
        return str.hashCode();
    }

    //rehashing!
    public void insert(String str) {
        int i = str.hashCode();
        table[i].list.addLast(str);
    }

    public void delete(String str) {
        int i = str.hashCode();
        table[i].list.delete(str);
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