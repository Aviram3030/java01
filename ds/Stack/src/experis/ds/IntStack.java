package experis.ds;

class IntStack {
    protected int[] data;
    protected int tos;

    public IntStack(){
        data = new int[32];
        tos = 0;
    }

    public IntStack(int size){
        if(size > 0) {
            data = new int[size];
            tos = 0;
        }
        System.exit(1);
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
            updateValues(n);
        }
        else{
            System.out.print("The stack is full");
        }
    }

    public void updateValues(int n){
        data[tos] = n;
        ++tos;
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
        tos = 0;
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