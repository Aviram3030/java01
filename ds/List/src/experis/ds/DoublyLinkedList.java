package experis.ds;

public class DoublyLinkedList<T> {
    private Node<T> tail;
    private Node<T> head;
    private int size;

    public DoublyLinkedList() {
        size = 0;
    }

    public DoublyLinkedList(T val) {
        size = 1;
        tail = new Node<>(val);
        head = tail;
    }

    public int find(T a) {
        Node<T> obj = head;
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

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public T get(int index) {
        if (index < 0 || index > size)
            return null;

        return getTheElement(index);

    }

    private T getTheElement(int index){
        Node<T> obj = head;
        for (int i = 0; i < index; i++) {
            obj = obj.getNext();
        }

        return obj.getData();
    }

    public void addAtTail(T a) {
        if (isEmpty()) {
            firstElement(a);
            return;
        }

        executeAddHead(a);
    }

    public void addTail(Node<T> a){
        tail.setNext(a);
        a.setPrevious(tail);
        a.setNext(null);
        tail = a;

        changeSize(size + 1);

    }

    private void executeAddTail(T a){
        Node<T> obj = new Node<>(a);
        obj.setPrevious(tail);
        tail.setNext(obj);
        tail = obj;
        changeSize(size + 1);
    }

    public void addAtHead(T a) {
        if (isEmpty()) {
            firstElement(a);
            return;
        }

        executeAddTail(a);
    }

    private void executeAddHead(T a){
        Node<T> obj = new Node<>(a);
        obj.setNext(head);
        head.setPrevious(obj);
        head = obj;
        changeSize(size + 1);
    }

    private void firstElement(T a) {
        if (size == 0) {
            tail = new Node<>(a);
            head = tail;
            changeSize(size + 1);
        }
    }

    private Boolean isEmpty() {
        return size == 0;
    }


    public void remove(T a) {
        if (a.equals(head.getData())) {
            head = head.getNext();
            changeSize(size - 1);
            return;
        }
        if (a.equals(tail.getData())) {
            tail = tail.getNext();
            changeSize(size - 1);
            return;
        }

        removeElement(a);
    }


    private void removeElement(T a) {
        Node<T> obj = head.getNext();

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
        if (at < 0 || at > size ) {
            return;
        }

        if (at == 0) {
            addAtTail(a);
            return;
        }

        if (at == size) {
            addAtHead(a);
            return;
        }
        insertElement(a, at);

    }

    private void insertElement(T a, int at){
        Node<T> obj = head;
        for (int i = 0; i < at - 1; i++) {
            obj = obj.getNext();
        }

        Node<T> temp = obj.getNext();

        Node<T> aNode = new Node<>(a);
        obj.setNext(aNode);
        aNode.setPrevious(obj);

        aNode.setNext(temp);
        temp.setPrevious(aNode);

        changeSize(size + 1);

    }

    public Node<T> popTail() {
        if (size == 0) {
            return null;
        }

        Node<T> a = tail;
        tail = tail.getPrevious();
        changeSize(size - 1);

        return a;
    }

    public Node<T> popHead() {
        if (size == 0) {
            return null;
        }

        Node<T> a = head;
        head = head.getNext();
        head.setPrevious(null);
        a.setNext(null);
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
        Node<T> obj = head;
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            s.append(obj.getData().toString());
            s.append(" ");
            obj = obj.getNext();
        }

        return s.toString();
    }

    public void addHead(DoublyLinkedList<T> a) {
        changeSize(size + a.size());
        head.setPrevious(a.getTail());
        a.getTail().setNext(head);
        head = a.getHead();
    }

    public void addTail(DoublyLinkedList<T> a) {
        changeSize(size + a.size());
        tail.setNext(a.getHead());
        a.getHead().setPrevious(tail);
        tail = a.getTail();
    }

    public DoublyLinkedList<T> intersection(DoublyLinkedList<T> a) {
        DoublyLinkedList<T> newList = new DoublyLinkedList<>();
        Node<T> obj = head;

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
        Node<T> obj = head;

        for (int i = 0; i < size; i++) {
            if(a.find(obj.getData()) == -1){
                newList.addAtTail(obj.getData());
            }
            obj = obj.getNext();
        }

        return newList;

    }


}
