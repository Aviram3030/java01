package experis.ds.tests;

import experis.ds.persons.Author;
import experis.ds.persons.AuthorsNameList;

import static org.junit.jupiter.api.Assertions.*;

class AuthorsNameListTest {

    @org.junit.jupiter.api.Test
    void add() {
        AuthorsNameList authors = new AuthorsNameList();
        Author a = authors.add("Author a");
        Author b = authors.add("Author a");
        assertTrue(a == b);
        b = authors.add("Author b");
        assertFalse(a == b);
    }
}