package experis.ds;

public class Memory {
    private int size = 1024;
    private int[] data;
    private int head;


    public Memory(){
        data = new int[size];
        head = size / 2;
    }

    public int read(){
        return data[head];
    }

    public void write(int n){
        data[head] = n;
    }


    public void moveLeft(){
        if(head - 1 < 0){
            createNewArrayFromLeft();
        }

        --head;


    }

    public void moveRight(){
        if(head + 1 >= size){
            createNewArrayFromRight();
        }
        else{
            ++head;
        }

    }

    public int getValue(){
        return data[head];
    }

    protected void createNewArrayFromLeft(){
        updateSizeAndHead();

        int[] newData = new int[size];
        fillTheArray(newData, head , newData.length);

        this.data = newData;
    }

    protected void createNewArrayFromRight(){
        updateSizeAndHead();

        int[] newData = new int[size];
        fillTheArray(newData, 0, head);

        this.data = newData;
    }

    protected void updateSizeAndHead(){
        size = size * 2;
        head = size / 2;
    }

    protected void fillTheArray(int[] newData, int start, int end){
        for(int i = start, j = 0 ;i < end; i++, j++){
            newData[i] = this.data[j];
        }
    }






}
