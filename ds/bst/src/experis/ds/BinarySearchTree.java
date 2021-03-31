package experis.ds;

import java.util.Comparator;
import java.util.function.Function;

public class BinarySearchTree<T,K>{

     static class Node<T>{
        private Node<T> right;
        T data;
        private Node<T> left;

        public Node(T data){
            this.data = data;
        }

        public String toString(){
            return data.toString();
        }

    }

    static class Trio<T>{
        private Node<T> node;
        private Direction leftOrRight;
        private Boolean isFound;

        public Trio(Node<T> node, Direction leftOrRight, Boolean isFound){
            this.node = node;
            this.leftOrRight = leftOrRight;
            this.isFound = isFound;
        }

    }

     enum Direction {
         LEFT, RIGHT
    }

    private Node<T> root;
    private int size = 0;
    private final Comparator<K> comparator;
    private final KeyExtractor<T,K> keyExtractor;

    public BinarySearchTree(Comparator<K> comparator, KeyExtractor<T,K> keyExtractor){
        this.comparator = comparator;
        this.keyExtractor = keyExtractor;
    }

    public int size(){
        return size;
    }

    public Boolean isEmpty(){
        return size == 0;
    }

    public void insert(T a){
        Node<T> newNode = new Node<>(a);

        if(isEmpty()){
            root = newNode;
            size = 1;
            return;
        }

        changeSize(size + 1);

        if(compareKeys(keyExtractor.extract(a),(root)) == 0){
            System.out.println("The element is already in the tree - at the root");
            return;
        }

        K needle = keyExtractor.extract(a);
        Trio<T> trio= find_r(needle, root);

        if(trio.isFound){
            System.out.println("The element is already in the tree");
            return;
        }

        if(isLeft(trio)){
            insertLeft(newNode, trio.node, needle);
            return;
        }

        insertRight(newNode, trio.node, needle);
    }

    private void changeSize(int n){
        size = n;
    }

    private Boolean isLeft(Trio<T> trio){
        return trio.leftOrRight == Direction.LEFT;
    }

    private void insertLeft(Node<T> newNode, Node<T> node, K needle){
        if(node.left == null){
            node.left = newNode;
            return;
        }

        newNode.left = node.left;
        node.left = newNode;
    }

    private void insertRight(Node<T> newNode, Node<T> node, K needle){
        if(node.right == null){
            node.right = newNode;
            return;
        }

        newNode.right = node.right;
        node.right = newNode;
    }

    public Boolean contains(Node<T> a){
        T val = a.data;
        var key = keyExtractor.extract(val);

        return find(key) != null;
    }

    public Node<T> find(K needle){
        if(isEmpty()){
            return null;
        }

        if(compareKeys(needle, root) != 0) {
            Trio<T> a = find_r(needle, root);

            if(!a.isFound){
                return null;
            }
            if(a.leftOrRight == Direction.LEFT){
                return a.node.left;
            }
            else{
                return a.node.right;
            }
        }

        return root;
    }

    private Trio<T> find_r(K needle, Node<T> currentNode) {
        Trio<T> pair;

        if(compareKeys(needle, currentNode) < 0) {
             pair = checkLeft(needle, currentNode);
        }
        else {
             pair = checkRight(needle, currentNode);
        }

        return pair;
    }

    private Trio<T> checkLeft(K needle, Node<T> currentNode){
        if(currentNode.left == null) {
            return new Trio<>(currentNode, Direction.LEFT, false);
        }

        int compare = compareKeys(needle,currentNode.left);
        if (compare == 0) {
            return new Trio<>(currentNode, Direction.LEFT,true);
        }
        if (compare < 0) {
            return find_r(needle, currentNode.left);
        }

        return new Trio<>(currentNode.left, Direction.RIGHT,false);
    }

    private Trio<T> checkRight(K needle, Node<T> currentNode){
        if(currentNode.right == null){
            return new Trio<>(currentNode, Direction.RIGHT, false);
        }

        int compare = compareKeys(needle,currentNode.right);
        if(compare == 0){
            return new Trio<>(currentNode, Direction.RIGHT,true);
        }
        if(compare > 0){
            return find_r(needle,currentNode.right);
        }

        return new Trio<>(currentNode.right, Direction.LEFT,false);
    }

    private int compareKeys(K needle, Node<T> currentNode) {
        T val = currentNode.data;
        var key = keyExtractor.extract(val);
        return comparator.compare(needle, key);
    }

    public void forEach(Function func){
        if(isEmpty()) {
            return;
        }

        forEach_r(func, root);
    }

    private void forEach_r(Function func, Node<T> a){
        func.apply(a);

        if(a.left != null){
            forEach_r(func, a.left);
        }

        if(a.right != null){
            forEach_r(func, a.right);
        }
    }

    public T max(){
        if(isEmpty()){
            return null;
        }

        Node<T> a = root;
        while(a.right != null){
            a = a.right;
        }

        return a.data;
    }

    public T min(){
        if(isEmpty()){
            return null;
        }

        Node<T> a = root;
        while(a.left != null){
            a = a.left;
        }

        return a.data;
    }


}
