package bookstore.app.deleteBook;

import bookstore.core.Book;
import java.util.ArrayList;

public class UseCase {
    private final Gateway gateway;

    public UseCase(Gateway gateway) {
        this.gateway = gateway;
    }

    public void execute(int id) throws BookIdNotFoundException {
        ArrayList<Book> books = gateway.load();
        boolean removed = books.removeIf(b -> b.id() == id);

        if (!removed) {
            throw new BookIdNotFoundException(id);
        }

        gateway.save(books);
    }
}
