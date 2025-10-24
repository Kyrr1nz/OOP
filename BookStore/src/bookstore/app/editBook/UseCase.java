package bookstore.app.editBook;

import bookstore.core.Book;
import bookstore.core.InvalidBookException;
import java.util.ArrayList;

public class UseCase {
    private final Gateway gateway;

    public UseCase(Gateway gateway) {
        this.gateway = gateway;
    }

    public void execute(int id, String newTitle, String newAuthor)
            throws BookIdNotFoundException, InvalidBookException {
        var books = gateway.load();
        boolean found = false;

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).id() == id) {
                var updated = new Book(id, newTitle, newAuthor);
                books.set(i, updated);
                found = true;
                break;
            }
        }

        if (!found) {
            throw new BookIdNotFoundException(id);
        }

        gateway.save(books);
    }
}
