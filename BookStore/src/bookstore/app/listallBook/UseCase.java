package bookstore.app.listallBook;
import bookstore.core.Book;
import java.util.ArrayList;
public class UseCase {
    private final Gateway gateway;
    public UseCase(Gateway gateway) {
        this.gateway = gateway;
    }
    public ArrayList<Book> execute() {return  this.gateway.load();}
}
