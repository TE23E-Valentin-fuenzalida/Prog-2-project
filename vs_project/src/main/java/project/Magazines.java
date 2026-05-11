package project;
/* 
Valentin fuenzalida ribbing
magazines innehåller variabler med en konstuktor, getters, setters och
två override en för att skriva ut och en för sortera.
*/
public class Magazines extends library implements Comparable<Magazines>{
    // variabler
    private int issueNumber;
    private String category;
    private int publishedYear;

    public Magazines(String id, String title, boolean isAvailable, int issueNumber, String category, int publishedYear){
        super(id, title, isAvailable);
        this.issueNumber=issueNumber;
        this.category=category;
        this.publishedYear=publishedYear;
    }

    public String getCatogory() {
        return category;
    }
    public int getIssueNumber() {
        return issueNumber;
    }
    public int getPublishedYear() {
        return publishedYear;
    }
    
    public void setCatogory(String catogory) {
        this.category = catogory;
    }
    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    @Override
    // Override för att skriva ut
    public String toString() {
        return "\n"+ "id: "+id+"\n title: "+title+"\n issueNumber: "+issueNumber+"\n category: "+category+"\n publishedYear: "+publishedYear+"\n isAvailable: "+isAvailable+"\n";
    }
    @Override
    // Override för att sortera efter titel
    public int compareTo(Magazines o) {
        return this.title.compareToIgnoreCase(o.title);
    }
}
