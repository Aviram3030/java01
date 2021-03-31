package experis.ds;

import java.util.Comparator;

class Student{
    private String name;
    private int id;

    Student(String name, int id){
        this.name = name;
        this.id = id;
    }

    public int getID(){
        return id;
    }

    public String toString(){
        return " name: " + name +
                " id: " + id;
    }

}

/* public class Tests {

    public static void fx1(){
        Comparator<Integer> compare = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };


        KeyExtractor extractor = new KeyExtractorStudent();

        Student a = new Student("Student - 1", 1);
        Student b = new Student("Student - 2", 3);
        Student c = new Student("Student - 3", 4);
        Student d = new Student("Student - 4", 2);


        BinarySearchTree tree = new BinarySearchTree(compare, extractor);
        tree.insert(a);
        tree.insert(b);
        tree.insert(c);
        tree.insert(d);

        System.out.println("Printing the students");

        Func<Student, Integer> func = (Func<Student, Integer>) (a1) -> {System.out.println(a1.toString()) ;return 0;};

        tree.forEach(func, Traversals.INORDER);

        System.out.println();

        System.out.println("Adding more students");

        a = new Student("Student - 5", -2);
        b = new Student("Student - 6", -8);
        c = new Student("Student - 7", 9);
        d = new Student("Student - 8", 0);

        tree.insert(a);
        tree.insert(b);
        tree.insert(c);
        tree.insert(d);

        tree.forEach(func, Traversals.INORDER);
    }
} */
