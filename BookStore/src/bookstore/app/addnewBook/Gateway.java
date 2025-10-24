package bookstore.app.addnewBook;
import bookstore.core.Book;
import java.util.ArrayList;
public interface Gateway {
    ArrayList<Book> load();
    void save(ArrayList<Book> books) ;
}
