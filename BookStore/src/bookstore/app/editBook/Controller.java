package bookstore.app.editBook;

import  bookstore.core.InvalidBookException;
import  bookstore.app.editBook.BookIdNotFoundException;
import java.util.*;
public class Controller {
    private final UseCase uc;
    public Controller(UseCase uc) { this.uc = uc; }
    public void execute(String[] args){
        if(args.length!=3){
            System.err.println("Args must be : <id> <title> <author>");
            return;
        }       int id;
        try {
            id = Integer.parseInt(args[0]);
        }catch(NumberFormatException e){
            System.err.println("id must be an integer");
            return;
        }
        var title = args[1];
        var author = args[2];
        try {
            this.uc.execute(id, title, author);
        }catch (BookIdNotFoundException e){
            System.err.println("Book with id "+ e.bookId() + " already existed");
            return;
        }catch (InvalidBookException e){
            System.err.println("Invalid book" + e.getMessage());
            return;
        }
        System.out.println("Book was added successfully");

    }
}
