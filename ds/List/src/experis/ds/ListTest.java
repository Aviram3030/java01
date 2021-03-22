package experis.ds;

public class ListTest {

    public static void fx1(){
        DoublyLinkedList<Integer> a = new DoublyLinkedList<>(4);
        a.addAtHead(5);
        a.addAtHead(2);
        a.addAtTail(1);
        a.insert(5,5);
        System.out.println(a.toString());

        System.out.println(a.get(2));

        a.remove(5);
        System.out.println(a.toString());

        a.insert(5,1);
        System.out.println(a.toString());

        DoublyLinkedList<Integer> b = new DoublyLinkedList<>(3);
        b.addAtHead(7);
        b.addAtHead(8);
        b.addAtTail(9);

        a.addTail(b);
        System.out.println(a.toString());
    }

    public static void fx2(){
        DoublyLinkedList<Integer> a = new DoublyLinkedList<>(8);
        a.addAtHead(5);
        a.addAtHead(2);
        a.addAtTail(1);
        a.insert(5,5);

        System.out.println(a.get(1));

        DoublyLinkedList<Integer> b = new DoublyLinkedList<>(4);
        b.addAtHead(5);
        b.addAtHead(8);
        b.addAtTail(9);

        //System.out.println(a.toString());
        //System.out.println(b.toString());

        System.out.println(a.not(b).toString());

        System.out.println(a.intersection(b).toString());


    }


    public static void fx3(){
        DoublyLinkedList<Integer> a = new DoublyLinkedList<>(4);
        a.addAtHead(5);
        a.addAtHead(2);
        a.addAtTail(1);
        a.insert(5,5);

        DoublyLinkedList<Integer> b = new DoublyLinkedList<>(3);
        b.addAtHead(7);
        b.addAtHead(8);
        b.addAtTail(9);

        System.out.println(a.equalsTo(b));

        a.addHead(b);
        System.out.println(a.toString());
    }

    public static void fx4(){
        DoublyLinkedList<Integer> a = new DoublyLinkedList<>(8);
        a.addAtHead(5);
        a.addAtHead(2);
        a.addAtTail(1);

        System.out.println(a.popHead().getData().toString());
        System.out.println(a.popTail().getData().toString());

    }
}