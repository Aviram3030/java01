package experis.ds;

public class Node<T>{
    private Node<T> next;
    T data;
    private Node<T> previous;

    public Node(){
    }

    public Node(T data){
        this.data = data;
    }

    public void setNext(Node<T> a){
        next = a;
    }

    public void setPrevious(Node<T> a){
        previous = a;
    }

    public Node<T> getNext(){
        return next;
    }

    public Node<T> getPrevious(){
        return previous;
    }

    public T getData(){
        return data;
    }



}
