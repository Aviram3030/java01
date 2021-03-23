package experis.ds;


public class Memory implements IMemory{
    private static final int size = 2;
    private int head;
    private ArrayNode data;


    public Memory(){
        int[] newData = new int[size];
        data = new ArrayNode(newData);
        head = size / 2;
    }

    public int read(){
        int[] v = data.getArray();
        return v[head];
    }

    public void write(int n){
        data.setValue(n, head);
    }

    public void moveLeft(){
        if(head == 0){
            if(data.getPrevious() == null) {
                createNewArrayFromLeft();
            }
            else{
                data = data.getPrevious();
            }

            head = size - 1;
        }
        else{
            --head;
        }
    }

    public void moveRight(){
        if(head == size - 1){
            if(data.getNext() == null) {
                createNewArrayFromRight();
            }
            else{
                data = data.getNext();
            }

            head = 0;
        }
        else{
            ++head;
        }

    }

    private void createNewArrayFromLeft(){
        ArrayNode v = createNewArrayNode();

        data.setPrevious(v);
        data = data.getPrevious();

    }

    private void createNewArrayFromRight(){
        ArrayNode v = createNewArrayNode();

        data.setNext(v);
        data = data.getNext();
    }

    private ArrayNode createNewArrayNode(){
        int[] newData = new int[size];
        return new ArrayNode(newData);
    }


    public int getCurrentValue(){
        return data.getArray()[head];
    }

}
