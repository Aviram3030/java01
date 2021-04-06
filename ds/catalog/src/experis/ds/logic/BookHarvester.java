package experis.ds.logic;

import experis.ds.database.books.BookDetails;
import experis.ds.userinterface.input.Input;


public class BookHarvester {

    private Input input;

    public BookHarvester(Input input){
        this.input = input;
    }

    public BookDetails nextBook(String separate) {
        try {
            String[] details = input.line().split(separate);
            return new BookDetails(details);
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean hasNext(){
        return input.hasNext();
    }

}
