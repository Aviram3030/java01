package experis.ds;

class IntStack {
    protected int[] data;
    protected int tos;
    protected int capacity;

    public IntStack(){
        data = new int[32];
        tos = 0;
        capacity = 32;
    }

    public IntStack(int size){
        if(size > 0) {
            data = new int[size];
            tos = 0;
            capacity = size;
        }
        else {
            System.exit(1);
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
        if(!isFull()){
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

    public Boolean isFull(){
        return tos == data.length;
    }

    public String toString(){
        return "stack " + capacity + ":[" + getValues() + "]";
    }

    public String getValues(){
        if(tos == 0){
            return null;
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < tos - 1; i++){
            sb.append(data[i] + ",");
        }

        sb.append(data[tos - 1]);
        return sb.toString();
    }
}