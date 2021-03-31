package experis.ds;

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
        tree = new BinarySearchTree<Integer,Student>(compare, key);

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
        assertFalse(tree.contains(students[0]));
        for(int i = 0; i < students.length; i++){
            tree.insert(students[i]);
        }

        for(int i = 0; i < students.length; i++){
            assertTrue(tree.contains(students[i]));
        }
    }

    @Test
    void find() {
    }

    @Test
    void forEach() {
    }

    @Test
    void height() {
    }

    @Test
    void isComplete() {
    }

    @Test
    void max() {
    }

    @Test
    void nthOrder() {
    }

    @Test
    void min() {
    }
}