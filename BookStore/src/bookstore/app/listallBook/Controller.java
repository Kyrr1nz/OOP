package bookstore.app.listallBook;

public class Controller {
    private final UseCase uc;

    public Controller(UseCase uc) {
        this.uc = uc;
    }
    public void execute() {
        var books = this.uc.execute();
        System.out.println("Number of books in the list: " + books.size());
        for(var book : books) {
            System.out.printf("[%d] %s (%s)",book.id(),book.title(),book.author());
            System.out.println();
        }
    }
}
