package experis.ds;


class IntStack {
    private int[] data;
    private int tos;

    public IntStack(){
        data = new int[32];
        tos = 0;
    }

    public IntStack(int size){
        if(size > 0) {
            data = new int[size];
            tos = 0;
        }
    }

    // Essentials
    public int size() {
        return tos;
    }

    public boolean isEmpty() {
        return tos == 0;
    }

    public void push(int n){
        if(tos != data.length - 1){
            data[tos] = n;
            ++tos;
        }
        else{
            System.out.print("The stack is full");
        }
    }

    public int pop(){
        if(!this.isEmpty()){
          --tos;
          return data[tos];
        }

        return -1;
    }

    public int peek(){
        if (tos > 1) {
            return data[tos - 1];
        }
        System.out.println("the Stack is Empty");
        return 0;
    }

    public void clear(){
        while(tos > 0){
            this.pop();
        }
    }
    public void push(int... numbers){
        for(int i = 0;i < numbers.length; i++){
            this.push(numbers[i]);
        }
    }

    public void printElements(){
        for(int i = tos - 1; i >= 0; i--){
            System.out.print(data[i]+ "\t");
        }
        System.out.println();
    }
}

public class Main{
    public static void main(String[] args){
        //test1();
        //test2();
        test3();
    }

    public static void test1(){
        IntStack x = new IntStack();
        x.push(1);
        x.push(2);
        x.printElements();

        x.pop();
        x.printElements();

        x.push(3,4);
        x.printElements();

        x.pop();
        x.pop();
        x.peek();
        System.out.println("Pop: " + x.pop());

        x.pop();
        x.printElements();


    }

    public static void test2() {
        IntStack x = new IntStack();
        x.push(3);
        x.push(4);
        x.printElements();

        x.clear();

        x.printElements();

    }

    public static void test3(){
        IntStack x = new IntStack(-1);
        System.out.println(x.size());
    }



}