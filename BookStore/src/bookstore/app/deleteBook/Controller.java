package bookstore.app.deleteBook;

import bookstore.app.deleteBook.BookIdNotFoundException;
public class Controller {
    private final UseCase uc;
    public Controller(UseCase uc) {
        this.uc = uc;
    }

    public void execute(String[] args) {
        if (args.length != 1) {
            System.err.println("Args must be: <id>");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("id must be an integer");
            return;
        }

        try {
            uc.execute(id);
            System.out.println("Book with id " + id + " deleted successfully");
        } catch (BookIdNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
