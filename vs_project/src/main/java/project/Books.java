package project;
/* 
Valentin fuenzalida ribbing
Books innehåller variabler med en konstuktor, getters, setters och
två override en för att skriva ut och en för sortera.
*/
public class Books extends library implements Comparable<Books>{
    // variabler för Books
    private String author;
    private String genre;
    private int pages;
    
    public Books(String id, String title, boolean isAvailable, String author, String genre, int pages){
        super(id, title, isAvailable);
        this.author=author;
        this.genre=genre;
        this.pages=pages;
    }
    public String getAuthor() {
        return author;
    }
    public String getGenre() {
        return genre;
    }
    public int getPages() {
        return pages;
    }

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
    // Override för att skria ut
    public String toString() {
        return "\n"+"id: "+id+"\n title: "+title+"\n author: "+author+"\n genre: "+genre+"\n pages: "+pages+"\n isAvailable: "+isAvailable+"\n";
    }
    @Override
    // Override för att sortera efter titel
    public int compareTo(Books o) {
        return this.title.compareToIgnoreCase(o.title);
    }
}
