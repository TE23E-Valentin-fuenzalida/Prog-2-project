package project;
/* 
Valentin fuenzalida ribbing
Books innehåller variabler med en konstuktor, setters och getters.
*/
public class Books extends library{
    // variabler för Books
    private String author;
    private String genre;
    private int pages;
    
    // konstruktor för Books
    public Books(String id, String title, boolean isAvailable, String author, String genre, int pages){
        super(id, title, isAvailable);
        this.author=author;
        this.genre=genre;
        this.pages=pages;
    }
    // getters
    public String getAuthor() {
        return author;
    }
    public String getGenre() {
        return genre;
    }
    public int getPages() {
        return pages;
    }

    // setters
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setPages(int pages) {
        this.pages = pages;
    }

     @Override
    public String toString() {
        return "\n"+"id: "+id+"\n title: "+title+"\n author: "+author+"\n genre: "+genre+"\n pages: "+pages+"\n isAvailable: "+isAvailable+"\n";
    }
}
