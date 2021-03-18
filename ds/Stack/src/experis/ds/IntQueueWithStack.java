package experis.ds;

public class IntQueueWithStack{

    private IntStack stack;

    public IntQueueWithStack(){
        stack = new IntStack();
    }

    public IntQueueWithStack(int size){
        if(size > 0) {
            stack = new IntStack(size);
        }
        else {
            System.exit(1);
        }
    }

    public void enqueue(int n){
        stack.push(n);
    }

    public int dequeue(){
        IntStack temp = new IntStack();
        switchBetweenStacks(stack, temp);
        int value = temp.pop();
        switchBetweenStacks(temp, stack);

        return value;

    }

    public Boolean isFull(){
        return stack.isFull();
    }

    public Boolean isEmpty(){
        return stack.isEmpty();
    }

    public void switchBetweenStacks(IntStack a, IntStack b){
        while(!a.isEmpty()) {
            b.push(a.pop());
        }
    }

}
