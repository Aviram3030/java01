package experis.ds;

import experis.ds.exception.IllegalSizeException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeUnitTest {
    BinarySearchTree<Integer,Student> tree;
    KeyExtractor<Integer,Student> key;
    Student[] students;

    @BeforeEach
    void setUp() {
        students = new Student[]{
                new Student("Student - 1", 5),
                new Student("Student - 2", -3),
                new Student("Student - 3", 4),
                new Student("Student - 4", 10),
                new Student("Student - 5", -5),
                new Student("Student - 6", -4),
                new Student("Student - 7", 8),
                new Student("Student - 8", 7),
                new Student("Student - 9", 1),
                new Student("Student - 10", 9),
        };

        Comparator<Integer> compare = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };

        key = new KeyExtractorStudent();
        try {
            tree = new BinarySearchTree<Integer, Student>(compare, key);
        }
        catch(IllegalArgumentException e){
            e.printStackTrace();
        }

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void size() {
        assertTrue(tree.size() == 0);
        tree.insert(students[0]);
        assertTrue(tree.size() == 1);
    }

    @Test
    void isEmpty() {
        assertTrue(tree.isEmpty());
        tree.insert(students[0]);
        assertFalse(tree.isEmpty());
    }

    @Test
    void insert() {
        assertTrue(tree.insert(students[0]));
        assertFalse(tree.insert(students[0]));

        for(int i = 1;i < students.length; i++){
            assertTrue(tree.insert(students[i]));
        }
    }

    @Test
    void contains() {
        assertFalse(tree.contains(students[1]));
        for(int i = 0; i < students.length; i++){
            tree.insert(students[i]);
        }

        for(int i = 0; i < students.length; i++){
            assertTrue(tree.contains(students[i]));
        }
    }

    @Test
    void find() {
        assertTrue(tree.find(students[0].getID()) == null);
        for(int i = 0; i < students.length; i++){
            tree.insert(students[i]);
        }

        for(int i = 0; i < students.length; i++){
            assertTrue(tree.find(students[i].getID()) == students[i]);
        }

    }

    @Test
    void forEach() {
    }

    @Test
    void height() {
        assertTrue(tree.height() == -1);
        insertTest();

        assertTrue(tree.height() == 3);
    }

    @Test
    void isComplete() {
        assertTrue(tree.isComplete());
        tree.insert(students[0]);
        tree.insert(students[1]);
        tree.insert(students[3]);

        assertTrue(tree.isComplete());
    }

    @Test
    void isPerfect() {
        assertTrue(tree.isPerfect());
        tree.insert(students[0]);
        tree.insert(students[1]);
        tree.insert(students[3]);

        assertTrue(tree.isPerfect());
    }

    @Test
    void max() {
        insertTest();
        assertEquals(tree.max(), students[3]);
    }

    @Test
    void nthOrder() {
        insertTest();

        try {
            assertTrue(tree.nthOrder(0).getID() == 10);
            assertTrue(tree.nthOrder(2).getID() == 8);
            assertTrue(tree.nthOrder(5).getID() == 4);
            assertTrue(tree.nthOrder(9).getID() == -5);
        }
        catch(IllegalSizeException e){
            e.printStackTrace();
        }
    }

    @Test
    void min() {
        insertTest();
        assertEquals(tree.min(), students[4]);
    }

    @Test
    void reduce(){
        insertTest();
        int sum = 0;
        for(int i = 0; i < students.length; i++){
            sum += students[i].getID();
        }

        BiFunc<Integer,Student,Integer> biFunc = new BiFunc<Integer, Student, Integer>() {
            @Override
            public Integer apply(Student student, Integer t) {
                return student.getID() + t;
            }
        };

        assertTrue(tree.reduce(biFunc) == sum);
    }

    void remove(){
        insertTest();

        assertTrue(tree.remove(students[0]) == students[0].getID());
        assertFalse(tree.contains(students[0]));
        assertTrue(tree.size() == 9);

    }

    public void insertTest(){
        for(int i = 0; i < students.length; i++){
            tree.insert(students[i]);
        }
    }
}