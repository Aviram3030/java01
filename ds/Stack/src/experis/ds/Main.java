package experis.ds;


public class Main{
    public static void main(String[] args){
        test1();
        //test2();
        //test3();
    }

    public static void test1(){
        IntStackMin x = new IntStackMin();
        x.push(2);
        System.out.println(x.min());

        x.push(1);
        System.out.println(x.min());

        x.pop();
        System.out.println(x.min());

        x.push(3);
        System.out.println(x.min());

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



}