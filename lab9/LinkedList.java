public class LinkedList<T> {
    private Node<T> head;
    private int size;


    public LinkedList() {
        head = null;
        size = 0;
    }


    public void insert(int pos, T data) {
        if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (pos == 0) {
            Node<T> newNode = new Node<>(data);
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> current = head;
            for (int i = 0; i < pos - 1; i++) {
                current = current.next;
            }
            Node<T> newNode = new Node<>(data);
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == 0) {
            head = head.next;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
    }


    public Node<T> getHead() {
        return head;
    }

}
