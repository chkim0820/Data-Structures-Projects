public class Queue extends SinglyLinkedList {

    private SinglyLinkedList list;

    public Queue () {
        this.list = new SinglyLinkedList();
    }

    public Integer remove() {
        return list.removeFirst();
    }
}
