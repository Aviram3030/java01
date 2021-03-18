package experis.ds;


public class Main{
    public static void main(String[] args){
        //test1();
        //test2();
        //test3();
        //test4();
        test5();
    }

    public static void test1(){
        IntStackMin x = new IntStackMin();
        x.push(2);
        System.out.println(x.min());

        x.push(1);
        System.out.println(x.min());

        System.out.println(x.toString());
        x.pop();
        System.out.println(x.min());
        System.out.println(x.toString());

        x.push(3);
        System.out.println(x.min());
        System.out.println(x.toString());

        x.pop();
        System.out.println(x.min());

        x.push(4);
        x.clear();
        System.out.println(x.min());

    }

    public static void test2() {
        IntStackMin x = new IntStackMin();
        x.push(3);
        x.push(4);
        x.printElements();

        x.clear();

        x.printElements();

    }

    public static void test3(){
        IntStackMin x = new IntStackMin(-1);
        System.out.println(x.size());
    }

    public static void test4(){
        IntQueueWithStack x = new IntQueueWithStack(3);
        x.enqueue(4);
        x.enqueue(2);
        x.enqueue(1);

        System.out.println(x.dequeue());
        System.out.println(x.dequeue());
        System.out.println(x.dequeue());

        System.out.println("HELLO");

    }

    public static void test5(){
        IntQueue x = new IntQueue(3);
        x.enqueue(4);
        x.enqueue(2);
        x.enqueue(1);

        System.out.println(x.toString());

        System.out.println(x.dequeue());
        System.out.println(x.dequeue());
        System.out.println(x.dequeue());

        x.enqueue(2);
        x.enqueue(1);

        System.out.println(x.toString());

        System.out.println(x.dequeue());
        System.out.println(x.dequeue());


    }

}