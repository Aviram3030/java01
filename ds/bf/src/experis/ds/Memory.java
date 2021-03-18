package experis.ds;

public class Memory {
    private int[] data;
    private int head;
    private int size;

    public Memory(int size, int head){
        data = new int[size];
        this.head = head;
        this.size = size;
    }

    public void increase(){
        ++data[head];
    }

    public void decrease(){
        --data[head];
    }

    public void moveLeft(){
        --head;
    }

    public void moveRight(){
        ++head;
    }

    public void printChar(){
        System.out.println((char)data[head]);
    }

    public void printValue(){
        System.out.println(data[head]);
    }






}
