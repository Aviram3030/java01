package experis.ds.database.creators;

import java.util.HashMap;

public class AuthorsNameList {
    private HashMap<String, Author> authors = new HashMap<>();

     public Author add(String authorName) {
        Author author = authors.get(authorName);

        if(author == null){
            author = new Author(authorName);
            authors.put(authorName, author);
        }

        return author;
    }

    public Author get(String authorName){
         return authors.get(authorName);
    }

}
