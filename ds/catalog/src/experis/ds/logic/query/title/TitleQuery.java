package experis.ds.logic.query.title;

import experis.ds.database.details.Book;

import java.util.ArrayList;

public class TitleQuery {
    private final ArrayList<Command> commands = new ArrayList<>();

    public void load(String[] queryTitles){
        for(String title: queryTitles){
            if(!title.equals("")) {
                commands.add(getCommand(title));
            }
        }
    }

    public ArrayList<Book> getBooksByTitle(ArrayList<Book> books) {
        ArrayList<Book> booksList = new ArrayList<>();

        for(Book book: books){
            String title = book.getTitle();
            if(this.search(title)){
                booksList.add(book);
            }
        }
        return booksList;
    }

    private Boolean search(String bookTitle){
        for(Command command : commands){
            if(!checkIfContains(command, bookTitle)){
                return false;
            }
        }
        return true;
    }

    private Command getCommand(String title){
        if(title.charAt(0) == '-'){
            return new Command(title.substring(1), CommandType.NOT_PRESENT);
        }
        return new Command(title,CommandType.PRESENT);
    }

    private Boolean checkIfContains(Command command, String bookTitle){
        String title = command.getTitle().toLowerCase();
        CommandType type = command.getType();
        bookTitle = bookTitle.toLowerCase();

        if(type == CommandType.NOT_PRESENT && bookTitle.contains(title)) {
            return false;
        }
        return type != CommandType.PRESENT || bookTitle.contains(title);
    }

    public void clear(){
        commands.clear();
    }
}

