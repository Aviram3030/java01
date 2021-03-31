package experis.ds;

import java.util.Comparator;

public class BinarySearchTree<K,T>{

     private static class Node<T>{
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

    private static class Trio<T>{
        private Node<T> node;
        private Direction leftOrRight;
        private Boolean isFound;

        public Trio(Node<T> node, Direction leftOrRight, Boolean isFound){
            this.node = node;
            this.leftOrRight = leftOrRight;
            this.isFound = isFound;
        }

        private Boolean isLeft(){
            return leftOrRight == Direction.LEFT;
        }

    }

     enum Direction {
         LEFT, RIGHT
    }

    private Node<T> root;
    private int size = 0;
    private final Comparator<K> comparator;
    private final KeyExtractor<K,T> keyExtractor;
    private static int count = 0;

    public BinarySearchTree(Comparator<K> comparator, KeyExtractor<K,T> keyExtractor){
        this.comparator = comparator;
        this.keyExtractor = keyExtractor;
    }

    public int size(){
        return size;
    }

    public Boolean isEmpty(){
        return size == 0;
    }

    public Boolean insert(T a){
        Node<T> newNode = new Node<>(a);

        if(isEmpty()){
            root = newNode;
            size = 1;
            return true;
        }

        changeSize(size + 1);

        if(compareKeys(keyExtractor.extract(a),root) == 0){
            System.out.println("The element is already in the tree - at the root");
            return false;
        }

        K needle = keyExtractor.extract(a);
        Trio<T> trio= find_r(needle, root);

        if(trio.isFound){
            System.out.println("The element is already in the tree");
            return false;
        }

        if(trio.isLeft()){
            insertLeft(newNode, trio.node, needle);
            return true;
        }

        insertRight(newNode, trio.node, needle);
        return true;
    }

    private void changeSize(int n){
        size = n;
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

    public Boolean contains(T val){
        K key = keyExtractor.extract(val);

        return find(key) != null;
    }

    public T find(K needle){
        if(isEmpty()){
            return null;
        }

        if(compareKeys(needle, root) == 0) {
            return root.data;
        }

        Trio<T> a = find_r(needle, root);

        if(!a.isFound){
            return null;
        }
        if(a.leftOrRight == Direction.LEFT){
            return a.node.left.data;
        }

        return a.node.right.data;

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

    public void forEach(Func func, Traversals order){
        if(isEmpty()) {
            return;
        }

        switch(order){
            case INORDER:
                forEachInorder(func, root);
                break;
            case PREORDER:
                forEachPreorder(func, root);
                break;
            case POSTORDER:
                forEachPostorder(func, root);
                break;
        }
    }

    private void forEachInorder(Func func, Node<T> a) {
        if(a.left != null){
            forEachInorder(func, a.left);
        }

        func.apply(a);

        if(a.right != null){
            forEachInorder(func, a.right);
        }
    }

    private void forEachPostorder(Func func, Node<T> a) {
        if(a.left != null){
            forEachInorder(func, a.left);
        }

        if(a.right != null){
            forEachInorder(func, a.right);
        }

        func.apply(a);
    }

    private void forEachPreorder(Func func, Node<T> a){
        func.apply(a);

        if(a.left != null){
            forEachPreorder(func, a.left);
        }

        if(a.right != null){
            forEachPreorder(func, a.right);
        }
    }

    public int Height(){
       return Height_r(root);
    }

    private int Height_r(Node<T> a){
        if(a == null){
            return -1;
        }
        int leftDepth = Height_r(a.left);
        int rightDepth = Height_r(a.right);

        if(leftDepth > rightDepth){
            return leftDepth + 1;
        }
        return rightDepth;
    }

    private boolean isPerfect(){
        if(size() == 0){
            return true;
        }
        int height = Height_r(root);
        return calculateSizeForPerfectTree(height) == size() - 1;
    }

    private int calculateSizeForPerfectTree(int height){
        if(height == 0) {
            return 2;
        }

        return calculateSizeForPerfectTree(height - 1) * 2;
    }

    public boolean isComplete(){
        if(size() == 0){
            return true;
        }

        return checkIsComplete(root);
    }

    private boolean checkIsComplete(Node<T> a){
        if(isOnlyOneNull(a.left, a.right) || isOnlyOneNull(a.right, a.left)){
            return false;
        }

        if(areBothNull(a.left, a.right)){
            return true;
        }

        return checkIsComplete(a.left) && checkIsComplete(a.left);
    }

    private boolean isOnlyOneNull(Node<T> a, Node<T> b){
        return a == null && b != null;
    }

    private boolean areBothNull(Node<T> a, Node<T> b){
        return a == null && b == null;
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

    public T nthOrder(int n){
        count = 0;
        return nthOrder_r(root, n).data;
    }

    private Node<T> nthOrder_r(Node<T> a, int n){
        if(a == null || n > size() || n < 0){
            return null;
        }

        Node<T> node = nthOrder_r(a.left, n);

        if(node != null){
            return node;
        }

        count++;
        if(count == n)
            return a;

        node = nthOrder_r(a.right, n);
        if(node != null){
            return node;
        }

        return null;
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
