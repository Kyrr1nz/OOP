package bookstore.core;
public class Book {
    private final int id;
    private final String title;
    private final String author;

    public Book(int id,String title,String author)throws InvalidBookException {
            if(title.isBlank()){
            throw new InvalidBookException("Title cannot be empty");
        }
        if(title.contains("|")){
            throw new InvalidBookException("Title cannot contain |");
        }
        if(author.isBlank()){
            throw new InvalidBookException("Author cannot be empty");
        }
        if(author.contains("|")){
            throw new InvalidBookException("Author cannot contain |");

        }
    this.id = id;
        this.title = title;
        this.author = author;
    }
    public int id(){return this.id;}
    public String title(){return this.title;}
    public String author(){return this.author;}
}
