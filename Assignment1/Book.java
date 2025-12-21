import java.time.Year;
import java.util.Scanner;

public class Book {
    private int id;
    private static int idGen = 1;
    private String title;
    private String author;
    private int year;
    private boolean available;
//    true – the book is available;
//    false – the book is borrowed

    public Book(){
        id = idGen++;
        available = true;
    }

    public Book(String title, String author, int year){
        this();
        setTitle(title);
        setAuthor(author);
        setYear(year);
    }


    //    getters
    public int getID(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public int getYear(){
        return year;
    }

    public boolean getAvailable(){
        return available;
    }

    //    setters
    public void setTitle(String title){
        if( title != null ){
            this.title = title;
        }
    }

    public void setAuthor(String author){
        if( title != null && !title.trim().isEmpty() ){
            this.title = title.trim();
        } else {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
    }

    public void setYear(int year){
        int currentYear = Year.now().getValue();
        if( year >= 1500 && year <= currentYear ){
            this.year = year;
        } else {
            throw new IllegalArgumentException("Year must be between 1500 and " + currentYear);
        }
    }

    //    other methods
    public void markAsBorrowed(){
        available = false;
    }

    public void markAsReturned(){
        available = true;
    }

    @Override
    public String toString() {
        return "Book's id=" + id +
                " title=" + title +
                " author=" + author +
                " year=" + year +
                " available status=" + available;
    }
}