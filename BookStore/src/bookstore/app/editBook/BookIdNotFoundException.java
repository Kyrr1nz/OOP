package bookstore.app.editBook;

public class BookIdNotFoundException extends Exception {
    private final int bookId;

    public BookIdNotFoundException(int bookId) {
        super("Book with id " + bookId + " not found");
        this.bookId = bookId;
    }

    public int bookId() {
        return bookId;
    }
}
