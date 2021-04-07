package experis.ds.database.books;

public class BookDetails {
    private String isbn;
    private final String title;
    private final String author;
    private final String year;
    private final String publisher;

    public BookDetails(String[] details) throws Exception {
        setISBN(details[0]);
        this.title = details[1];
        this.author = details[2];
        this.year = details[3];
        this.publisher = details[4];
    }


    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getYear() {
        return year;
    }

    public String getPublisher() {
        return publisher;
    }

    private void setISBN(String text) throws Exception {
        if (text.length() != 9 && text.length() != 10 && text.length() != 13) {
            throw new Exception();
        }
        if (text.length() == 9) {
            StringBuilder sb = new StringBuilder();
            sb.append("0");
            sb.append(text);
            text = sb.toString();
        }

        this.isbn = text;
    }


}
