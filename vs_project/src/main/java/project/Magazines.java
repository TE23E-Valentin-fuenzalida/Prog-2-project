package project;
/* 
Valentin fuenzalida ribbing
magazines innehåller variabler med en konstuktor, getters och setters.
*/
public class Magazines extends library {
    // variabler
    private int issueNumber;
    private String category;
    private int publishedYear;

    //konstruktor för Magazines
    public Magazines(String id, String title, boolean isAvailable, int issueNumber, String category, int publishedYear){
        super(id, title, isAvailable);
        this.issueNumber=issueNumber;
        this.category=category;
        this.publishedYear=publishedYear;
    }

    // getters
    public String getCatogory() {
        return category;
    }
    public int getIssueNumber() {
        return issueNumber;
    }
    public int getPublishedYear() {
        return publishedYear;
    }
    
    // setters
    public void setCatogory(String catogory) {
        this.category = catogory;
    }
    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    @Override
    public String toString() {
        return "\n"+ "id: "+id+"\n title: "+title+"\n issueNumber: "+issueNumber+"\n category: "+category+"\n publishedYear: "+publishedYear+"\n isAvailable: "+isAvailable+"\n";
    }
}
