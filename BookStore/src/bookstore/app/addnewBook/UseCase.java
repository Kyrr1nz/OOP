package bookstore.app.addnewBook;

import bookstore.core.Book;
import bookstore.core.InvalidBookException;

public class UseCase {
    private final Gateway gateway;

    public UseCase(Gateway gateway){
    this.gateway = gateway;
    }
    public void execute(int id, String title, String author) throws InvalidBookException, BookIdExistedException {
        var books = this.gateway.load();
        for (var book : books) {
            if (book.id() == id) {
                throw new BookIdExistedException(id);

            }
        }
        var book = new Book(id, title, author);
        books.add(book);
        this.gateway.save(books);
    }
}
