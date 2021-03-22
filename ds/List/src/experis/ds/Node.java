package experis.ds;

public class Node<T>{
    private Node next;
    T data;
    private Node previous;

    public Node(){
    }

    public Node(T data){
        this.data = data;
    }

    public void setNext(Node a){
        next = a;
    }

    public void setPrevious(Node a){
        previous = a;
    }

    public Node getNext(){
        return next;
    }

    public Node getPrevious(){
        return previous;
    }

    public T getData(){
        return data;
    }



}
