package experis.ds;

class IntStackMin extends IntStack{

    private int[] min;

    public IntStackMin(){
        min = new int[32];
    }

    public IntStackMin(int size){
        super(size);
        min = new int[size];
    }

    public void push(int n){
        int value;

        if(tos != data.length - 1){
            if(tos == 0){
                value = n;
            }
            else{
                value = getSmaller(min[tos - 1], n);
            }

            min[tos] = value;
            updateValues(n);
        }
        else{
            System.out.print("The stack is full");
        }

    }

    public int min(){
        if(isEmpty()){
            System.out.println("There is no min since the stack is empty");
            return -1;
        }

        return min[tos - 1];
    }

    public int getSmaller(int a, int b){
        if(a > b)
            return b;
        return a;
    }
}
