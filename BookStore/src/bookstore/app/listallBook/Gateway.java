package bookstore.app.listallBook;

import bookstore.core.Book;
import java.util.ArrayList;
public interface Gateway {
    ArrayList<Book> load();
}
