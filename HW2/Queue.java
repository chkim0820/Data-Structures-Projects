public class Queue extends SinglyLinkedList {

    private SinglyLinkedList list;

    public Queue () {
        this.list = new SinglyLinkedList();
    }

    public int add(Integer e) {
        int save = list.add(e);
        return new Integer(save);
    }

    public Integer remove() {
        return list.removeFirst();
    }
}
