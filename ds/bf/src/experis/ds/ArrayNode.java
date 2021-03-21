package experis.ds;

public class ArrayNode {
    private final int size = 10;
    private final int[] elements;
    private ArrayNode next;
    private ArrayNode previous;

    public ArrayNode(){
        elements = new int[size];
    }

    public ArrayNode(int[] data){
        this.elements = data;
    }

    public int[] getArray(){
        return elements;
    }

    public void setValue(int n, int index){
        elements[index] = n;
    }

    public void setNext(ArrayNode v){
        this.next = v;
        v.previous = this;
    }

    public void setPrevious(ArrayNode v){
        this.previous = v;
        v.next = this;
    }

    public ArrayNode getNext(){
        return next;
    }

    public ArrayNode getPrevious(){
        return previous;
    }
}
