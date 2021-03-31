package experis.ds;

import java.util.Comparator;

class Student{
    public String name;
    public int id;

    Student(String name, int id){
        this.name = name;
        this.id = id;
    }

    public String toString(){
        return " name: " + name +
                " id: " + id;
    }
}

public class Tests {

    public static void fx1(){
        Comparator<Integer> compare = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };


        KeyExtractor extractor = new KeyExtractor<Student,Integer>();

        Student a = new Student("Student - 1", 1);
        Student b = new Student("Student - 2", 3);
        Student c = new Student("Student - 3", 4);
        Student d = new Student("Student - 4", 2);

        extractor.add(a,1);
        extractor.add(b,3);
        extractor.add(c,4);
        extractor.add(d,2);

        BinarySearchTree tree = new BinarySearchTree(compare, extractor);
        tree.insert(a);
        tree.insert(b);
        tree.insert(c);
        tree.insert(d);

        System.out.println("Printing the students");

        tree.forEach( (n) -> { System.out.println(n.toString()); return n; } );

        System.out.println();

        System.out.println("Adding more students");

        a = new Student("Student - 5", -2);
        b = new Student("Student - 6", -8);
        c = new Student("Student - 7", 9);
        d = new Student("Student - 8", 0);

        extractor.add(a,-2);
        extractor.add(b,-8);
        extractor.add(c,9);
        extractor.add(d,0);

        tree.insert(a);
        tree.insert(b);
        tree.insert(c);
        tree.insert(d);

        tree.forEach( (n) -> { System.out.println(n.toString()); return n; } );
    }
}
