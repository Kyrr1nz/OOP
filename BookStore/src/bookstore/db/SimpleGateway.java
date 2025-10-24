package bookstore.db;
import bookstore.core.Book;
import java.util.ArrayList;
import java.io.*;
public class SimpleGateway implements
        bookstore.app.addnewBook.Gateway,
        bookstore.app.listallBook.Gateway,
        bookstore.app.deleteBook.Gateway,
        bookstore.app.editBook.Gateway
{
    public final String file;

    public SimpleGateway(String file) {
        this.file = file;}
    public ArrayList<Book> load() {
        try (
            var fr = new FileReader(this.file);
            var br = new BufferedReader(fr) )
        {
            var books = new ArrayList<Book>();;
            while (true){
                var str = br.readLine();
                if(str == null) break;
                var book = parseBook(str);
                books.add(book);
            }
            return books;
        }catch (FileNotFoundException e){
            return new ArrayList<>();
        } catch (IOException e){
            throw new RuntimeException("Could not read file " + this.file);
        }
    }
    public void save(ArrayList<Book> books){
        try (var fw = new FileWriter(this.file);
            var bw = new BufferedWriter(fw) ){
        for(var book : books){
            var str = this.formatBook(book);
            bw.write(str);
            bw.newLine();
        }
        }catch (IOException e){
            throw new RuntimeException("Could not write file " + this.file);
        }
    }
    private Book parseBook(String str){
        try {
            var a = str.split("\\|");
            var id = Integer.parseInt(a[0]);
            var title = a[1];
            var author = a[2];
            return new Book(id,title,author);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    private String formatBook(Book book){
        return String.format("%d|%s|%s",book.id(),book.title(),book.author());
    }
}
