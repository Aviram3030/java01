package experis.ds;

public class DoublyLinkedList<T> {
    private Node<T> head;
    private int size;
    private Node<T> tail;

    public DoublyLinkedList() {
        size = 0;
    }

    public DoublyLinkedList(T val) {
        size = 1;
        head = new Node<>(val);
        tail = head;
    }

    public int find(T a) {
        Node<T> obj = tail;
        int i = 0;
        while (obj != null && !obj.getData().equals(a)) {
            obj = obj.getNext();
            i++;
        }

        if (obj == null) {
            return -1;
        }

        return i;
    }

    public int size() {
        return size;
    }

    public Node<T> getTail() {
        return tail;
    }

    public Node<T> getHead() {
        return head;
    }

    public T get(int index) {
        if (index < 0 || index > size)
            return null;

        return getTheElement(index);

    }

    private T getTheElement(int index){
        Node<T> obj = tail;
        for (int i = 0; i < index; i++) {
            obj = obj.getNext();
        }

        return obj.getData();
    }

    public void addAtHead(T a) {
        if (isEmpty()) {
            firstElement(a);
            return;
        }

        executeAddHead(a);
    }

    private void executeAddHead(T a){
        Node<T> obj = new Node<>(a);
        obj.setPrevious(head);
        head.setNext(obj);
        head = obj;
        changeSize(size + 1);
    }

    public void addAtTail(T a) {
        if (isEmpty()) {
            firstElement(a);
            return;
        }

        executeAddTail(a);
    }

    private void executeAddTail(T a){
        Node<T> obj = new Node<>(a);
        obj.setNext(tail);
        tail.setPrevious(obj);
        tail = obj;
        changeSize(size + 1);
    }

    private void firstElement(T a) {
        if (size == 0) {
            head = new Node<>(a);
            tail = head;
            changeSize(size + 1);
        }
    }

    private Boolean isEmpty() {
        return size == 0;
    }


    public void remove(T a) {
        if (a.equals(tail.getData())) {
            tail = tail.getNext();
            changeSize(size - 1);
            return;
        }
        if (a.equals(head.getData())) {
            head = head.getNext();
            changeSize(size - 1);
            return;
        }

        removeElement(a);
    }


    private void removeElement(T a) {
        Node<T> obj = tail.getNext();

        for (int i = 0; i < size - 1; i++) {
            if (a.equals(obj.getData())) {
                obj.getPrevious().setNext(obj.getNext());
                obj.getNext().setPrevious(obj.getPrevious());
                changeSize(size - 1);
                return;
            }
            obj = obj.getNext();
        }
    }

    public void insert(T a, int at) {
        if (at < 0 || at > size + 1) {
            return;
        }

        if (at == 1) {
            addAtTail(a);
            return;
        }

        if (at == size + 1) {
            addAtHead(a);
            return;
        }
        insertElement(a, at);

    }

    private void insertElement(T a, int at){
        Node<T> obj = tail;
        for (int i = 0; i < at - 1; i++) {
            obj = obj.getNext();
        }

        Node<T> aNode = new Node<>(a);
        obj.setNext(aNode);
        aNode.setPrevious(obj);

        obj = obj.getNext();
        aNode.setNext(obj);
        obj.setPrevious(aNode);

        changeSize(size + 1);

    }

    public Node<T> popHead() {
        if (size == 0) {
            return null;
        }
        Node<T> a = head;
        head = head.getPrevious();
        changeSize(size - 1);

        return a;
    }

    public Node<T> popTail() {
        if (size == 0) {
            return null;
        }
        Node<T> a = tail;
        tail = tail.getNext();
        changeSize(size - 1);

        return a;

    }

    private void changeSize(int n) {
        size = n;
    }

    public Boolean equalsTo(DoublyLinkedList<T> a) {
        return this.equals(a);
    }

    public String toString() {
        Node<T> obj = tail;
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            s.append(obj.getData().toString());
            s.append(" ");
            obj = obj.getNext();
        }

        return s.toString();
    }

    public void addTail(DoublyLinkedList<T> a) {
        changeSize(size + a.size());
        tail.setPrevious(a.getHead());
        a.getHead().setNext(tail);
        tail = a.getTail();
    }

    public void addHead(DoublyLinkedList<T> a) {
        changeSize(size + a.size());
        head.setNext(a.getTail());
        a.getTail().setPrevious(head);
        head = a.getHead();
    }

    public DoublyLinkedList<T> intersection(DoublyLinkedList<T> a) {
        DoublyLinkedList<T> newList = new DoublyLinkedList<>();
        Node<T> obj = tail;

        for (int i = 0; i < size; i++) {
            if(a.find(obj.getData()) != -1){
                newList.addAtTail(obj.getData());
            }

            obj = obj.getNext();
        }

        return newList;
    }

    public DoublyLinkedList<T> not(DoublyLinkedList<T> a) {
        DoublyLinkedList<T> newList = new DoublyLinkedList<>();
        Node<T> obj = tail;

        for (int i = 0; i < size; i++) {
            if(a.find(obj.getData()) == -1){
                newList.addAtTail(obj.getData());
            }
            obj = obj.getNext();
        }

        return newList;

    }


}
