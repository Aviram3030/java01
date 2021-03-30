package experis.ds;

import java.util.Comparator;
import java.util.function.Function;

public class BinarySearchTree<T>{
    private Node<T> root;
    private int size = 0;
    private final Comparator<T> comparator;

    static class Node<T>{
        private Node<T> right;
        T data;
        private Node<T> left;

        public Node(){
        }

        public Node(T data){
            this.data = data;
        }

        public void setRight(Node<T> a){
            right = a;
        }

        public void setLeft(Node<T> a){
            left = a;
        }

        public Node<T> getRight(){
            return right;
        }

        public Node<T> getLeft(){
            return left;
        }

        public T getData(){
            return data;
        }

    }

    public BinarySearchTree(Comparator<T> comparator){
        this.comparator = comparator;
    }

    public int size(){
        return size;
    }

    public Boolean isEmpty(){
        return size == 0;
    }

    public void insert(Node<T> a){
        if(isEmpty()){
            root = a;
            size = 1;
            return;
        }

        insertNode(a, root);
        ++size;
    }

    private void insertNode(Node<T> a, Node<T> currentNode){
        if(comparator.compare(a.getData(), currentNode.getData()) > 0){
            if(currentNode.getLeft() != null) {
                insertNode(a, currentNode.getLeft());
            }
            else{
                currentNode.setLeft(a);
            }
        }
        else{
            if(currentNode.getRight() != null) {
                insertNode(a, currentNode.getRight());
            }
            else{
                currentNode.setRight(a);
            }
        }
    }

    public Boolean contains(Node<T> a){
        return find(a.getData()) != null;
    }

    public Node<T> find(T data){
        if(isEmpty()){
            return null;
        }

        return findOnTree(data, root);
    }

    private Node<T> findOnTree(T data, Node<T> currentNode){
        int compare = comparator.compare(data, currentNode.getData());

        if(compare == 0) {
            return currentNode;
        }
        else if(compare < 0){
            if(currentNode.getLeft() != null){
                return findOnTree(data, currentNode.getLeft());
            }
        }
        else{
            if(currentNode.getRight() != null){
                return findOnTree(data, currentNode.getRight());
            }
        }

        return null;
    }

    public void forEach(Function func){
        if(isEmpty()) {
            return;
        }
        forEachOnTree(func, root);
    }

    private void forEachOnTree(Function func, Node<T> a){
        func.apply(a);

        if(a.getLeft() != null){
            forEachOnTree(func, a.getLeft());
        }

        if(a.getRight() != null){
            forEachOnTree(func, a.getRight());
        }
    }

    public T max(){
        if(isEmpty()){
            return null;
        }

        Node<T> a = root;
        while(a.getRight() != null){
            a = a.getRight();
        }

        return a.getData();
    }

    public T min(){
        if(isEmpty()){
            return null;
        }

        Node<T> a = root;
        while(a.getLeft() != null){
            a = a.getLeft();
        }

        return a.getData();
    }


}
