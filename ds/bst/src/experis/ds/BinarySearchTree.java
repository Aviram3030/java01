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
        private final Node<T> node;
        private final Direction leftOrRight;
        private final Boolean isFound;

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
    private int count;

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

        size++;

        if(compareNodeToKey(keyExtractor.extract(a), root) == 0){
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
            insertLeft(newNode, trio.node);
            return true;
        }

        insertRight(newNode, trio.node);
        return true;
    }

    private void insertLeft(Node<T> newNode, Node<T> node){
        if(node.left == null){
            node.left = newNode;
            return;
        }

        if(compareNodeToNode(newNode,node.left) > 0) {
            newNode.left = node.left;
            node.left = newNode;
        }
        else{
            node.left.left = newNode;
        }
    }

    private void insertRight(Node<T> newNode, Node<T> node){
        if(node.right == null){
            node.right = newNode;
            return;
        }
        if(compareNodeToNode(newNode,node.right) < 0) {
            newNode.right = node.right;
            node.right = newNode;
        }
        else{
            node.right.right = newNode;
        }
    }

    private int compareNodeToNode(Node<T> newNode, Node<T> node){
        return comparator.compare(keyExtractor.extract(newNode.data),keyExtractor.extract(node.right.data));
    }

    public Boolean contains(T val){
        K key = keyExtractor.extract(val);

        return find(key) != null;
    }

    public T find(K needle){
        if(isEmpty()){
            return null;
        }

        if(compareNodeToKey(needle, root) == 0) {
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

        if(compareNodeToKey(needle, currentNode) < 0) {
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

        int compare = compareNodeToKey(needle,currentNode.left);
        if (compare == 0) {
            return new Trio<>(currentNode, Direction.LEFT,true);
        }
        else {
            return find_r(needle, currentNode.left);
        }
    }

    private Trio<T> checkRight(K needle, Node<T> currentNode){
        if(currentNode.right == null){
            return new Trio<>(currentNode, Direction.RIGHT, false);
        }

        int compare = compareNodeToKey(needle,currentNode.right);
        if(compare == 0){
            return new Trio<>(currentNode, Direction.RIGHT,true);
        }
        else {
            return find_r(needle, currentNode.right);
        }
    }

    private int compareNodeToKey(K needle, Node<T> currentNode) {
        T val = currentNode.data;
        K key = keyExtractor.extract(val);
        return comparator.compare(needle, key);
    }

    public void forEach(Func<T,K> func, Traversals order){
        if(isEmpty()) {
            return;
        }

        switch (order) {
            case INORDER -> forEachInorder(func, root);
            case PREORDER -> forEachPreorder(func, root);
            case POSTORDER -> forEachPostorder(func, root);
        }
    }

    private void forEachInorder(Func<T,K> func, Node<T> a) {
        if(a.left != null){
            forEachInorder(func, a.left);
        }

        func.apply(a.data);

        if(a.right != null){
            forEachInorder(func, a.right);
        }
    }

    private void forEachPostorder(Func<T,K> func, Node<T> a) {
        if(a.left != null){
            forEachInorder(func, a.left);
        }

        if(a.right != null){
            forEachInorder(func, a.right);
        }

        func.apply(a.data);
    }

    private void forEachPreorder(Func<T,K> func, Node<T> a){
        func.apply(a.data);

        if(a.left != null){
            forEachPreorder(func, a.left);
        }

        if(a.right != null){
            forEachPreorder(func, a.right);
        }
    }

    public int height(){
       return height_r(root);
    }

    private int height_r(Node<T> a){
        if(a == null){
            return -1;
        }
        int leftDepth = height_r(a.left);
        int rightDepth = height_r(a.right);

        if(leftDepth > rightDepth){
            return leftDepth + 1;
        }
        return rightDepth + 1;
    }

    public boolean isPerfect(){
        if(size() == 0){
            return true;
        }

        int height = height_r(root);
        return calculateSizeForPerfectTree(height) == size() + 1;
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
        if(isOnlyOneChildNull(a.left, a.right) || isOnlyOneChildNull(a.right, a.left)){
            return false;
        }

        if(areBothNull(a.left, a.right)){
            return true;
        }

        return checkIsComplete(a.left) && checkIsComplete(a.left);
    }

    private boolean isOnlyOneChildNull(Node<T> a, Node<T> b){
        return a == null && b != null;
    }

    private boolean areBothNull(Node<T> a, Node<T> b){
        return a == null && b == null;
    }

    public K reduce(BiFunc<K,T,K> func){
        if(size == 0){
            return null;
        }
        K keyManipulation = keyExtractor.extract(min());
        for(int i = 0 ; i < size() - 1; i++){
            keyManipulation = func.apply(this.nthOrder(i), keyManipulation);
        }

        return keyManipulation;
    }

    public T nthOrder(int n){
        count = size() - 1;
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

        if(count == n)
            return a;

        count--;

        node = nthOrder_r(a.right, n);
        return node;
    }

    public T max(){
        if(isEmpty()){
            return null;
        }

        Func<Node<T>,Node<T>> f = (a -> a.right);
        return getMinMax(f).data;

    }

    public T min(){
        if(isEmpty()){
            return null;
        }

        Func<Node<T>,Node<T>> f = (a -> a.left);
        return getMinMax(f).data;
    }

    private Node<T> getMinMax(Func<Node<T>,Node<T>> func){
        Node<T> child = root;
        while(func.apply(child) != null){
            child = func.apply(child);
        }

        return child;
    }

}
