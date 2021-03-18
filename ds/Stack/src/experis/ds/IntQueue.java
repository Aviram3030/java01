package experis.ds;

public class IntQueue {

    private int[] data;
    private int tail = 0;
    private int head = 0;
    private int size = 0;
    private int capacity;

    public IntQueue(){
        data = new int[32];
        capacity = 32;
    }

    public IntQueue(int len){
        if(len >= 0) {
            data = new int[len];
            capacity = len;
        }
    }

    public void enqueue(int n){
        if(!isFull()) {
            data[tail] = n;
            ++tail;
            tail = tail % capacity;
            ++size;
        }
        else{
            System.out.print("The queue is full");
        }
    }

    public int dequeue(){
        int value;

        if(!isEmpty()){
            value = data[head];
            ++head;
            head = head % capacity;
            --size;

            return value;
        }

        return -1;
    }

    public int peek(){
        if(tail > 0) {
            return data[tail - 1];
        }

        return -1;
    }

    public int getSize(){
        return size;
    }

    public int getCapacity(){
        return capacity;
    }

    public Boolean isFull(){
        return size == capacity;
    }

    public Boolean isEmpty(){
        return size == 0;
    }

    public String toString(){
        return "stack " + capacity + ":[" + getValues() + "]";
    }

    public String getValues(){
        StringBuilder sb = new StringBuilder();

        if(tail > head){
            sb.append(getData(head , tail));
            sb.append(data[tail - 1]);

            return sb.toString();
        }

        if(tail < head){
            sb.append(getData(head , capacity + 1));
            sb.append(getData(0 , tail));
            sb.append(data[tail - 1]);

            return sb.toString();
        }

        if(capacity == size){
            sb.append(getData(0 , capacity));
            sb.append(data[capacity - 1]);
            return sb.toString();
        }

        return null;
    }

    public String getData(int start, int end){
        StringBuilder sb = new StringBuilder();

        for(int i = start; i < end - 1; i++){
            sb.append(data[i] + ",");
        }

        return sb.toString();

    }

}
