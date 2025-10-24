package bookstore.app.addnewBook;

import bookstore.core.InvalidBookException;

public class BookIdExistedException extends Exception {


    private final int bookId;
    public BookIdExistedException(int bookId) {
        this.bookId = bookId;
    }
    public int bookId()
    {
        return bookId;
    }
}
